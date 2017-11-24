package com.example.rajvi.rajvihospital;

/**
 * Created by Rajvi on 07-Nov-2017.
 */

public class Test {
    //private variables
    int testID, patientID, heart_rate;
    double bpl, bph, temp, blood_sugar_level;

    //empty constructor
    public Test(){}

    //5 parameters
    public Test(int patientid, double bpl, double bph, double temp, double bsl, int hrt){
        this.patientID = patientid;
        this.bpl = bpl;
        this.bph = bph;
        this.temp = temp;
        this.blood_sugar_level = bsl;
        this.heart_rate = hrt;
    }

    //6 parameters
    public Test(int testid, int patientid, double bpl, double bph, double temp, double bsl, int hrt){
        this.testID = testid;
        this.patientID = patientid;
        this.bpl = bpl;
        this.bph = bph;
        this.temp = temp;
        this.blood_sugar_level = bsl;
        this.heart_rate = hrt;
    }

    // Getters and Setters
    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public double getBpl() {
        return bpl;
    }

    public void setBpl(double bpl) {
        this.bpl = bpl;
    }

    public double getBph() {
        return bph;
    }

    public void setBph(double bph) {
        this.bph = bph;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getBsl() {
        return blood_sugar_level;
    }

    public void setBsl(double bsl) {
        this.blood_sugar_level = bsl;
    }

    public int getHrt() {
        return heart_rate;
    }

    public void setHrt(int hrt) {
        this.heart_rate = hrt;
    }
}