package com.backend.mlopsbackend.Entities;

import java.util.Map;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="requests", schema = "public")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto generate id
    private Long id;

    public Long requesterId;

    // The 20 fields info
    private String field0;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String field6;
    private String field7;
    private String field8;
    private String field9;
    private String field10;
    private String field11;
    private String field12;
    private String field13;
    private String field14;
    private String field15;
    private String field16;
    private String field17;
    private String field18;
    private String field19;

    private String prediction;
    private String feedback;
    
    public Request(){}

    public void SetValuesFromMap (Map<String,List<Integer>> map) {
        this.field0 = map.get("0").get(0).toString();
        this.field1 = map.get("1").get(0).toString();
        this.field2 = map.get("2").get(0).toString();
        this.field3 = map.get("3").get(0).toString();
        this.field4 = map.get("4").get(0).toString();
        this.field5 = map.get("5").get(0).toString();
        this.field6 = map.get("6").get(0).toString();
        this.field7 = map.get("7").get(0).toString();
        this.field8 = map.get("8").get(0).toString();
        this.field9 = map.get("9").get(0).toString();
        this.field10 = map.get("10").get(0).toString();
        this.field11 = map.get("11").get(0).toString();
        this.field12 = map.get("12").get(0).toString();
        this.field13 = map.get("13").get(0).toString();
        this.field14 = map.get("14").get(0).toString();
        this.field15 = map.get("15").get(0).toString();
        this.field16 = map.get("16").get(0).toString();
        this.field17 = map.get("17").get(0).toString();
        this.field18 = map.get("18").get(0).toString();
        this.field19 = map.get("19").get(0).toString();
    }

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for field0
    public String getField0() {
        return field0;
    }

    public void setField0(String field0) {
        this.field0 = field0;
    }

    // Getter and Setter for field1
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    // Getter and Setter for field2
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    // Getter and Setter for field3
    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    // Getter and Setter for field4
    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    // Getter and Setter for field5
    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    // Getter and Setter for field6
    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    // Getter and Setter for field7
    public String getField7() {
        return field7;
    }

    public void setField7(String field7) {
        this.field7 = field7;
    }

    // Getter and Setter for field8
    public String getField8() {
        return field8;
    }

    public void setField8(String field8) {
        this.field8 = field8;
    }

    // Getter and Setter for field9
    public String getField9() {
        return field9;
    }

    public void setField9(String field9) {
        this.field9 = field9;
    }

    // Getter and Setter for field10
    public String getField10() {
        return field10;
    }

    public void setField10(String field10) {
        this.field10 = field10;
    }

    // Getter and Setter for field11
    public String getField11() {
        return field11;
    }

    public void setField11(String field11) {
        this.field11 = field11;
    }

    // Getter and Setter for field12
    public String getField12() {
        return field12;
    }

    public void setField12(String field12) {
        this.field12 = field12;
    }

    // Getter and Setter for field13
    public String getField13() {
        return field13;
    }

    public void setField13(String field13) {
        this.field13 = field13;
    }

    // Getter and Setter for field14
    public String getField14() {
        return field14;
    }

    public void setField14(String field14) {
        this.field14 = field14;
    }

    // Getter and Setter for field15
    public String getField15() {
        return field15;
    }

    public void setField15(String field15) {
        this.field15 = field15;
    }

    // Getter and Setter for field16
    public String getField16() {
        return field16;
    }

    public void setField16(String field16) {
        this.field16 = field16;
    }

    // Getter and Setter for field17
    public String getField17() {
        return field17;
    }

    public void setField17(String field17) {
        this.field17 = field17;
    }

    // Getter and Setter for field18
    public String getField18() {
        return field18;
    }

    public void setField18(String field18) {
        this.field18 = field18;
    }

    // Getter and Setter for field19
    public String getField19() {
        return field19;
    }

    public void setField19(String field19) {
        this.field19 = field19;
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
