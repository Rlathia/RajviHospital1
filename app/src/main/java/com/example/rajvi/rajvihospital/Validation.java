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
        Log.d("in checkLetters()", "just entered");
        if (str.matches("[a-zA-Z]*") && str.length() != 0 && str != "") {
            Log.d("in checkLetters()", "correct : " + str);
            return true;
        }
        else
            return false;
    }

    //INPUT must contain only numbers
    public boolean checkNumber(String input){
        Log.d("in checkNumber()", "just entered");
        int num;
        if(!(input == null || input == " ")) {
            num = Integer.parseInt(input);
            if(num > 0){
                Log.d("in checkNumbers()", "correct : " + num);
                return true;
            }
            return false;
        }
        else
            return false;
    }
}
