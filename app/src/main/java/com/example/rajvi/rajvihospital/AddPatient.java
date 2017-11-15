package com.example.rajvi.rajvihospital;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPatient extends AppCompatActivity {

    Button btnAddPatient;
    EditText entfname;
    EditText entlname;
    EditText entdepartment;
    EditText entdoctorID;
    EditText entroom;

    DBHandler db;
    Validation vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        db = new DBHandler(this);
        vd = new Validation();

        btnAddPatient = (Button) findViewById(R.id.btn_patient);

        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entfname = (EditText) findViewById(R.id.et_fname);
                entlname = (EditText) findViewById(R.id.et_lname);
                entdepartment = (EditText) findViewById(R.id.et_department);
                entdoctorID = (EditText) findViewById(R.id.et_doctor_id);
                entroom = (EditText) findViewById(R.id.et_room);

                String doctorId = entdoctorID.getText().toString();
                String fname = entfname.getText().toString();
                String lname = entlname.getText().toString();
                String department = entdepartment.getText().toString();
                String room = entroom.getText().toString();
                //View object for focus
                View focusView = null;
                //Boolean variables for validation
                Boolean validFname = true;
                Boolean validLname = true;
                Boolean validDepart = true;
                Boolean validRoom = true;
                Boolean  validDocID = true;
                Boolean isValidRoom = vd.checkUsername(room);
                Boolean isValidFName = vd.checkLetters(fname);
                Boolean isValidLName = vd.checkLetters(lname);
                Boolean isValidDepart = vd.checkLetters(department);
                Boolean isValidDocID = vd.checkNumber(doctorId);

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
                }if(isValidRoom == false){
                    entroom.setError("Invalid. Must start with letters and followed by Numbers");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid room ","rr = "+ room);
                    focusView = entroom;
                    validRoom = false;
                }if(isValidDocID == false){
                    entdoctorID.setError("Invalid. Must contain Only numbers");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid doctor ID ","rr = "+ doctorId);
                    focusView = entdoctorID;
                    validDocID = false;
                }

                if(!validDocID || !validRoom || !validFname || !validLname || !validDepart) {
                    focusView.requestFocus();
                }else{
                    int num = Integer.parseInt(doctorId);
                    Log.d("values entered", "\nname: " + fname + lname + "\ndprtmt:  " + department + "\ndoc ID: " + num + "\nroom: " + room);

                    try {
                        Log.d("database" , "db : " + db);
                        //calling addNewPatient method from the DBHandler class
                        db.addNewPatient(new Patient(fname, lname, department, num, room));
                        Toast.makeText(getApplicationContext(), "Patient added...", Toast.LENGTH_LONG).show();
                        Log.d("added successfully", "\nname: " + fname + lname + "\ndprtmt:  " + department + "\ndoc ID: " + num + "\nroom: " + room);
                        finish();//go back to previous activity. Here, goes to Home Activity
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Database error \n Couldn't add new Patient", Toast.LENGTH_LONG).show();
                        Log.d("error", "method addNewPatient()");
                    }
                }
            }
        });
    }
}
