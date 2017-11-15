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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        db = new DBHandler(this);

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
                int int_doctorID = Integer.parseInt(doctorId);
                String fname = entfname.getText().toString();
                String lname = entlname.getText().toString();
                String department = entdepartment.getText().toString();
                String room = entroom.getText().toString();

                Log.d("values entered", "\nname: " + fname + lname + "\ndprtmt:  " + department + "\ndoc ID: " + int_doctorID + "\nroom: " + room);

                try {
                    Log.d("database" , "db : " + db);
                    //calling addNewPatient method from the DBHandler class
                    db.addNewPatient(new Patient(fname, lname, department, int_doctorID, room));
                    Toast.makeText(getApplicationContext(), "Patient added...", Toast.LENGTH_LONG).show();
                    Log.d("added successfully", "\nname: " + fname + lname + "\ndprtmt:  " + department + "\ndoc ID: " + int_doctorID + "\nroom: " + room);
                    finish();//go back to previous activity. Here, goes to Home Activity
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Database error \n Couldn't add new Patient", Toast.LENGTH_LONG).show();
                    Log.d("error", "method addNewPatient()");
                }
            }
        });
    }
}
