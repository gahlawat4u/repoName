package com.gms.xms.txndb.vo;

import com.gms.xms.common.json.JsonDate2StringSerializer;
import com.gms.xms.common.json.JsonDouble2StringSerializer;
import com.gms.xms.common.json.JsonString2DateDeserializer;
import com.gms.xms.common.json.JsonString2DoubleDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Posted from SalesRepVo
 * <p>
 * Author HoangPH Nov 19, 2015
 */
public class SalesRepVo extends BaseVo {

    private static final long serialVersionUID = 1L;

    private Long salesRepId;
    private Long userId;
    private Date hireDate;
    private Date terminateDate;
    private String salesManager;
    private Double percentPayout;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String fax;
    private String email;
    private Boolean useWebshipContactInformation;
    private Double salary;
    private Double vehicleAllowance;
    private Double phoneAllowance;
    private Double healthAllowance;
    private String allowance1Description;
    private Double allowance1Amount;
    private String allowance2Description;
    private Double allowance2Amount;
    private String allowance3Description;
    private Double allowance3Amount;
    private String deduction1Description;
    private Double deduction1Amount;
    private String deduction2Description;
    private Double deduction2Amount;
    private String deduction3Description;
    private Double deduction3Amount;
    private String setups;
    private String activation;
    private String dayOfActivate;
    private Boolean excludeRanking;

    public Long getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(Long salesRepId) {
        this.salesRepId = salesRepId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getHireDate() {
        return hireDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @JsonSerialize(using = JsonDate2StringSerializer.class)
    public Date getTerminateDate() {
        return terminateDate;
    }

    @JsonDeserialize(using = JsonString2DateDeserializer.class)
    public void setTerminateDate(Date terminateDate) {
        this.terminateDate = terminateDate;
    }

    public String getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(String salesManager) {
        this.salesManager = salesManager;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPercentPayout() {
        return percentPayout;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setPercentPayout(Double percentPayout) {
        this.percentPayout = percentPayout;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getUseWebshipContactInformation() {
        return useWebshipContactInformation;
    }

    public void setUseWebshipContactInformation(Boolean useWebshipContactInformation) {
        this.useWebshipContactInformation = useWebshipContactInformation;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getSalary() {
        return salary;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getVehicleAllowance() {
        return vehicleAllowance;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setVehicleAllowance(Double vehicleAllowance) {
        this.vehicleAllowance = vehicleAllowance;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getPhoneAllowance() {
        return phoneAllowance;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setPhoneAllowance(Double phoneAllowance) {
        this.phoneAllowance = phoneAllowance;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getHealthAllowance() {
        return healthAllowance;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setHealthAllowance(Double healthAllowance) {
        this.healthAllowance = healthAllowance;
    }

    public String getAllowance1Description() {
        return allowance1Description;
    }

    public void setAllowance1Description(String allowance1Description) {
        this.allowance1Description = allowance1Description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAllowance1Amount() {
        return allowance1Amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setAllowance1Amount(Double allowance1Amount) {
        this.allowance1Amount = allowance1Amount;
    }

    public String getAllowance2Description() {
        return allowance2Description;
    }

    public void setAllowance2Description(String allowance2Description) {
        this.allowance2Description = allowance2Description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAllowance2Amount() {
        return allowance2Amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setAllowance2Amount(Double allowance2Amount) {
        this.allowance2Amount = allowance2Amount;
    }

    public String getAllowance3Description() {
        return allowance3Description;
    }

    public void setAllowance3Description(String allowance3Description) {
        this.allowance3Description = allowance3Description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getAllowance3Amount() {
        return allowance3Amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setAllowance3Amount(Double allowance3Amount) {
        this.allowance3Amount = allowance3Amount;
    }

    public String getDeduction1Description() {
        return deduction1Description;
    }

    public void setDeduction1Description(String deduction1Description) {
        this.deduction1Description = deduction1Description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDeduction1Amount() {
        return deduction1Amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setDeduction1Amount(Double deduction1Amount) {
        this.deduction1Amount = deduction1Amount;
    }

    public String getDeduction2Description() {
        return deduction2Description;
    }

    public void setDeduction2Description(String deduction2Description) {
        this.deduction2Description = deduction2Description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDeduction2Amount() {
        return deduction2Amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setDeduction2Amount(Double deduction2Amount) {
        this.deduction2Amount = deduction2Amount;
    }

    public String getDeduction3Description() {
        return deduction3Description;
    }

    public void setDeduction3Description(String deduction3Description) {
        this.deduction3Description = deduction3Description;
    }

    @JsonSerialize(using = JsonDouble2StringSerializer.class)
    public Double getDeduction3Amount() {
        return deduction3Amount;
    }

    @JsonDeserialize(using = JsonString2DoubleDeserializer.class)
    public void setDeduction3Amount(Double deduction3Amount) {
        this.deduction3Amount = deduction3Amount;
    }

    public String getSetups() {
        return setups;
    }

    public void setSetups(String setups) {
        this.setups = setups;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getDayOfActivate() {
        return dayOfActivate;
    }

    public void setDayOfActivate(String dayOfActivate) {
        this.dayOfActivate = dayOfActivate;
    }

    public Boolean getExcludeRanking() {
        return excludeRanking;
    }

    public void setExcludeRanking(Boolean excludeRanking) {
        this.excludeRanking = excludeRanking;
    }

    @Override
    public String toString() {
        return "SalesRepVo [salesRepId=" + salesRepId + ", userId=" + userId + ", hireDate=" + hireDate + ", terminateDate=" + terminateDate + ", salesManager=" + salesManager + ", percentPayout=" + percentPayout + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", phone=" + phone + ", fax=" + fax + ", email=" + email + ", useWebshipContactInformation=" + useWebshipContactInformation + ", salary=" + salary + ", vehicleAllowance="
                + vehicleAllowance + ", phoneAllowance=" + phoneAllowance + ", healthAllowance=" + healthAllowance + ", allowance1Description=" + allowance1Description + ", allowance1Amount=" + allowance1Amount + ", allowance2Description=" + allowance2Description + ", allowance2Amount=" + allowance2Amount + ", allowance3Description=" + allowance3Description + ", allowance3Amount=" + allowance3Amount + ", deduction1Description=" + deduction1Description + ", deduction1Amount=" + deduction1Amount
                + ", deduction2Description=" + deduction2Description + ", deduction2Amount=" + deduction2Amount + ", deduction3Description=" + deduction3Description + ", deduction3Amount=" + deduction3Amount + ", setups=" + setups + ", activation=" + activation + ", dayOfActivate=" + dayOfActivate + ", excludeRanking=" + excludeRanking + "]";
    }
}