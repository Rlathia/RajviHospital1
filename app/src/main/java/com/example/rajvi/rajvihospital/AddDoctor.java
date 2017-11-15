package com.example.rajvi.rajvihospital;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddDoctor extends AppCompatActivity {

    //UI objects
    Button btnAddDoctor;
    EditText entfname;
    EditText entlname;
    EditText entdepartment;
    EditText entusername;
    EditText entpassword;
    RadioGroup enttype;
    RadioButton rbtn;
    String user_type;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        db = new DBHandler(this);

        entusername = (EditText) findViewById(R.id.et_doc_username);
        entpassword = (EditText) findViewById(R.id.et_doc_password);
        entfname = (EditText) findViewById(R.id.et_fname);
        entlname = (EditText) findViewById(R.id.et_lname);
        entdepartment = (EditText) findViewById(R.id.et_department);
        enttype = (RadioGroup) findViewById(R.id.radio_type);
        btnAddDoctor = (Button) findViewById(R.id.btn_doctor);
        //to store default selection
        int selectedId = enttype.getCheckedRadioButtonId();
        rbtn = (RadioButton) findViewById(selectedId);
        user_type = rbtn.getText().toString();

        enttype.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int selectedId = enttype.getCheckedRadioButtonId();
                Log.d("rr seelcted id: ","selectedId");
                rbtn = (RadioButton) findViewById(selectedId);
                user_type = rbtn.getText().toString();
            }
        });

        btnAddDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Reset errors
                entusername.setError(null);
                entpassword.setError(null);
                entfname.setError(null);
                entlname.setError(null);
                entdepartment.setError(null);

                String fname = entfname.getText().toString();
                String lname = entlname.getText().toString();
                String department = entdepartment.getText().toString();
                String username = entusername.getText().toString();
                String password = entpassword.getText().toString();
                //Boolean variables for validation
                Boolean validUsername = true;
                Boolean validPassword = true;
                Boolean validFname = true;
                Boolean validLname = true;
                Boolean validDepart = true;
                Boolean isValidUsername = checkUsername(username);
                Boolean isValidPassword = checkPassword(password);
                Boolean isValidFName = checkLetters(fname);
                Boolean isValidLName = checkLetters(lname);
                Boolean isValidDepart = checkLetters(department);
                //View object for focus
                View focusView = null;

                if(isValidUsername == false){
                    entusername.setError("Invalid. Must start with a letter & length > 3");
                    Toast.makeText(getApplicationContext(), "Example: 'Rajvi12'", Toast.LENGTH_LONG).show();
                    Log.d("entered username ","= "+ username);
                    focusView = entusername;
                    validUsername = false;
                }
                if(isValidPassword == false){
                    entpassword.setError("Required");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("entered password ","= "+ password);
                    focusView = entpassword;
                    validPassword = false;
                }
                if(isValidFName == false){
                    entfname.setError("Invalid. Only letters");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("entered first name ","= "+ fname);
                    focusView = entfname;
                    validFname = false;
                }
                if(isValidLName == false){
                    entlname.setError("Invalid. Only letters");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("entered last name ","= "+ lname);
                    focusView = entlname;
                    validLname = false;
                }
                if(isValidDepart == false){
                    entdepartment.setError("Invalid. Only letters");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("entered department ","= "+ department);
                    focusView = entdepartment;
                    validDepart = false;
                }

                if(!validUsername || !validPassword || !validFname || !validLname || !validDepart) {
                    focusView.requestFocus();
                }else{

                    Log.d("values entered", "rr \n username: " + username + " " + password + "\nname: " + fname + lname + "\ndprtmt:  " + department + "type :" + user_type);

                    try {
                        Log.d("database", "db : " + db);
                        //calling addNewDoctor method from the DBHandler class
                        db.addNewDoctor(new Doctor(username, password, fname, lname, department, user_type == "Doctor" ? 1 : 0));// if 'add doctor' btn clicked, type = 1
                        Toast.makeText(getApplicationContext(), "Doctor added...", Toast.LENGTH_LONG).show();
                        Log.d("added successfully", "rr \n username: " + username + " " + password + "\nname: " + fname + lname + "\ndprtmt:  " + department + "type :" + user_type);
                        finish();//go back to previous activi"ty. Here, goes to Home Activity
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Database error \n Couldn't add new Doctor", Toast.LENGTH_LONG).show();
                        Log.d("error", "rr method addNewDoctor()");
                    }
                }
            }
        });
    }

    /* USERNAME should contain letters and numbers both
       USERNAME must be greater than 3 characters long*/
    public boolean checkUsername(String username) {
        if ( username == "" || !(username.matches("[a-zA-Z][a-zA-Z0-9]{3,}")) )
            return false;
        else
            return true;
    }
    //PASSWORD cannot be null
    public boolean checkPassword(String password) {
        if ( password != "" && password.length() != 0 )
            return true;
        else
            return false;
    }
    // STRING must contain letters only and cannot be null
    public boolean checkLetters(String str){
        Log.d("in checkLetters()", "just entered");
        if (str.matches("[a-zA-Z]*") && str.length() != 0 && str != "") {
            Log.d("in checkLetters()", "correct : " + str);
            return true;
        }
        else
            return false;
    }
}