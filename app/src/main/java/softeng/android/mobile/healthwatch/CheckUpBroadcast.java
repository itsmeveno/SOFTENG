package softeng.android.mobile.healthwatch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.Calendar;
import java.util.List;

public class CheckUpBroadcast extends BroadcastReceiver {

    Uri ringtone;
    private String cTime;
    private String cDate;
    private String cActive;
    private String[] cDateSplit;
    private String[] cTimeSplit;
    private int mYear, mMonth, mHour, mMinute, mDay, cCheckUpRecievedID;

    private Calendar mCalendar;
    private CheckupAlarmReciever cAlarmReceiver;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            DBHelper rb = new DBHelper(context);
            mCalendar = Calendar.getInstance();
            cAlarmReceiver = new CheckupAlarmReciever();

            List<ReminderCheckUp> checkupreminder = rb.getAllCheckupReminders();
            for (ReminderCheckUp crm : checkupreminder) {
                cCheckUpRecievedID = crm.getCheckID();
                cActive = crm.getCheckActive();
                cDate = crm.getCheckDate();
                cTime = crm.getCheckTime();

                cDateSplit = cDate.split("/");
                cTimeSplit = cTime.split(":");

                mDay = Integer.parseInt(cDateSplit[0]);
                mMonth = Integer.parseInt(cDateSplit[1]);
                mYear = Integer.parseInt(cDateSplit[2]);
                mHour = Integer.parseInt(cTimeSplit[0]);
                mMinute = Integer.parseInt(cTimeSplit[1]);

                mCalendar.set(Calendar.MONTH, --mMonth);
                mCalendar.set(Calendar.YEAR, mYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
                mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
                mCalendar.set(Calendar.MINUTE, mMinute);
                mCalendar.set(Calendar.SECOND, 0);

                if (cActive.equals("true")) {
                    cAlarmReceiver.setAlarm(context, mCalendar, cCheckUpRecievedID, ringtone);
                }
            }
        }
    }
}
