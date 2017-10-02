package softeng.android.mobile.healthwatch;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PatientProfile extends AppCompatActivity {

    TextView name, gender, birthday, age, weight, height, bmi, bloodtype, illness, contactperson, contactnumber;
    ImageButton edit;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Profile");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PatientProfile.this, Home.class);
                startActivity(i);
            }
        });

        name = (TextView) findViewById(R.id.txtname);
        gender = (TextView) findViewById(R.id.txtgender);
        birthday = (TextView) findViewById(R.id.txtbirthday);
        age = (TextView) findViewById(R.id.txtage);
        weight = (TextView) findViewById(R.id.txtweight);
        height = (TextView) findViewById(R.id.txtheight);
        bmi = (TextView) findViewById(R.id.txtbmi);
        bloodtype = (TextView) findViewById(R.id.txtbloodtype);
        illness = (TextView) findViewById(R.id.txtillness);
        contactperson = (TextView) findViewById(R.id.txtcontactperson);
        contactnumber = (TextView) findViewById(R.id.txtcontactnumber);
        edit = (ImageButton) findViewById(R.id.btnedit);
        mydb = new DBHelper(this);

        Cursor res = mydb.patientName();
        StringBuffer sbname = new StringBuffer(), sbgender = new StringBuffer(), sbbirthday = new StringBuffer(), sbweight = new StringBuffer(), sbheight = new StringBuffer(), sbbloodtype = new StringBuffer(), sbilness = new StringBuffer(), sbcontactperson = new StringBuffer(), sbcontactnumber = new StringBuffer();
        while (res.moveToNext()) {
            sbname.append(res.getString(1));
            sbgender.append(res.getString(2));
            sbbirthday.append(res.getString(3));
            sbweight.append(res.getString(4));
            sbheight.append(res.getString(5));
            sbbloodtype.append(res.getString(6));
            sbilness.append(res.getString(7));
            sbcontactperson.append(res.getString(8));
            sbcontactnumber.append(res.getString(9));
        }

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c.getTime());

        Double d_bmi;
        Double d_weight = Double.parseDouble(sbweight.toString());
        Double d_height = Double.parseDouble(sbheight.toString());

        d_bmi = (d_weight / d_height / d_height) * 10000;
        DecimalFormat df2 = new DecimalFormat("###.##");
        Double dd_bmi = Double.valueOf(df2.format(d_bmi));
        String s_bmi = Double.toString(dd_bmi);

        String bdate = sbbirthday.toString();
        String cdate = formattedDate;
        String bdateresult = bdate.substring(bdate.lastIndexOf("/") + 1);
        String cdateresult = cdate.substring(cdate.lastIndexOf("/") + 1);

        int iage, ibdate, icdate;
        ibdate = Integer.parseInt(bdateresult);
        icdate = Integer.parseInt(cdateresult);
        iage = icdate - ibdate;
        String s_age = Integer.toString(iage);

        name.setText("Name: " + sbname.toString());
        gender.setText("Gender: " + sbgender.toString());
        age.setText("Age: " + s_age);
        birthday.setText("Birthday: " + sbbirthday.toString());
        weight.setText("Weight: " + sbweight.toString());
        height.setText("Height: " + sbheight.toString());
        bmi.setText("BMI: " + s_bmi);
        bloodtype.setText("Blood Type: " + sbbloodtype.toString());
        illness.setText("Illness: " + sbilness.toString());
        contactperson.setText("Contact Person: " + sbcontactperson.toString());
        contactnumber.setText("Contact Number: " + sbcontactnumber.toString());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PatientProfile.this, EditProfile.class);
                startActivity(i);
            }
        });
    }
}
