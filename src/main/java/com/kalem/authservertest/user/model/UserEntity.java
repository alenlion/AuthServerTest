package com.kalem.authservertest.user.model;

import com.kalem.authservertest.baseclass.enums.LocalizationType;
import com.kalem.authservertest.baseclass.enums.UserStatusType;
import com.kalem.authservertest.baseclass.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Data
@EqualsAndHashCode( callSuper = false )
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "USERS",
        indexes = {
                @Index(name = "USERS_I1", columnList = "DELETED"),
                @Index(name = "USERS_I2", columnList = "DELETED,STATUS,USERNAME")
        }
        )

public class UserEntity extends BaseEntity<Integer> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;

        @Column(name = "PASSWORD", nullable = true, length = 500)
    private String password;

    @Column(name = "USERNAME", nullable = true, length = 50)
    private String username;

    @Column(name = "FULL_NAME", nullable = true, length = 100)
    private String fullName;

    @Column(name = "COMPANY_ID")
    private Integer companyId;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column( name = "STATUS", columnDefinition = "smallint", nullable = true, length = 1 )
    private UserStatusType status;

    @Column(name = "PASSWRD_EXPIRED", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordExpired;

    @Enumerated(EnumType.STRING)
    @Column(name = "LANG_TYPE", nullable = true, length = 5)
    private LocalizationType localization = LocalizationType.tr_TR;

    @Column(name = "LOGIN_DEVICE", length = 100)
    private String loginDevice;

    @Column(name = "FIRSTNAME", nullable = true, length = 50)
    private String firstname;

    @Column(name = "LASTNAME", nullable = true, length = 50)
    private String lastname;

    @Column(name = "PHONE", nullable = true, length = 50)
    private String phone;

    @Column(name = "ROLE", nullable = true, length = 50)
    private String role;

    //    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    @Fetch(FetchMode.SELECT)
//    @Where(clause = "deleted <> 1")
//    private Collection<UserRoleEntity> roles;

}
