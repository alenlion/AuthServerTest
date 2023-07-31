package com.kalem.authservertest.baseclass.enums;

public enum UserType {
    CENTER_USER((short) 0),  //Merkez Kullanıcısı
    BRANCH_USER((short) 1),  //Şube Kullanıcısı
    MOBILE_USER((short) 2),  //Mobile Merkez Kullanıcısı
    FRC_USER((short) 3),  //Fanchise Kullanıcısı
    MOBILE_USER_BRANCH((short) 4);  //Mobile Mağza Kullanıcı

    private final short value;

    UserType( short value) {
        this.value = value;
    }
}
