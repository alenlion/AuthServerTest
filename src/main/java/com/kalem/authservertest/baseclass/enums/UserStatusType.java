package com.kalem.authservertest.baseclass.enums;

public enum UserStatusType {
    NONE((short) 0),
    INACTIVE((short) 1),
    ACTIVE((short) 2),
    PASSIVE((short) 3),
    BLOCK((short) 4);

    private short value;

    UserStatusType( short value) {
        this.value = value;
    }
}
