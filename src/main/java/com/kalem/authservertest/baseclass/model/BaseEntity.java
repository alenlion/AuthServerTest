package com.kalem.authservertest.baseclass.model;

import jakarta.persistence.MappedSuperclass;
/**
 * @author Rayan Aksu
 * @since 7/27/2023
 */

@MappedSuperclass
public abstract class BaseEntity<I extends Number> extends CoreEntity{
    public abstract I getId();

    public abstract void setId( I id );

}
