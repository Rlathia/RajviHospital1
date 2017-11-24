package com.example.rajvi.rajvihospital;

/**
 * Created by Rajvi on 13-Nov-2017.
 */

public class Doctor {
    //private variables
    int id;
    String username;
    String password;
    String fname;
    String lname;
    String department;
    int type;
    //empty constructor
    public Doctor(){

    }

    //contructor with username and password
    public Doctor(String username, String password){
        this.username = username;
        this.password = password;
    }
    //7 parameter
    public Doctor( int id, String username, String password, String fname, String lname, String depart, int type ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.department = depart;
        this.type = type;
    }

    //6 parameters
    public Doctor( String username, String password, String fname, String lname, String depart, int type ){
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.department = depart;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
