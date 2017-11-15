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
    Validation vd;

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
                Log.d("selected id: ","rr selectedId");
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
                Boolean isValidUsername = vd.checkUsername(username);
                Boolean isValidPassword = vd.checkPassword(password);
                Boolean isValidFName = vd.checkLetters(fname);
                Boolean isValidLName = vd.checkLetters(lname);
                Boolean isValidDepart = vd.checkLetters(department);
                //View object for focus
                View focusView = null;

                if(isValidUsername == false){
                    entusername.setError("Invalid. Must start with a letter & length > 3");
                    Toast.makeText(getApplicationContext(), "Example: 'Rajvi12'", Toast.LENGTH_LONG).show();
                    Log.d("invalid username "," rr = "+ username);
                    focusView = entusername;
                    validUsername = false;
                }
                if(isValidPassword == false){
                    entpassword.setError("Invalid. Length must be > 3");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid password "," rr = "+ password);
                    focusView = entpassword;
                    validPassword = false;
                }
                if(isValidFName == false){
                    entfname.setError("Invalid. Must contain Only letters");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid first name "," rr = "+ fname);
                    focusView = entfname;
                    validFname = false;
                }
                if(isValidLName == false){
                    entlname.setError("Invalid. Must contain Only letters");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid last name ","rr = "+ lname);
                    focusView = entlname;
                    validLname = false;
                }
                if(isValidDepart == false){
                    entdepartment.setError("Invalid. Must contain Only letters");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid department ","rr = "+ department);
                    focusView = entdepartment;
                    validDepart = false;
                }

                if(!validUsername || !validPassword || !validFname || !validLname || !validDepart) {
                    focusView.requestFocus();
                }else{

                    Log.d("values entered", "rr \n username: " + username + " " + password + "\nname: " + fname + lname + "\ndprtmt:  " + department + "type :" + user_type);

                    try {
                        Log.d("database", "rr db : " + db);
                        //calling addNewDoctor method from the DBHandler class
                        db.addNewDoctor(new Doctor(username, password, fname, lname, department, user_type == "Doctor" ? 1 : 0));// if 'add doctor' btn clicked, type = 1
                        Toast.makeText(getApplicationContext(), "Doctor added...", Toast.LENGTH_LONG).show();
                        Log.d("added successfully", "rr \n username: " + username + " " + password + "\nname: " + fname + lname + "\ndprtmt:  " + department + "type :" + user_type);
                        finish();//go back to previous activity. Here, goes to Home Activity
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Database error \n Couldn't add new Doctor", Toast.LENGTH_LONG).show();
                        Log.d("error", "rr method addNewDoctor()");
                    }
                }
            }
        });
    }

}