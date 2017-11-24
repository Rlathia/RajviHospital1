package com.example.rajvi.rajvihospital;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class AddPatient extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnAddPatient;
    EditText entfname;
    EditText entlname;
    EditText entdepartment;
    EditText entroom;
    Spinner entdoc;
    DBHandler db;
    Validation vd;
    String selectedDocID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        db = new DBHandler(this);
        vd = new Validation();
        entfname = (EditText) findViewById(R.id.et_fname);
        entlname = (EditText) findViewById(R.id.et_lname);
        entdepartment = (EditText) findViewById(R.id.et_department);
        entroom = (EditText) findViewById(R.id.et_room);
        entdoc = (Spinner) findViewById(R.id.et_doc);
        btnAddPatient = (Button) findViewById(R.id.btn_patient);
        // Spinner click listener
        entdoc.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        // Loading spinner data from database
        loadSpinnerData();

        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname = entfname.getText().toString();
                String lname = entlname.getText().toString();
                String department = entdepartment.getText().toString();
                String room = entroom.getText().toString();
                //View object for focus, used for validation errors
                View focusView = null;
                //Boolean variables for validation
                Boolean validFname = true, validLname = true, validDepart = true, validRoom = true;
                Boolean isValidRoom = vd.checkUsername(room);
                Boolean isValidFName = vd.checkLetters(fname);
                Boolean isValidLName = vd.checkLetters(lname);
                Boolean isValidDepart = vd.checkLetters(department);

                if(isValidFName == false){
                    entfname.setError(getString(R.string.error_only_letters));
                    Log.d("invalid first name "," rr = "+ fname);
                    focusView = entfname;
                    validFname = false;
                }
                if(isValidLName == false){
                    entlname.setError(getString(R.string.error_only_letters));
                    Log.d("invalid last name ","rr = "+ lname);
                    focusView = entlname;
                    validLname = false;
                }
                if(isValidDepart == false){
                    entdepartment.setError(getString(R.string.error_only_letters));
                    Log.d("invalid department ","rr = "+ department);
                    focusView = entdepartment;
                    validDepart = false;
                }
                if(isValidRoom == false){
                    entroom.setError(getString(R.string.error_letters_numbers));
                    Log.d("invalid room ","rr = "+ room);
                    focusView = entroom;
                    validRoom = false;
                }

                if(!validRoom || !validFname || !validLname || !validDepart) {
                    focusView.requestFocus();
                }else{
                    //converting string 'selectedDocID' into integer 'num' before adding into database
                    int num = Integer.parseInt(selectedDocID);

                    try {
                        Log.d("database" , "rr db : " + db);
                        //calling addNewPatient method from the DBHandler class
                        db.addNewPatient(new Patient(fname, lname, department, num, room));
                        Toast.makeText(getApplicationContext(), "Patient added", Toast.LENGTH_LONG).show();
                        Log.d("added successfully", "rr \nname: " + fname + lname + "\ndprtmt:  " + department + "\ndoc ID: " + num + "\nroom: " + room);
                        finish();//go back to previous activity. Here, goes to Home Activity
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Database error \n Couldn't add new Patient", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String[] selectedDocArray =  adapterView.getSelectedItem().toString().split(":");
        selectedDocID = selectedDocArray[0];
        Log.d("selected doctor:", adapterView.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        selectedDocID = "1";
    }

    private void loadSpinnerData(){

        List<String> doctorList = db.getDoctorNameList();
        // Creating adapter for doctor spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, doctorList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        entdoc.setAdapter(dataAdapter);
    }
}
