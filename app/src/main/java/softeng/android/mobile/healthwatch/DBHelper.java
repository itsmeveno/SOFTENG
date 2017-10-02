package softeng.android.mobile.healthwatch;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String databaseName = "healthwatchDB";
    public static final String tblPatientProfile = "tblPatientProfile";
    public static final String tblDoctorProfile = "tblDoctorProfile";
    public static final String tblMedicine = "tblMedicine";
    public static final String tblExpenses = "tblExpenses";
    public static final String tblCheckUpSchedule = "tblCheckUpSchedule";

    private static final String KEY_ID = "Medicine_id";
    private static final String KEY_TITLE = "Medicine_Name";
    private static final String KEY_DATE = "Date";
    private static final String KEY_TIME = "Time";
    private static final String KEY_REPEAT = "Repeat";
    private static final String KEY_REPEAT_NO = "Repeat_no";
    private static final String KEY_REPEAT_TYPE = "Repeat_type";
    private static final String KEY_ACTIVE = "Active";
    private static final String KEY_NUMBEROFMEDICINE = "NumberOfMedicine";
    private static final String KEY_VARIANTS = "Variant";
    private static final String KEY_PRICE = "Price";
    private static final String KEY_MDESC = "Medicine_Desc";

    private static final String KEY_CHECKUPID = "CheckUp_ID";
    private static final String KEY_CHECKUPTITLE = "CheckUp_Title";
    private static final String KEY_CHECKUPDESC = "CheckUp_Desc";
    private static final String KEY_CHECKUPDOCTOR = "Doctor_ID";
    private static final String KEY_CHECKUPDATE = "Date";
    private static final String KEY_CHECKUPTIME = "Time";
    private static final String KEY_CHECKUPLOCATION = "Location";
    private static final String KEY_CHECKUPACTIVE = "Active";

    private static final String KEY_EXPENSESID = "Expenses_ID";
    private static final String KEY_MEDICINEID = "Medicine_ID";
    private static final String KEY_MEDICINENUMBEROFINTAKE = "Intake_Medicine";
    private static final String KEY_MEDICINEPRICE = "Price";


    public DBHelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stud

        /**
         * Patient Profile Table
         */

        String sqltblPatientProfile = "CREATE TABLE " + tblPatientProfile +
                "(" +
                "Patient_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Patient_Name TEXT, " +
                "Gender TEXT, " +
                "Birthday TEXT, " +
                "Weight TEXT, " +
                "Height TEXT, " +
                "BloodType TEXT, " +
                "Illness TEXT, " +
                "ContactPerson TEXT, " +
                "EmergencyNumber TEXT" +
                ")";

        /**
         * Doctor Profile Table
         */
        String sqltblDoctorProfile = "CREATE TABLE " + tblDoctorProfile +
                "(" +
                "Doctors_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Doctors_Name TEXT, " +
                "Contact TEXT, " +
                "Hospital TEXT, " +
                "Speciality TEXT, " +
                "Location TEXT" +
                ")";

        /**
         * Medicine Table
         */
        String sqltblMedicine = "CREATE TABLE " + tblMedicine +
                "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Medicine_Name TEXT,"
                + "Date TEXT,"
                + "Time INTEGER,"
                + "Repeat BOOLEAN,"
                + "Repeat_no INTEGER,"
                + "Repeat_type TEXT,"
                + "Active BOOLEAN,"
                + "NumberOfMedicine TEXT,"
                + "Variant TEXT,"
                + "Price TEXT,"
                + "Medicine_Desc TEXT"
                + ")";

        /**
         * Checkup Schedule Table
         */
        String sqltblCheckUpSchedule = "CREATE TABLE " + tblCheckUpSchedule +
                "("
                + KEY_CHECKUPID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_CHECKUPTITLE + "TEXT, "
                + KEY_CHECKUPDESC + "TEXT, "
                + KEY_CHECKUPDOCTOR + "TEXT, "
                + KEY_CHECKUPDATE + "TEXT, "
                + KEY_CHECKUPTIME + "TEXT, "
                + KEY_CHECKUPLOCATION + "TEXT,"
                + KEY_CHECKUPACTIVE + "TEXT"
                + ")";
        /**
         * Expenses Table
         */
        String sqltblExpenses = "CREATE TABLE " + tblExpenses +
                "("
                + KEY_EXPENSESID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_MEDICINEID + "TEXT, "
                + KEY_MEDICINENUMBEROFINTAKE + "TEXT, "
                + KEY_MEDICINEPRICE + "TEXT"
                + ")";
        db.execSQL(sqltblPatientProfile);
        db.execSQL(sqltblDoctorProfile);
        db.execSQL(sqltblMedicine);
        db.execSQL(sqltblExpenses);
        db.execSQL(sqltblCheckUpSchedule);
    }

    /**
     * Creating Database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int args1, int args2) {
        db.execSQL("DROP TABLE IF EXISTS" + tblPatientProfile);
        db.execSQL("DROP TABLE IF EXISTS" + tblDoctorProfile);
        db.execSQL("DROP TABLE IF EXISTS" + tblMedicine);
        db.execSQL("DROP TABLE IF EXISTS" + tblCheckUpSchedule);
        onCreate(db);
    }

    /**
     * Patient Profile Table SQL COMMAND
     */
    public boolean insertPatientProfile(String patientName, String gender, String birthday, String weight, String height,
                                        String bloodtype, String illness, String contactperson, String contactnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Patient_Name", patientName);
        contentValues.put("Gender", gender);
        contentValues.put("Birthday", birthday);
        contentValues.put("Weight", weight);
        contentValues.put("Height", height);
        contentValues.put("BloodType", bloodtype);
        contentValues.put("Illness", illness);
        contentValues.put("ContactPerson", contactperson);
        contentValues.put("EmergencyNumber", contactnumber);

        long result = db.insert(tblPatientProfile, null, contentValues);
        return result != -1;
    }

    public Cursor patientName() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tblPatientProfile, null);
        return res;
    }

    public boolean updateprofile(String id, String weight, String height, String contactperson, String contactnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Patient_ID", id);
        contentValues.put("Weight", weight);
        contentValues.put("Height", height);
        contentValues.put("ContactPerson", contactperson);
        contentValues.put("EmergencyNumber", contactnumber);
        db.update(tblPatientProfile, contentValues, "Patient_ID=?", new String[]{id});
        return true;
    }


    //END OF PATIENT PROFILE COMMAND

    /**
     * DOCTOR TABLE SQL COMMAND
     */
    public boolean insertDoctorProfile(String doctorname, String contact, String speciality, String hospital, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Doctors_Name", doctorname);
        contentValues.put("Contact", contact);
        contentValues.put("Hospital", speciality);
        contentValues.put("Speciality", hospital);
        contentValues.put("Location", location);
        long result = db.insert(tblDoctorProfile, null, contentValues);
        return result != -1;
    }

    public Cursor doctocName() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tblDoctorProfile, null);
        return res;
    }

    // END OF DOCTOR COMMAND

    /**
     * MEDICINE REMINDER TABLE SQL COMMAND
     */

    // Adding new Reminder
    public int addReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, reminder.getTitle());
        values.put(KEY_DATE, reminder.getDate());
        values.put(KEY_TIME, reminder.getTime());
        values.put(KEY_REPEAT, reminder.getRepeat());
        values.put(KEY_REPEAT_NO, reminder.getRepeatNo());
        values.put(KEY_REPEAT_TYPE, reminder.getRepeatType());
        values.put(KEY_ACTIVE, reminder.getActive());
        values.put(KEY_NUMBEROFMEDICINE, reminder.getNumberOfMedicine());
        values.put(KEY_VARIANTS, reminder.getVariant());
        values.put(KEY_PRICE, reminder.getPrice());
        values.put(KEY_MDESC, reminder.getMedicineDesc());
        // Inserting Row
        long ID = db.insert(tblMedicine, null, values);
        db.close();
        return (int) ID;
    }

    public Reminder getReminder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tblMedicine, new String[]
                        {
                                KEY_ID,
                                KEY_TITLE,
                                KEY_DATE,
                                KEY_TIME,
                                KEY_REPEAT,
                                KEY_REPEAT_NO,
                                KEY_REPEAT_TYPE,
                                KEY_ACTIVE,
                                KEY_NUMBEROFMEDICINE,
                                KEY_VARIANTS,
                                KEY_PRICE,
                                KEY_MDESC
                        }, KEY_ID + "=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7),
                cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));

        return reminder;
    }

    // Getting all Reminders
    public List<Reminder> getAllReminders() {
        List<Reminder> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + tblMedicine;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder();
                reminder.setID(Integer.parseInt(cursor.getString(0)));
                reminder.setTitle(cursor.getString(1));
                reminder.setDate(cursor.getString(2));
                reminder.setTime(cursor.getString(3));
                reminder.setRepeat(cursor.getString(4));
                reminder.setRepeatNo(cursor.getString(5));
                reminder.setRepeatType(cursor.getString(6));
                reminder.setActive(cursor.getString(7));
                reminder.setNumberOfMedicine(cursor.getString(8));
                reminder.setVariant(cursor.getString(9));
                reminder.setPrice(cursor.getString(10));
                reminder.setMedicineDesc(cursor.getString(11));
                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        return reminderList;
    }

    // Getting Reminders Count
    public int getRemindersCount() {
        String countQuery = "SELECT * FROM " + tblMedicine;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Updating single Reminder
    public int updateReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, reminder.getTitle());
        values.put(KEY_DATE, reminder.getDate());
        values.put(KEY_TIME, reminder.getTime());
        values.put(KEY_REPEAT, reminder.getRepeat());
        values.put(KEY_REPEAT_NO, reminder.getRepeatNo());
        values.put(KEY_REPEAT_TYPE, reminder.getRepeatType());
        values.put(KEY_ACTIVE, reminder.getActive());
        values.put(KEY_MDESC, reminder.getMedicineDesc());

        // Updating row
        return db.update(tblMedicine, values, KEY_ID + "=?",
                new String[]{String.valueOf(reminder.getID())});
    }

    // Deleting single Reminder
    public void deleteReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tblMedicine, KEY_ID + "=?",
                new String[]{String.valueOf(reminder.getID())});
        db.close();
    }

    public Cursor medicine() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tblMedicine, null);
        return res;
    }

    public int addCheckUpReminder(ReminderCheckUp checkUpReminder) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_CHECKUPTITLE, checkUpReminder.getCheckTitle());
        values.put(KEY_CHECKUPDESC, checkUpReminder.getCheckDesc());
        values.put(KEY_CHECKUPDOCTOR, checkUpReminder.getCheckDoctor());
        values.put(KEY_CHECKUPDATE, checkUpReminder.getCheckDate());
        values.put(KEY_CHECKUPTIME, checkUpReminder.getCheckTime());
        values.put(KEY_CHECKUPLOCATION, checkUpReminder.getCheckLocation());
        values.put(KEY_CHECKUPACTIVE, checkUpReminder.getCheckActive());
        long ID = db.insert(tblCheckUpSchedule, null, values);
        db.close();
        return (int) ID;
    }

    //END OF MEDICINE COMMAND

    /**
     * Checkup Schedule Table SQL COMMAND
     */


    // Getting all Reminders
    public List<ReminderCheckUp> getAllCheckupReminders() {
        List<ReminderCheckUp> reminderList = new ArrayList<>();

        // Select all Query
        String selectQuery = "SELECT * FROM " + tblCheckUpSchedule;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ReminderCheckUp reminder = new ReminderCheckUp();
                reminder.setCheckID(Integer.parseInt(cursor.getString(0)));
                reminder.setCheckTitle(cursor.getString(1));
                reminder.setCheckDesc(cursor.getString(2));
                reminder.setCheckDoctor(cursor.getString(3));
                reminder.setCheckDate(cursor.getString(4));
                reminder.setCheckTime(cursor.getString(5));
                reminder.setCheckLocation(cursor.getString(6));
                reminder.setCheckActive(cursor.getString(7));
                // Adding Reminders to list
                reminderList.add(reminder);
            } while (cursor.moveToNext());
        }
        return reminderList;
    }

    public ReminderCheckUp getCheckupReminder(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tblCheckUpSchedule, new String[]
                        {
                                KEY_CHECKUPID,
                                KEY_CHECKUPTITLE,
                                KEY_CHECKUPDESC,
                                KEY_CHECKUPDOCTOR,
                                KEY_CHECKUPDATE,
                                KEY_CHECKUPTIME,
                                KEY_CHECKUPLOCATION,
                                KEY_CHECKUPACTIVE
                        }, KEY_CHECKUPID + "=?",

                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        ReminderCheckUp getCheckupReminder = new ReminderCheckUp(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7));

        return getCheckupReminder;
    }

    public Cursor myschedule() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tblCheckUpSchedule, null);
        return res;
    }
    //END OF CHECKUP SCHEDULE COMMAND

    /**
     * EXPENSES TABLE SQL COMMAND
     */
    public boolean insertexpenses(String medicineID, String medicineIntake, String medicineprice) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_MEDICINEID, medicineID);
        contentValues.put(KEY_MEDICINENUMBEROFINTAKE, medicineIntake);
        contentValues.put(KEY_MEDICINEPRICE, medicineprice);
        long result = db.insert(tblExpenses, null, contentValues);
        return result != -1;
    }

    public Cursor selectexpenses() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tblExpenses, null);
        return res;
    }

    public Cursor selectmedicine(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + tblExpenses + " WHERE " + KEY_MEDICINEID + "=" + id, null);
        return res;
    }

    public boolean updateexpenses(String id, String medicineIntake) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_EXPENSESID, id);
        contentValues.put(KEY_MEDICINENUMBEROFINTAKE, medicineIntake);

        db.update(tblExpenses, contentValues, "id=?", new String[]{id});
        return true;
    }

}