package softeng.android.mobile.healthwatch;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddCheckUpSchedule extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    // Values for orientation change
    private static final String KEY_TITLE = "title_key";
    private static final String KEY_CHECKUPDESC = "checkupdesk_key";
    private static final String KEY_CHECKUPDOCTOR = "doctor_key";
    private static final String KEY_CHECKUPDATE = "date_key";
    private static final String KEY_CHECKUPTIME = "time_key";
    private static final String KEY_CHECKUPLOCATION = "location_key";
    private static final String KEY_CHECKUPACTIVE = "active_key";
    // Constant values in milliseconds
    private static final long milMinute = 60000L;
    private static final long milHour = 3600000L;
    private static final long milDay = 86400000L;
    private static final long milWeek = 604800000L;
    private static final long milMonth = 2592000000L;
    Uri ringtone;
    EditText CheckupTitle, CheckUpDesc, Doctor, Location;
    TextView txtTime, txtDate;
    Button saveSchedule;
    private Calendar mCalendar;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String mDate;
    private String mTime;
    private String mActive;
    private String cTitle;
    private String cDesc;
    private String cDoctor;
    private String cLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_check_up_schedule);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        setTitle("Add Check Up Schedule");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddCheckUpSchedule.this, Home.class);
                startActivity(i);
            }
        });
        CheckupTitle = (EditText) findViewById(R.id.edtCheckup);
        CheckUpDesc = (EditText) findViewById(R.id.reminder_desc);
        Doctor = (EditText) findViewById(R.id.edtDoctor);
        Location = (EditText) findViewById(R.id.edtLocation);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtDate = (TextView) findViewById(R.id.txtDate);
        saveSchedule = (Button) findViewById(R.id.addcheckupschedule);
        mActive = "true";

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

        mDate = mDay + "/" + mMonth + "/" + mYear;
        mTime = mHour + ":" + mMinute;

        txtTime.setText("Time: " + mTime);
        txtDate.setText("Date: " + mDate);
        saveSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckupTitle.getText().toString().length() == 0)
                    CheckupTitle.setError("Reminder Title cannot be blank!");
                else {
                    saveReminder();
                }
                mActive = "false";
            }
        });
    }

    public void setDate(View v) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    public void setTime(View v) {
        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setThemeDark(false);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear++;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        mYear = year;
        mDate = dayOfMonth + "/" + monthOfYear + "/" + year;
        txtDate.setText("Date: " + mDate);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mHour = hourOfDay;
        mMinute = minute;
        if (minute < 10) {
            mTime = hourOfDay + ":" + "0" + minute;
        } else {
            mTime = hourOfDay + ":" + minute;
        }
        txtTime.setText("Time: " + mTime);
    }

    public void ringtone(View v) {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, ringtone);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI, ringtone);
        startActivityForResult(intent, 1);
    }

    public void saveReminder() {
        DBHelper rb = new DBHelper(this);
        cTitle = CheckupTitle.getText().toString();
        cDesc = CheckUpDesc.getText().toString();
        cDoctor = Doctor.getText().toString();
        cLocation = Location.getText().toString();

        // Creating Reminder
        int ID = rb.addCheckUpReminder(new ReminderCheckUp(cTitle, cDesc, cDoctor, cLocation, mTime, mDate, mActive));
        // Set up calender for creating the notification
        // Create a new notification
        mCalendar.set(Calendar.MONTH, --mMonth);
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        new AlarmReceiver().setAlarm(getApplicationContext(), mCalendar, ID, ringtone);

        // Create toast to confirm new reminder
        Toast.makeText(getApplicationContext(), "Saved",
                Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_TITLE, CheckupTitle.getText());
        outState.putCharSequence(KEY_CHECKUPDESC, CheckUpDesc.getText());
        outState.putCharSequence(KEY_CHECKUPDOCTOR, Doctor.getText());
        outState.putCharSequence(KEY_CHECKUPLOCATION, Location.getText());
        outState.putCharSequence(KEY_CHECKUPTIME, txtTime.getText());
        outState.putCharSequence(KEY_CHECKUPDATE, txtDate.getText());
        outState.putCharSequence(KEY_CHECKUPACTIVE, mActive);
    }

}