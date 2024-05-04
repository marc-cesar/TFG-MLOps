package com.backend.mlopsbackend.Entities;

import java.sql.Timestamp;
import java.util.Map;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name="requests", schema = "public")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto generate id
    private Long id;

    public Long requesterId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    public Client client;

    @Column(name="approval_time", nullable = false)
    public Timestamp approvalTime;

    // The 20 fields info
    private String statusExistingAccount;
    private String monthsDuration;
    private String creditHistory;
    private String purpose;
    private String creditAmount;
    private String savingAccount;
    private String presentEmploymentSince;
    private String installmentPercentage;
    private String statusAndSex;
    private String otherDebtors;
    private String presentResidenceSince;
    private String property;
    private String ageInYears;
    private String otherInstallmentPlans;
    private String housing;
    private String numberOfExistingCredits;
    private String job;
    private String peopleToProvideMaintenance;
    private String telephoneNumber;
    private String foreignWorker;

    private String prediction;
    private String feedback;
    
    public Assessment(){}

    public void SetValuesFromMap (Map<String,List<Integer>> map) {
        this.statusExistingAccount = map.get("0").get(0).toString();
        this.monthsDuration = map.get("1").get(0).toString();
        this.creditHistory = map.get("2").get(0).toString();
        this.purpose = map.get("3").get(0).toString();
        this.creditAmount = map.get("4").get(0).toString();
        this.savingAccount = map.get("5").get(0).toString();
        this.presentEmploymentSince = map.get("6").get(0).toString();
        this.installmentPercentage = map.get("7").get(0).toString();
        this.statusAndSex = map.get("8").get(0).toString();
        this.otherDebtors = map.get("9").get(0).toString();
        this.presentResidenceSince = map.get("10").get(0).toString();
        this.property = map.get("11").get(0).toString();
        this.ageInYears = map.get("12").get(0).toString();
        this.otherInstallmentPlans = map.get("13").get(0).toString();
        this.housing = map.get("14").get(0).toString();
        this.numberOfExistingCredits = map.get("15").get(0).toString();
        this.job = map.get("16").get(0).toString();
        this.peopleToProvideMaintenance = map.get("17").get(0).toString();
        this.telephoneNumber = map.get("18").get(0).toString();
        this.foreignWorker = map.get("19").get(0).toString();
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for field0
    public String getStatusExistingAccount() {
        return statusExistingAccount;
    }

    public void setStatusExistingAccount(String field0) {
        this.statusExistingAccount = field0;
    }

    // Getter and Setter for field1
    public String getMonthsDuration() {
        return monthsDuration;
    }

    public void setMonthsDuration(String field1) {
        this.monthsDuration = field1;
    }

    // Getter and Setter for field2
    public String getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(String field2) {
        this.creditHistory = field2;
    }

    // Getter and Setter for field3
    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String field3) {
        this.purpose = field3;
    }

    // Getter and Setter for field4
    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String field4) {
        this.creditAmount = field4;
    }

    // Getter and Setter for field5
    public String getSavingAccount() {
        return savingAccount;
    }

    public void setSavingAccount(String field5) {
        this.savingAccount = field5;
    }

    // Getter and Setter for field6
    public String getPresentEmploymentSince() {
        return presentEmploymentSince;
    }

    public void setPresentEmploymentSince(String field6) {
        this.presentEmploymentSince = field6;
    }

    // Getter and Setter for field7
    public String getInstallmentPercentage() {
        return installmentPercentage;
    }

    public void setInstallmentPercentage(String field7) {
        this.installmentPercentage = field7;
    }

    // Getter and Setter for field8
    public String getStatusAndSex() {
        return statusAndSex;
    }

    public void setStatusAndSex(String field8) {
        this.statusAndSex = field8;
    }

    // Getter and Setter for field9
    public String getOtherDebtors() {
        return otherDebtors;
    }

    public void setOtherDebtors(String field9) {
        this.otherDebtors = field9;
    }

    // Getter and Setter for field10
    public String getPresentResidenceSince() {
        return presentResidenceSince;
    }

    public void setPresentResidenceSince(String field10) {
        this.presentResidenceSince = field10;
    }

    // Getter and Setter for field11
    public String getProperty() {
        return property;
    }

    public void setProperty(String field11) {
        this.property = field11;
    }

    // Getter and Setter for field12
    public String getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(String field12) {
        this.ageInYears = field12;
    }

    // Getter and Setter for field13
    public String getOtherInstallmentPlans() {
        return otherInstallmentPlans;
    }

    public void setOtherInstallmentPlans(String field13) {
        this.otherInstallmentPlans = field13;
    }

    // Getter and Setter for field14
    public String getHousing() {
        return housing;
    }

    public void setHousing(String field14) {
        this.housing = field14;
    }

    // Getter and Setter for field15
    public String getNumberOfExistingCredits() {
        return numberOfExistingCredits;
    }

    public void setNumberOfExistingCredits(String field15) {
        this.numberOfExistingCredits = field15;
    }

    // Getter and Setter for field16
    public String getJob() {
        return job;
    }

    public void setJob(String field16) {
        this.job = field16;
    }

    // Getter and Setter for field17
    public String getPeopleToProvideMaintenance() {
        return peopleToProvideMaintenance;
    }

    public void setPeopleToProvideMaintenance(String field17) {
        this.peopleToProvideMaintenance = field17;
    }

    // Getter and Setter for field18
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String field18) {
        this.telephoneNumber = field18;
    }

    // Getter and Setter for field19
    public String getForeignWorker() {
        return foreignWorker;
    }

    public void setForeignWorker(String field19) {
        this.foreignWorker = field19;
    }

    // Getter and Setter for prediction
    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    // Getter and Setter for feedback
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
