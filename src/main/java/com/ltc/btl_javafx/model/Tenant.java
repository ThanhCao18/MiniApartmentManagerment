package com.ltc.btl_javafx.model;

import java.time.LocalDate;
import java.util.Objects;

public class Tenant extends Person {
    private String tenantID;
    private LocalDate rentDate;

    public Tenant() {
    }

    public Tenant(String tenantID, String name, String sex, LocalDate birthdate, String citizenID, String phoneNum, String placeOrigin, LocalDate rentDate) {
        super(name, sex, birthdate, citizenID, phoneNum, placeOrigin);
        this.tenantID = tenantID;
        this.placeOrigin = placeOrigin;
        this.rentDate = rentDate;
    }

    public String getTenantID() {
        return this.tenantID;
    }

    public void setTenantID(String tenantID) {
        this.tenantID = tenantID;
    }

    public LocalDate getRentDate() {
        return this.rentDate;
    }

    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Tenant other = (Tenant)obj;
            return Objects.equals(this.birthdate, other.birthdate) && Objects.equals(this.citizenID, other.citizenID) && Objects.equals(this.tenantID, other.tenantID) && Objects.equals(this.name, other.name) && Objects.equals(this.phoneNum, other.phoneNum) && Objects.equals(this.placeOrigin, other.placeOrigin) && Objects.equals(this.rentDate, other.rentDate) && Objects.equals(this.sex, other.sex);
        }
    }
}
