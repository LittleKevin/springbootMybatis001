package com.kevin.mybatis_demo.model;

import lombok.Data;

@Data
public class CompanyInfo {
    private String companyId;
    private String companyName;
    private String month;
    private String dzTotal;

    public CompanyInfo(String companyId, String companyName, String month, String dzTotal) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.month = month;
        this.dzTotal = dzTotal;
    }
    public CompanyInfo(){}

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDzTotal() {
        return dzTotal;
    }

    public void setDzTotal(String dzTotal) {
        this.dzTotal = dzTotal;
    }
}
