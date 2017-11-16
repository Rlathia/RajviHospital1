package com.example.rajvi.rajvihospital;

import android.util.Log;

/**
 * Created by Rajvi on 15-Nov-2017.
 */

public class Validation {

    //empty constructor
    public Validation(){

    }
    /* USERNAME should contain letters and numbers both
   USERNAME must be greater than 3 characters long*/
    public boolean checkUsername(String username) {
        if ( username == "" || !(username.matches("[a-zA-Z][a-zA-Z0-9]{3,}")) )
            return false;
        else
            return true;
    }
    //PASSWORD cannot be null and must be greater than 3 characters long
    public boolean checkPassword(String password) {
        if ( password != "" && password.length() > 3 )
            return true;
        else
            return false;
    }
    //INPUT must contain letters only and cannot be null
    public boolean checkLetters(String str){
        Log.d("in checkLetters()", "rr just entered");
        if (str.matches("[a-zA-Z]*") && str.length() != 0 && str != "") {
            Log.d("in checkLetters()", "rr correct : " + str);
            return true;
        }
        else
            return false;
    }

    //INPUT must contain only numbers
    public boolean checkNumber(String input){
        Log.d("in checkNumber()", "rr just entered");
        int num;
        if(!(input == null)) {
            Log.d("number entered ","rr" + input);
            // try catch block for converting string into number.
            // If Integer.parseInt(A23D), then NumberFormatException will be thrown and app will stop if not caught.
            try {
                num = Integer.parseInt(input);
            }catch(NumberFormatException e){
                return false;
            }
            if(num > 0){
                Log.d("in checkNumbers()", "rr correct : " + num);
                return true;
            }
            return false;
        }
        else
            return false;
    }
    //INPUT blood sugar level must be between 50 mg/dL to 200 mg/dL
    public boolean checkBSL(String input){
        Log.d("in checkBSL()", "rr just entered");
        double bsl;
        if(!(input == null)) {
            Log.d("BSL entered ","rr" + input);
            // try catch block for converting string into number.
            // If Integer.parseInt(A23D), then NumberFormatException will be thrown and app will stop if not caught.
            try {
                bsl = Double.parseDouble(input);
            }catch(NumberFormatException e){
                return false;
            }
            if(bsl > 50 && bsl < 200){
                Log.d("in checkBSL()", "rr correct : " + bsl);
                return true;
            }
            return false;
        }
        else
            return false;
    }

    //INPUT blood pressure: Diastolic must be between 40 and 100 mmHg
    public boolean checkBPL(String input){
        Log.d("in checkBPL()", "rr just entered");
        double bpl;
        if(!(input == null)) {
            Log.d("BPL entered ","rr" + input);
            // try catch block for converting String into Number.
            // If Double.parseDouble(A23D), then NumberFormatException will be thrown and app will stop if not caught.
            try {
                bpl = Double.parseDouble(input);
            }catch(NumberFormatException e){
                return false;
            }
            if(bpl > 40 && bpl < 100){
                Log.d("in checkBPL()", "rr correct : " + bpl);
                return true;
            }
            return false;
        }
        else
            return false;
    }
    //INPUT blood pressure : systolic must be between 70 to 250 mmHg
    public boolean checkBPH(String input){
        Log.d("in checkBPH()", "rr just entered");
        double bph;
        if(!(input == null)) {
            Log.d("BPH entered ","rr" + input);
            // try catch block for converting String into Number.
            // If Double.parseDouble(A23D), then NumberFormatException will be thrown and app will stop if not caught.
            try {
                bph = Double.parseDouble(input);
            }catch(NumberFormatException e){
                return false;
            }
            if(bph > 70 && bph < 250){
                Log.d("in checkBPH()", "rr correct : " + bph);
                return true;
            }
            return false;
        }
        else
            return false;
    }

    //INPUT Temperature must be between 70 and 114 degree F
    public boolean checkTemp(String input){
        Log.d("in checkTemp()", "rr just entered");
        double temp;
        if(!(input == null)) {
            Log.d("Temperature entered ","rr" + input);
            // try catch block for converting String into Number.
            // If Double.parseDouble(A23D), then NumberFormatException will be thrown and app will stop if not caught.
            try {
                temp = Double.parseDouble(input);
            }catch(NumberFormatException e){
                return false;
            }
            if(temp > 70 && temp < 114){
                Log.d("in checkTemp()", "rr correct : " + temp);
                return true;
            }
            return false;
        }
        else
            return false;
    }
}