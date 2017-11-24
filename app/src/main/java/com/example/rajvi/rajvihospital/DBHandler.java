package com.example.rajvi.rajvihospital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajvi on 03-Nov-2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    //all static variables
    //database version
    private static final int DATABASE_VERSION = 10;

    //database name
    private static final String DATABASE_NAME = "RajviHospital";

    //table name
    private static final String TABLE_NURSE = "Nurse";
    private static final String TABLE_DOCTOR = "Doctor";
    private static final String TABLE_PATIENT = "Patient";
    private static final String TABLE_TEST = "Test";

    //doctor table columns name
    private static final String KEY_D_ID = "doctor_id";
    private static final String KEY_D_USERNAME = "username";
    private static final String KEY_D_PASSWORD = "password";
    private static final String KEY_D_FNAME = "firstname";
    private static final String KEY_D_LNAME = "lastname";
    private static final String KEY_D_DEPARTMENT = "department";
    private static final String KEY_D_TYPE = "type";

    //patient table columns name
    private static final String KEY_P_ID = "patient_id";
    private static final String KEY_P_FNAME = "firstname";
    private static final String KEY_P_LNAME = "lastname";
    private static final String KEY_P_DEPARTMENT = "department";
    private static final String KEY_P_DID = "doctor_id";
    private static final String KEY_P_ROOM = "patient_room";

    //test table columns name
    private static final String KEY_T_ID = "test_id";
    private static final String KEY_T_PID = "patient_id";
    private static final String KEY_T_TEMP = "temperature";
    private static final String KEY_T_BPL = "bpl";
    private static final String KEY_T_BPH = "bph";
    private static final String KEY_T_HRT = "heart_rate";
    private static final String KEY_T_BSL = "blood_sugar_level";

    //constructor
    public DBHandler(Context contex) {
        super (contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DOCTOR_TABLE = "CREATE TABLE " + TABLE_DOCTOR + "("
                + KEY_D_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_D_USERNAME + " TEXT,"
                + KEY_D_PASSWORD + " TEXT,"
                + KEY_D_FNAME + " TEXT,"
                + KEY_D_LNAME + " TEXT,"
                + KEY_D_DEPARTMENT + " TEXT,"
                + KEY_D_TYPE + " INTEGER )";

        String CREATE_PATIENT_TABLE = "CREATE TABLE " + TABLE_PATIENT + "("
                + KEY_P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_P_FNAME + " TEXT,"
                + KEY_P_LNAME + " TEXT,"
                + KEY_P_DEPARTMENT + " TEXT,"
                + KEY_P_DID + " INTEGER,"
                + KEY_P_ROOM + " TEXT)";
                //+ "FOREIGN KEY ("+KEY_P_DID+") REFERENCES "+ TABLE_DOCTOR + "("+KEY_D_ID+"))";

        String CREATE_TEST_TABLE = "CREATE TABLE " + TABLE_TEST + "("
                + KEY_T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_T_PID + " INTEGER,"
                + KEY_T_TEMP + " REAL,"
                + KEY_T_BPL + " REAL,"
                + KEY_T_BPH + " REAL,"
                + KEY_T_BSL + " REAL,"
                + KEY_T_HRT + " INTEGER)";
                //+ "FOREIGN KEY ("+KEY_T_PID+") REFERENCES "+ TABLE_PATIENT + "("+KEY_P_ID+")

        db.execSQL(CREATE_DOCTOR_TABLE);
        db.execSQL(CREATE_PATIENT_TABLE);
        db.execSQL(CREATE_TEST_TABLE);
        Log.d("in onCreate()" , "rr tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DROP OLDER TABLE IF EXISTED
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        Log.d("tables dropped" , "rr in onUpgrade()");

        //CREATE TABLES AGAIN
        onCreate(db);
    }

    public void addNewPatient(Patient newPat) {

        Log.d("in db.addNewPatient()", "rr just entered");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_P_FNAME, newPat.getFname());
        values.put(KEY_P_LNAME, newPat.getLname());
        values.put(KEY_P_DEPARTMENT, newPat.getDepartment());
        values.put(KEY_P_DID, newPat.getDoctorID());
        values.put(KEY_P_ROOM, newPat.getRoom());

        Log.d("in db.addNewPatient()", "rr values assigned to the variable");
        //inserting the row
        db.insert(TABLE_PATIENT, null, values);
        Log.d("in db.addNewPatient()", "rr values added to the table");
        db.close();
    }

    //doctor
    public void addNewDoctor(Doctor newDoc) {

        Log.d("in db.addNewDoctor()", "rr just entered");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_D_USERNAME, newDoc.getUsername());
        values.put(KEY_D_PASSWORD, newDoc.getPassword());
        values.put(KEY_D_FNAME, newDoc.getFname());
        values.put(KEY_D_LNAME, newDoc.getLname());
        values.put(KEY_D_DEPARTMENT, newDoc.getDepartment());
        values.put(KEY_D_TYPE, newDoc.getType());

        Log.d("in db.addNewDoctor()", "rr values assigned to the variable");
        //inserting the row
        db.insert(TABLE_DOCTOR, null, values);
        Log.d("in db.addNewDoctor()", "rr values added to the table");
        db.close();
    }

    //add new patient
    public void addNewTest(Test newTest) {

        Log.d("in db.addNewTest()", "rr just entered");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_T_PID, newTest.getPatientID());
        values.put(KEY_T_TEMP, newTest.getTemp());
        values.put(KEY_T_BPL, newTest.getBpl());
        values.put(KEY_T_BPH, newTest.getBph());
        values.put(KEY_T_BSL, newTest.getBsl());
        values.put(KEY_T_HRT, newTest.getHrt());

        Log.d("in db.addNewTest()", "rr values assigned to the variable");
        //inserting the row
        db.insert(TABLE_TEST, null, values);
        Log.d("in db.addNewTest()", "rr row added to the table");
        db.close();
    }

    //getting all patients
    public List<Patient> getPatientList(){

        List<Patient> patientList = new ArrayList<>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Patient std = new Patient();
                std.setId(Integer.parseInt(cursor.getString(0)));
                std.setFname(cursor.getString(1));
                std.setLname(cursor.getString(2));
                std.setDepartment(cursor.getString(3));
                std.setDoctorID(Integer.parseInt(cursor.getString(4)));
                std.setRoom(cursor.getString(5));

                //adding patients to the list
                patientList.add(std);

            } while(cursor.moveToNext());
        }
        db.close();
        //return patient list
        return patientList;
    }

    // getting all tests
    public List<Test> getTestList(){

        List<Test> testList = new ArrayList<>();
        //Select all query
        String selectQuery = "SELECT * FROM " + TABLE_TEST;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                Test test = new Test();
                test.setTestID(Integer.parseInt(cursor.getString(0)));
                test.setPatientID(Integer.parseInt(cursor.getString(1)));
                test.setBpl(Double.parseDouble(cursor.getString(2)));
                test.setBph(Double.parseDouble(cursor.getString(3)));
                test.setTemp(Double.parseDouble(cursor.getString(4)));
                test.setBsl(Double.parseDouble(cursor.getString(5)));
                test.setHrt(Integer.parseInt(cursor.getString(6)));

                //adding tests to the list
                testList.add(test);

            } while(cursor.moveToNext());
        }
        db.close();
        //return test list
        return testList;
    }

    //List of Doctor's id, firstname and lastname
    public List<String> getDoctorNameList(){

        List<String> doctorList = new ArrayList<>();
        //Select first name and last name query
        String selectQuery = "SELECT " + KEY_D_ID + ", " + KEY_D_FNAME + ", " + KEY_D_LNAME + " FROM " + TABLE_DOCTOR + " WHERE " + KEY_D_TYPE + " = 0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()){
            do{
                String doctor = "  " + cursor.getString(0) + ": " + cursor.getString(1) + " " + cursor.getString(2);
                Log.d("DoctorList: ",doctor);
                //adding (all Doctor's first and last name) string :doctor  to the list
                doctorList.add(doctor);

            } while(cursor.moveToNext());
        }
        db.close();
        //return test list
        return doctorList;
    }

    //Checking for username already exists
    public boolean isUniqueUsername(String username){

        //select the doctors/nurses having the same username and password as entered by the user in login form
        String userExistsQuery = "SELECT COUNT(*) FROM " + TABLE_DOCTOR + " WHERE " + KEY_D_USERNAME + " = '" + username + "';";
        Log.d("validUser() Query ","rr \"SELECT COUNT(*) FROM " + TABLE_DOCTOR + " WHERE " + KEY_D_USERNAME + " = " + username + ";");
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(userExistsQuery, null);

            //looping through all rows and adding to the string
            if(cursor.getCount() > 0){
                Log.d("Username ","count > 0");
                return false;
            }
            db.close();
        } catch(Exception e){
            Log.d("db isUniqueUsername()","rr e: "+ e);
            return false;
        }
        return true;
    }

    //Checking username and password
    public boolean validUser(Doctor doc){

        String username = doc.getUsername();
        String password = doc.getPassword();
        String str = null;
        //select the doctors/nurses having the same username and password as entered by the user in login form
        String userQuery = "SELECT " + KEY_D_ID + " FROM " + TABLE_DOCTOR + " WHERE " + KEY_D_USERNAME + " = '" + username + "' AND " + KEY_D_PASSWORD + " = '" + password + "';";
        Log.d("validUser() Query ","rr \"SELECT " + KEY_D_ID + " FROM " + TABLE_DOCTOR + " WHERE " + KEY_D_USERNAME + " = " + username + " AND " + KEY_D_PASSWORD + " = " + password + ";");
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(userQuery, null);

            //looping through all rows and adding to the string
            if(cursor.moveToFirst()){
                do{
                    str += cursor.getString(0) + "\n";
                } while(cursor.moveToNext());
            }
            db.close();
            Log.d("db validUser()","rr cursor: " + cursor + "\n " + str);
        } catch(Exception e){
            Log.d("db validUser()","rr e: "+ e);
        }
        // cannot check if cursor is not null because even if there is no result of the query, cursor will never be null
        // so using cursor, we are getting the values inside the cursor into a string and checking if that string is not null.
        if(str != null){
            return true;
        }
        else {
            Log.d("db validUser()", "rr no such user found: " + username + ", " + password);
            return false;
        }
    }
}