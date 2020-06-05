package com.trace.all.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*运输方*/
@Entity
public class Transporter {
    @Id
    @GeneratedValue
    private int id;

    private String companyName;

    private String personInCharge;

    private String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPersonInCharge() {
        return personInCharge;
    }

    public void setPersonInCharge(String personInCharge) {
        this.personInCharge = personInCharge;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Transporter{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", personInCharge='" + personInCharge + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
