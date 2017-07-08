package com.kaishengit.entity;

import java.sql.Timestamp;

public class Acc {
    private int id;
    private String name;
    private String password;
    private String idNo;
    private String tel;
    private int companyId;
    private String dept;

    private Company company;

    private Timestamp lastLoginTime;
    private String lastLoginIp;

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Acc() {
    }

    public Acc(int id, String name, String password, String idNo, String tel, int companyId, String dept) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.idNo = idNo;
        this.tel = tel;
        this.companyId = companyId;
        this.dept = dept;
    }


    @Override
    public String toString() {
        return "Acc [id=" + id + ", name=" + name + ", password=" + password + ", idNo=" + idNo + ", tel=" + tel
                + ", companyId=" + companyId + ", dept=" + dept + "]";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
