package softeng.android.mobile.healthwatch;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {

    TextView name, gender, birthday, bloodtype, illness;
    EditText weight, height, contactperson, contactnumber;
    ImageButton save;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Edit Profile");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditProfile.this, PatientProfile.class);
                startActivity(i);
            }
        });
        name = (TextView) findViewById(R.id.txtname);
        gender = (TextView) findViewById(R.id.txtgender);
        birthday = (TextView) findViewById(R.id.txtbirthday);
        bloodtype = (TextView) findViewById(R.id.txtbloodtype);
        illness = (TextView) findViewById(R.id.txtillness);

        weight = (EditText) findViewById(R.id.edtweight);
        height = (EditText) findViewById(R.id.edtheight);
        contactperson = (EditText) findViewById(R.id.edtcontactperson);
        contactnumber = (EditText) findViewById(R.id.edtcontactnumber);

        save = (ImageButton) findViewById(R.id.btnsave);
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
        name.setText(sbname.toString());
        gender.setText(sbgender.toString());
        birthday.setText(sbbirthday.toString());
        bloodtype.setText(sbbloodtype.toString());
        illness.setText(sbilness.toString());

        weight.setText(sbweight.toString());
        height.setText(sbheight.toString());
        contactperson.setText(sbcontactperson.toString());
        contactnumber.setText(sbcontactnumber.toString());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String w = weight.getText().toString();
                String h = height.getText().toString();
                String cp = contactperson.getText().toString();
                String cn = contactnumber.getText().toString();

                mydb.updateprofile("1", w, h, cp, cn);
                Intent i = new Intent(EditProfile.this, PatientProfile.class);
                startActivity(i);
            }
        });
    }
}
