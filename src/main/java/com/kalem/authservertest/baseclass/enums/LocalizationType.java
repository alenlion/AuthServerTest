package com.kalem.authservertest.baseclass.enums;

public enum LocalizationType {
    tr_TR((short) 0),
    en_US((short) 1),
    az_AZ((short) 2),
    ru_RU((short) 3),
    de_DE((short) 4);

    private final short value;

    LocalizationType(short value) {
        this.value = value;
    }

}
