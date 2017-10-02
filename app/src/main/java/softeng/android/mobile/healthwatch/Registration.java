package softeng.android.mobile.healthwatch;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText edtfname, edtlname, edtbirtdaymonth, edtbirtdayday, edtbirtdayyear, edtweight, edtheight, edtbloodtype, edtillness, edtcontactperson, edtcontactNumber;
    RadioGroup gender;
    RadioButton sex;
    Button submit;

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        edtfname = (EditText) findViewById(R.id.edtfname);
        edtlname = (EditText) findViewById(R.id.edtlname);
        gender = (RadioGroup) findViewById(R.id.rdggender);
        edtbirtdaymonth = (EditText) findViewById(R.id.edtbirtdaymonth);
        edtbirtdayday = (EditText) findViewById(R.id.edtbirtdayday);
        edtbirtdayyear = (EditText) findViewById(R.id.edtbirtdayyear);
        edtweight = (EditText) findViewById(R.id.edtweight);
        edtheight = (EditText) findViewById(R.id.edtheight);
        edtbloodtype = (EditText) findViewById(R.id.edtbloodtype);
        edtillness = (EditText) findViewById(R.id.edtillness);
        edtcontactperson = (EditText) findViewById(R.id.edtcontactPerson);
        edtcontactNumber = (EditText) findViewById(R.id.edtcontactNumber);
        submit = (Button) findViewById(R.id.btnsubmit);

        mydb = new DBHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtfname.getText().toString().length() == 0) {
                    edtfname.setError("Please input your firstname..!");
                } else if (edtlname.getText().toString().length() == 0) {
                    edtlname.setError("Please input your lastname!");
                } else if (edtheight.getText().toString().length() == 0) {
                    edtheight.setError("Please input your height!");
                } else if (edtillness.getText().toString().length() == 0) {
                    edtillness.setError("Please input your illness!");
                } else if (edtcontactperson.getText().toString().length() == 0) {
                    edtcontactperson.setError("Please input your contact person!");
                } else if (edtcontactNumber.getText().toString().length() == 0) {
                    edtcontactNumber.setError("Please input your contact number!");
                } else if (edtbloodtype.getText().toString().length() == 0) {
                    edtbloodtype.setError("Please input your blood type!");
                } else {
                    registerPatient();
                }
            }
        });

    }

    private void registerPatient() {
        int selectedId = gender.getCheckedRadioButtonId();
        sex = (RadioButton) findViewById(selectedId);
        String name = edtfname.getText().toString() + " " + edtlname.getText().toString();
        String gender = sex.getText().toString();
        String birthday = edtbirtdaymonth.getText().toString() + "/" + edtbirtdayday.getText().toString() + "/" + edtbirtdayyear.getText().toString();
        String weight = edtweight.getText().toString();
        String height = edtheight.getText().toString();
        String bloodtype = edtbloodtype.getText().toString();
        String illness = edtillness.getText().toString();
        String contactperson = edtcontactperson.getText().toString();
        String contactnumber = edtcontactNumber.getText().toString();

        boolean inserted = mydb.insertPatientProfile(name, gender, birthday, weight, height, bloodtype, illness, contactperson, contactnumber);
        if (inserted == true) {
            Toast.makeText(Registration.this, "Data inserted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(Registration.this, Home.class);
            startActivity(i);
        } else {
            Toast.makeText(Registration.this, "Data not inserted", Toast.LENGTH_LONG).show();
        }
    }
}
