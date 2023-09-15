package com.example.noCountry.DTO;

import com.example.noCountry.Entity.FiscalCondition;
import com.example.noCountry.Entity.Industry;
import com.example.noCountry.Entity.Location;
import com.example.noCountry.Entity.Role;

public class EmployerDTO extends UserDTO {
    
    private String enterpriseName;
    private FiscalCondition fiscalCondition;
    private Long cuit;
    private String taxResidence;
    private Industry industryType;
    private Boolean isVerified;
    
    public EmployerDTO(String enterpriseName, FiscalCondition fiscalCondition, Long cuit, String taxResidence, Industry industryType, Boolean isVerified) {
        this.enterpriseName = enterpriseName;
        this.fiscalCondition = fiscalCondition;
        this.cuit = cuit;
        this.taxResidence = taxResidence;
        this.industryType = industryType;
        this.isVerified = isVerified;
    }

    public EmployerDTO(String enterpriseName, FiscalCondition fiscalCondition, Long cuit, String taxResidence, Industry industryType, Boolean isVerified, String email, String password, String firstname, String lastname, Integer contactNum, Role role, Location location, String country) {
        super(email, password, firstname, lastname, contactNum, role, location, country);
        this.enterpriseName = enterpriseName;
        this.fiscalCondition = fiscalCondition;
        this.cuit = cuit;
        this.taxResidence = taxResidence;
        this.industryType = industryType;
        this.isVerified = isVerified;
    }

    

    public EmployerDTO() {
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public FiscalCondition getFiscalCondition() {
        return fiscalCondition;
    }

    public void setFiscalCondition(FiscalCondition fiscalCondition) {
        this.fiscalCondition = fiscalCondition;
    }

    public Long getCuit() {
        return cuit;
    }

    public void setCuit(Long cuit) {
        this.cuit = cuit;
    }

    public String getTaxResidence() {
        return taxResidence;
    }

    public void setTaxResidence(String taxResidence) {
        this.taxResidence = taxResidence;
    }

    public Industry getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Industry industryType) {
        this.industryType = industryType;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }
    
    
}
