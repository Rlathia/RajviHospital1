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

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends Activity {

    Button btnAddPatient, btnAddTest, btnShowPatient, btnShowTest, btnAddDoctor, btnSignOut;
    TextView tvPatientList, tvTestList, tvTitle;
    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHandler(this);

        tvTitle = (TextView) findViewById(R.id.textView_home_page);
        btnAddPatient = (Button) findViewById(R.id.btn_add_patient);
        btnAddTest = (Button) findViewById(R.id.btn_add_test);
        btnShowPatient = (Button) findViewById(R.id.btn_show_patient);
        btnShowTest = (Button) findViewById(R.id.btn_show_test);
        btnAddDoctor = (Button) findViewById(R.id.btn_add_doctor);
        btnSignOut = (Button) findViewById(R.id.btn_sign_out);

        final Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        tvTitle.setText("Welcome " + bundle.getString("userLoggedIn"));

        //Sign Out button click Listener
        btnSignOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Sign Out button" , "rr clicked");
                finish();

            }
        });
        // Add Patient button click listener
        btnAddPatient.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("add patient button ", "rr clicked");
                Intent intent = new Intent(MainActivity.this, AddPatient.class);
                Log.d("'Add Patient' intent ", "rr created");
                startActivity(intent);
            }
        });

        // Add Test button click listener
        btnAddTest.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTest.class);
                Log.d("'Add Test' intent ", "rr created");
                startActivity(intent);
            }
        });

        // Add Doctor button click listener
        btnAddDoctor.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("add doctor button ", "rr clicked");
                Intent intent = new Intent(MainActivity.this, AddDoctor.class);
                Log.d("'Add Doctor' intent ", "rr created");
                startActivity(intent);
            }
        });

        //Show Patient button click listener
        btnShowPatient.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("show patient button ", "rr clicked");
                // VIEW BLOCK NUMBER LIST IN THE TextView Widget
                tvPatientList = (TextView)findViewById(R.id.textView_patient_list);
                tvPatientList.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvPatientList.setText(""); // clear text area at each button press

                tvPatientList.setPadding(5,2,5,2);

                List<Patient> patientList = db.getPatientList(); // fetch list of patients
                Log.d("List fetched", "rr database patient list fetched");
                for(Patient pat : patientList){
                    String patDetail = "\nPatient ID: " + pat.getId() + "\n\tName: " + pat.getFname() + " " + pat.getLname() + "\n\tDepartment:  " + pat.getDepartment() + "\n\tDoctor ID: " + pat.getDoctorID() + "\n\tRoom No: " + pat.getRoom() + "";
                    tvPatientList.append("\n" + patDetail);
                }
            }
        });

        // Show Test button click listener
        btnShowTest.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("show test button ", "rr clicked");
                // VIEW BLOCK NUMBER LIST in the TextView Widget
                tvTestList = (TextView)findViewById(R.id.textView_test_list);
                tvTestList.setMovementMethod(ArrowKeyMovementMethod.getInstance());

                tvTestList.setText(""); //clear text area at each button press

                tvTestList.setPadding(5,2,5,2);

                List<Test> testList = db.getTestList(); //fetch list of tests
                Log.d("List fetched", "rr database test list fetched");
                for(Test tst : testList){
                    String testDetail = "\nTest ID: " + tst.getTestID() + "\n\tPatient ID: " + tst.getPatientID() + "\n\tbph / bpl: " + tst.getBph() + " / " + tst.getBpl() + "\n\tTemperature:  " + tst.getTemp() + "\n\tBlood Sugar Level: " + tst.getBsl() + "\n\tHeart Rate: " + tst.getHrt() + "";
                    tvTestList.append("\n" + testDetail);
                }
            }
        });
    }
}