package com.kalem.authservertest.config;

import com.kalem.authservertest.filter.CsrfCookieFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    /**
     * NoOpPasswordEncoder is not recommended for production usage.
     * Use only for non-prod.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain( HttpSecurity http ) throws Exception {

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName( "_csrf" );

        http.securityContext( ( context ) -> context
                        .requireExplicitSave( false ) )
                .sessionManagement( session -> session.sessionCreationPolicy( SessionCreationPolicy.ALWAYS ) )
                .cors( corsCustomizer -> corsCustomizer.configurationSource( new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration( HttpServletRequest request ) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins( Collections.singletonList( "http://localhost:4200" ) );
                        config.setAllowedMethods( Collections.singletonList( "*" ) );
                        config.setAllowCredentials( true );
                        config.setAllowedHeaders( Collections.singletonList( "*" ) );
                        config.setMaxAge( 3600L );
                        return config;
                    }
                } ) ).csrf( ( csrf ) -> csrf.csrfTokenRequestHandler( requestHandler ).ignoringRequestMatchers( "/contact", "/user/register" )
                        .csrfTokenRepository( CookieCsrfTokenRepository.withHttpOnlyFalse() ) )
                .addFilterAfter( new CsrfCookieFilter(), BasicAuthenticationFilter.class )
                .authorizeHttpRequests( ( requests ) -> requests
                        .requestMatchers( "/myAccount", "/myBalance", "/myLoans", "/myCards", "/user" ).authenticated()
                        .requestMatchers( "/notices", "/contact", "/user/register" ).permitAll() )
                .formLogin( Customizer.withDefaults() )
                .httpBasic( Customizer.withDefaults() );
        return http.build();

        /**
         *  Configuration to deny all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().denyAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/

        /**
         *  Configuration to permit all the requests
         */
        /*http.authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();*/
    }

}
