package com.kalem.authservertest.baseclass.enums;

import lombok.ToString;

@ToString
public enum DeletedType {
    FALSE((short) 0),
    TRUE((short) 1);

    private final short value;

    DeletedType(short value) {
        this.value = value;
    }
}
