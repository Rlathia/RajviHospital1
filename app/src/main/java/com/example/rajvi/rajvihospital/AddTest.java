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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);

        db = new DBHandler(this);

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
                int int_patID = Integer.parseInt(patId);
                String bpl = entbpl.getText().toString();
                double double_bpl = Double.parseDouble(bpl);
                String bph = entbph.getText().toString();
                double double_bph = Double.parseDouble(bph);
                String temp = enttemp.getText().toString();
                double double_temp = Double.parseDouble(temp);
                String bsl = entbsl.getText().toString();
                double double_bsl = Double.parseDouble(bsl);
                String hrt = enthrt.getText().toString();
                int int_hrt = Integer.parseInt(hrt);

                Log.d("values entered", "rr \nPat ID: " + int_patID + "\nbpl:  " + double_bpl + "\nbph: " + double_bph + "\ntemp: " + double_temp + "\nblood sugar level: " + double_bsl + "\nheart rate: " + int_hrt);

                try {
                    Log.d("database", "rr db : " + db);
                    //calling addNewPatient method from the DBHandler class
                    db.addNewTest(new Test(int_patID, double_bpl, double_bph, double_temp, double_bsl, int_hrt));
                    Toast.makeText(getApplicationContext(), "Test added...", Toast.LENGTH_LONG).show();
                    Log.d("added successfully", "rr \nPat ID: " + int_patID + "\nbpl:  " + double_bpl + "\nbph: " + double_bph + "\ntemp: " + double_temp + "\nblood sugar level: " + double_bsl + "\nheart rate: " + int_hrt);
                    finish();//go back to previous activity. Here, goes to Home Activity
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Database error \nCouldn't add new Test", Toast.LENGTH_LONG).show();
                    Log.d("error", "rr method addNewTest()");
                }
            }
        });
    }
}