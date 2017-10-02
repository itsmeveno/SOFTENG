package softeng.android.mobile.healthwatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddDoctor extends AppCompatActivity {


    EditText edtdoctorName, edtSpeciality, edtHospital, edtLocation, edtContact;
    TextView name;
    Button btnadd;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Doctor");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddDoctor.this, Home.class);
                startActivity(i);
            }
        });
        edtdoctorName = (EditText) findViewById(R.id.edtdoctorName);
        edtSpeciality = (EditText) findViewById(R.id.edtSpeciality);
        edtHospital = (EditText) findViewById(R.id.edtHospital);
        edtLocation = (EditText) findViewById(R.id.edtLocation);
        edtContact = (EditText) findViewById(R.id.edtContact);
        btnadd = (Button) findViewById(R.id.btnadd);

        db = new DBHelper(this);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctorName = edtdoctorName.getText().toString();
                String speciality = edtSpeciality.getText().toString();
                String hospital = edtHospital.getText().toString();
                String location = edtLocation.getText().toString();
                String contact = edtContact.getText().toString();
                boolean inserted = db.insertDoctorProfile(doctorName, contact, speciality, hospital, location);
                edtdoctorName.setText("");
                edtSpeciality.setText("");
                edtHospital.setText("");
                edtLocation.setText("");
                edtContact.setText("");
                if (inserted == true) {
                    Toast.makeText(AddDoctor.this, "Doctor inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddDoctor.this, "Doctor not inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
