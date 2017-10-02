package softeng.android.mobile.healthwatch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CheckUpNotification extends AppCompatActivity {

    public static final String EXTRA_REMINDER_ID
            = "CheckUp_ID";

    TextView checkuptitle, checkupdesc, checkupdoctor, checkuplocation, checuptime;
    DBHelper rb;
    private ReminderCheckUp mReceivedReminder;
    private int mReceivedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_up_notification);


        checkuptitle = (TextView) findViewById(R.id.checkuptitle);
        checkupdesc = (TextView) findViewById(R.id.checkupdesc);
        checkupdoctor = (TextView) findViewById(R.id.checkupdoctor);
        checkuplocation = (TextView) findViewById(R.id.checkuplocation);
        checuptime = (TextView) findViewById(R.id.checkup_time);

        mReceivedID = Integer.parseInt(getIntent().getStringExtra(EXTRA_REMINDER_ID));
        // Get reminder using reminder id
        rb = new DBHelper(this);
        mReceivedReminder = rb.getCheckupReminder(mReceivedID);

        String mTitle = mReceivedReminder.getCheckTitle();
        String Desc = mReceivedReminder.getCheckDesc();
        String Doctor = mReceivedReminder.getCheckDoctor();
        String Location = mReceivedReminder.getCheckLocation();
        String Time = mReceivedReminder.getCheckTime();

        checkuptitle.setText("Check Up: " + mTitle);
        checkupdesc.setText("Check Up Description: " + Desc);
        checkupdoctor.setText("Doctor: " + Doctor);
        checkuplocation.setText("Location " + Location);
        checuptime.setText("Time: " + Time);
    }
}
