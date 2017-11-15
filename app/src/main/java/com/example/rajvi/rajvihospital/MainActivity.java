package com.example.rajvi.rajvihospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ArrowKeyMovementMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {

    Button btnAddPatient, btnAddTest, btnShowPatient, btnShowTest, btnAddDoctor;
    TextView tvPatientList;
    TextView tvTestList;
    private String TAG = "patientInfo";
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);

        btnAddPatient = (Button) findViewById(R.id.btn_add_patient);
        btnAddTest = (Button) findViewById(R.id.btn_add_test);
        btnShowPatient = (Button) findViewById(R.id.btn_show_patient);
        btnShowTest = (Button) findViewById(R.id.btn_show_test);
        btnAddDoctor = (Button) findViewById(R.id.btn_add_doctor);

        btnAddPatient.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("add patient button ", "rr clicked");
                Intent intent = new Intent(MainActivity.this, AddPatient.class);
                Log.d("'Add Patient' intent ", "rr created");
                startActivity(intent);
            }
        });
        btnAddTest.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTest.class);
                Log.d("'Add Test' intent ", "rr created");
                startActivity(intent);
            }
        });
        btnAddDoctor.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("add doctor button ", "rr clicked");
                Intent intent = new Intent(MainActivity.this, AddDoctor.class);
                Log.d("'Add Doctor' intent ", "rr created");
                startActivity(intent);
            }
        });
//        btnShowPatient.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddPatient.class);
//                startActivity(intent);
//            }
//        });
        btnShowPatient.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("show patient button ", "rr clicked");
                //VIEW BLOCK NUMBER LIST IN THE TextView Widget
                tvPatientList = (TextView)findViewById(R.id.textView_patient_list);
                tvPatientList.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvPatientList.setText("");//clear text area at each button press

                tvPatientList.setPadding(5,2,5,2);

                List<Patient> patientList = db.getPatientList(); //fetch list of patients
                Log.d("List fetched", "rr database patient list fetched");
                for(Patient pat : patientList){
                    String patDetail = "\nPatient ID: " + pat.getId() + "\n\tName: " + pat.getFname() + " " + pat.getLname() + "\n\tDepartment:  " + pat.getDepartment() + "\n\tDoctor ID: " + pat.getDoctorID() + "\n\tRoom No: " + pat.getRoom() + "";
                    tvPatientList.append("\n" + patDetail);
                }
            }
        });

        btnShowTest.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("show test button ", "rr clicked");
                //VIEW BLOCK NUMBER LIST IN THE TextView Widget
                tvTestList = (TextView)findViewById(R.id.textView_test_list);
                tvTestList.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvTestList.setText("");//clear text area at each button press

                tvTestList.setPadding(5,2,5,2);

                List<Test> testList = db.getTestList(); //fetch list of tests
                Log.d("List fetched", "rr database test list fetched");
                for(Test tst : testList){
                    String testDetail = "\nTest ID: " + tst.getTestID() + "\n\tPatient ID: " + tst.getPatientID() + "\n\tbph & bph: " + tst.getBph() + " " + tst.getBpl() + "\n\tTemperature:  " + tst.getTemp() + "\n\tBlood sugar level: " + tst.getBsl() + "\n\tHeart rate: " + tst.getHrt() + "";
                    tvTestList.append("\n" + testDetail);
                }
            }
        });
    }
}