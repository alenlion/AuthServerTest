package com.kalem.authservertest.baseclass.model;


import com.kalem.authservertest.baseclass.enums.DeletedType;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class CoreEntity implements Serializable {

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "CREATE_DATE", nullable = false, updatable = false )
    private LocalDateTime createdDate;

    @Temporal( TemporalType.TIMESTAMP )
    @Column( name = "MODIFY_DATE" )
    private LocalDateTime modifiedDate;

    @Column( name = "DELETED", columnDefinition = "smallint", nullable = false, length = 1 )
    private DeletedType deleted = DeletedType.FALSE;

    @PrePersist
    public void setCreationDateTime() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void setChangeDateTime() {
        this.modifiedDate = LocalDateTime.now();
    }
}