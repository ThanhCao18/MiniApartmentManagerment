package com.ltc.btl_javafx.model;

import java.time.LocalDate;

public class Member extends Person {
    private String memberID;

    public Member(String memberID, String name, String sex, LocalDate birthdate, String citizenID, String phoneNum, String placeOrigin) {
        super(name, sex, birthdate, citizenID, phoneNum, placeOrigin);
        this.memberID = memberID;
    }

    public String getMemberID() {
        return this.memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }
}

