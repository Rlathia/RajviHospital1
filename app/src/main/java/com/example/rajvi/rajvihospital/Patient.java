package com.example.rajvi.rajvihospital;

/**
 * Created by Rajvi on 06-Nov-2017.
 */

public class Patient {

    //private variables
    int id, doctorID;
    String fname, lname, department, room;

    //empty constructor
    public Patient(){

    }

    //5 parameter
    public Patient(int id, String fname, String lname, String depart, int doctorID, String room){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.department = depart;
        this.doctorID = doctorID;
        this.room = room;
    }

    //4 parameter
    public Patient( String fname, String lname, String depart, int doctorID, String room){
        this.fname = fname;
        this.lname = lname;
        this.department = depart;
        this.doctorID = doctorID;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) { this.room = room; }
}