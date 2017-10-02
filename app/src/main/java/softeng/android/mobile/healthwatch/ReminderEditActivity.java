package softeng.android.mobile.healthwatch;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReminderEditActivity extends AppCompatActivity {

    // Constant Intent String
    public static final String EXTRA_REMINDER_ID = "Medicine_id";
    Button btntake, btnskip;
    String MedicineID;
    String mTitle;
    String mDate;
    String mTime;
    String mRepeat;
    String mRepeatNo;
    String mRepeatType;
    String mActive;
    String mNumberOfMedicine;
    String mVariant;
    String mPrice;
    String mDesc;
    private TextView mTitleText, txtDesc, txtnumberOfmedicine, txtTime;
    private int mReceivedID;
    private Reminder mReceivedReminder;
    private DBHelper rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        mTitleText = (TextView) findViewById(R.id.reminder_title);
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtnumberOfmedicine = (TextView) findViewById(R.id.txtnumberOfmedicine);
        txtTime = (TextView) findViewById(R.id.checkup_time);
        btnskip = (Button) findViewById(R.id.btnskip);
        btntake = (Button) findViewById(R.id.btntake);
        // Setup Reminder Title EditText
        mTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTitle = s.toString().trim();
                mTitleText.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        // Get reminder id from intent
        mReceivedID = Integer.parseInt(getIntent().getStringExtra(EXTRA_REMINDER_ID));
        // Get reminder using reminder id
        rb = new DBHelper(this);
        mReceivedReminder = rb.getReminder(mReceivedID);

        MedicineID = String.valueOf(mReceivedReminder.getID());
        mTitle = mReceivedReminder.getTitle();
        mDate = mReceivedReminder.getDate();
        mTime = mReceivedReminder.getTime();
        mRepeat = mReceivedReminder.getRepeat();
        mRepeatNo = mReceivedReminder.getRepeatNo();
        mRepeatType = mReceivedReminder.getRepeatType();
        mActive = mReceivedReminder.getActive();
        mNumberOfMedicine = mReceivedReminder.getNumberOfMedicine();
        mVariant = mReceivedReminder.getVariant();
        mPrice = mReceivedReminder.getPrice();
        mDesc = mReceivedReminder.getMedicineDesc();

        mTitleText.setText("Medicine Name : " + mTitle);
        txtDesc.setText("Medicine Description : " + mDesc);
        txtnumberOfmedicine.setText("You need to take " + mNumberOfMedicine + " " + mVariant + " of your medicine ");
        txtTime.setText("Time " + mTime);

        btntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int medid = Integer.parseInt(MedicineID);
                Cursor med = rb.selectmedicine(medid);
                if (med.getCount() == 0) {
                    boolean resulted = rb.insertexpenses(MedicineID, mNumberOfMedicine, mPrice);
                    if (resulted == true) {
                        Toast.makeText(ReminderEditActivity.this, "Taked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ReminderEditActivity.this, "Not Taked", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    StringBuffer buffer = new StringBuffer();
                    StringBuffer expenses = new StringBuffer();
                    if (med.moveToNext()) {
                        expenses.append(med.getString(0));
                        buffer.append(med.getString(2));
                    }
                    String intake = buffer.toString();
                    String id = expenses.toString();
                    int totalintake = Integer.parseInt(intake);
                    int tintake = Integer.parseInt(mNumberOfMedicine);

                    totalintake = totalintake + tintake;
                    String alltotalintake = String.valueOf(totalintake);
                    boolean update = rb.updateexpenses(id, alltotalintake);
                    if (update == true) {
                        Toast.makeText(ReminderEditActivity.this, "Taked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ReminderEditActivity.this, "Not Taked", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


}