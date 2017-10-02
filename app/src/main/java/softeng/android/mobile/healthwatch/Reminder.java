package softeng.android.mobile.healthwatch;

public class Reminder {
    private int mID;
    private String mTitle;
    private String mDate;
    private String mTime;
    private String mRepeat;
    private String mRepeatNo;
    private String mRepeatType;
    private String mActive;
    private String mNumberOfMedicine;
    private String mVariant;
    private String mPrice;
    private String mMedicineDesc;


    public Reminder(int ID, String Title, String Date, String Time, String Repeat, String RepeatNo, String RepeatType, String Active, String NumberofMedicine, String Variant, String Price, String Desc) {
        mID = ID;
        mTitle = Title;
        mDate = Date;
        mTime = Time;
        mRepeat = Repeat;
        mRepeatNo = RepeatNo;
        mRepeatType = RepeatType;
        mActive = Active;
        mNumberOfMedicine = NumberofMedicine;
        mVariant = Variant;
        mPrice = Price;
        mMedicineDesc = Desc;
    }

    public Reminder(String Title, String Date, String Time, String Repeat, String RepeatNo, String RepeatType, String Active, String NumberofMedicine, String Variant, String Price, String Desc) {
        mTitle = Title;
        mDate = Date;
        mTime = Time;
        mRepeat = Repeat;
        mRepeatNo = RepeatNo;
        mRepeatType = RepeatType;
        mActive = Active;
        mNumberOfMedicine = NumberofMedicine;
        mVariant = Variant;
        mPrice = Price;
        mMedicineDesc = Desc;
    }

    public Reminder() {
    }

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRepeatType() {
        return mRepeatType;
    }

    public void setRepeatType(String repeatType) {
        mRepeatType = repeatType;
    }

    public String getRepeatNo() {
        return mRepeatNo;
    }

    public void setRepeatNo(String repeatNo) {
        mRepeatNo = repeatNo;
    }

    public String getRepeat() {
        return mRepeat;
    }

    public void setRepeat(String repeat) {
        mRepeat = repeat;
    }

    public String getActive() {
        return mActive;
    }

    public void setActive(String active) {
        mActive = active;
    }

    public String getNumberOfMedicine() {
        return mNumberOfMedicine;
    }

    public void setNumberOfMedicine(String numberofmedicine) {
        mNumberOfMedicine = numberofmedicine;
    }

    public String getVariant() {
        return mVariant;
    }

    public void setVariant(String variant) {
        mVariant = variant;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getMedicineDesc() {
        return mMedicineDesc;
    }

    public void setMedicineDesc(String desc) {
        mMedicineDesc = desc;
    }
}
