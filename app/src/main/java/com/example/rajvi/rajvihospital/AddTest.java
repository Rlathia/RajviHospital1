package com.example.rajvi.rajvihospital;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTest extends Activity {

    Button btnAddTest;
    EditText entpatID;
    EditText entbpl;
    EditText entbph;
    EditText enttemp;
    EditText entbsl;
    EditText enthrt;

    DBHandler db;
    Validation vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        db = new DBHandler(this);
        vd = new Validation();

        btnAddTest = (Button) findViewById(R.id.btn_test);

        btnAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entpatID = (EditText) findViewById(R.id.et_patient_id);
                entbpl = (EditText) findViewById(R.id.et_bpl);
                entbph = (EditText) findViewById(R.id.et_bph);
                enttemp = (EditText) findViewById(R.id.et_temperature);
                entbsl = (EditText) findViewById(R.id.et_blood_sugar_level);
                enthrt = (EditText) findViewById(R.id.et_heart_rate);

                String patId = entpatID.getText().toString();
                String bpl = entbpl.getText().toString();
                String bph = entbph.getText().toString();
                String temp = enttemp.getText().toString();
                String bsl = entbsl.getText().toString();
                String hrt = enthrt.getText().toString();

                //View object for focus
                View focusView = null;
                //Boolean variables for validation
                Boolean validPatID = true;
                Boolean validBPL = true;
                Boolean validBPH = true;
                Boolean validTemp = true;
                Boolean validBSL = true;
                Boolean validHRT = true;
                Boolean isValidPatID = vd.checkNumber(patId);
                Boolean isValidHRT = vd.checkNumber(hrt);
                Boolean isValidBSL = vd.checkBSL(bsl);
                Boolean isValidBPL = vd.checkBPL(bpl);
                Boolean isValidBPH = vd.checkBPH(bph);
                Boolean isValidTemp = vd.checkTemp(temp);

                if(isValidPatID == false){
                    entpatID.setError("Invalid. Must contain Only numbers");
                    Log.d("invalid Patient ID","rr = "+ patId);
                    focusView = entpatID;
                    validPatID = false;
                }if(isValidBPL == false){
                    entbpl.setError("Invalid. Must be between 40 and 100");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid BPL","rr = "+ bpl);
                    focusView = entbpl;
                    validBPL = false;
                }if(isValidBPH == false){
                    entbph.setError("Invalid. Must be between 70 and 250");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid BPH","rr = "+ bph);
                    focusView = entbph;
                    validBPH = false;
                }if(isValidTemp == false){
                    enttemp.setError("Invalid. Must be between 70 and 114");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid Temperature","rr = "+ temp);
                    focusView = enttemp;
                    validTemp = false;
                }if(isValidBSL == false){
                    entbsl.setError("Invalid. Must be between 50 and 200");
                    //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
                    Log.d("invalid BSL","rr = "+ bsl);
                    focusView = entbsl;
                    validBSL = false;
                }if(isValidHRT == false){
                    enthrt.setError("Invalid. Must contain Only numbers");
                    Log.d("invalid Heart Rate","rr = "+ hrt);
                    focusView = enthrt;
                    validHRT = false;
                }

                if(!validPatID || !validBPL || !validBPH || !validTemp || !validBSL || !validHRT) {
                focusView.requestFocus();
                }else {
                    // converting string variables into their appropriate data types(as defined in the tables) before adding into database
                    int int_patId = Integer.parseInt(patId);
                    double double_bpl = Double.parseDouble(bpl);
                    double double_bph = Double.parseDouble(bph);
                    double double_temp = Double.parseDouble(temp);
                    double double_bsl = Double.parseDouble(bsl);
                    int int_hrt = Integer.parseInt(hrt);

                    Log.d("values entered", "rr \nPat ID: " + int_patId + "\nbpl:  " + double_bpl + "\nbph: " + double_bph + "\ntemp: " + double_temp + "\nblood sugar level: " + double_bsl + "\nheart rate: " + int_hrt);
                    try {
                        Log.d("database", "rr db : " + db);
                        // calling addNewPatient method from the DBHandler class
                        db.addNewTest(new Test(int_patId, double_bpl, double_bph, double_temp, double_bsl, int_hrt));
                        Toast.makeText(getApplicationContext(), "Test added...", Toast.LENGTH_LONG).show();
                        Log.d("added successfully", "rr \nPat ID: " + int_patId + "\nbpl:  " + double_bpl + "\nbph: " + double_bph + "\ntemp: " + double_temp + "\nblood sugar level: " + double_bsl + "\nheart rate: " + int_hrt);
                        finish(); // go back to previous activity. Here, goes to Home Activity
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Database error \nCouldn't add new Test", Toast.LENGTH_LONG).show();
                        Log.d("error", "rr method addNewTest()");
                    }
                }
            }
        });
    }
}