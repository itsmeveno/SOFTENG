package softeng.android.mobile.healthwatch;


public class ReminderCheckUp {

    private int cID;
    private String cTitle;
    private String cDesc;
    private String cDoctor;
    private String cLocation;
    private String cTime;
    private String cDate;
    private String cActive;

    public ReminderCheckUp(int ID, String Title, String Desc, String Doctor, String Location, String Time, String Date, String Active) {
        cID = ID;
        cTitle = Title;
        cDesc = Desc;
        cDoctor = Doctor;
        cLocation = Location;
        cTime = Time;
        cDate = Date;
        cActive = Active;
    }

    public ReminderCheckUp(String Title, String Desc, String Doctor, String Location, String Time, String Date, String Active) {
        cTitle = Title;
        cDesc = Desc;
        cDoctor = Doctor;
        cLocation = Location;
        cTime = Time;
        cDate = Date;
        cActive = Active;
    }

    public ReminderCheckUp() {
    }

    public int getCheckID() {
        return cID;
    }

    public void setCheckID(int cID) {
        cID = cID;
    }

    public String getCheckTitle() {
        return cTitle;
    }

    public void setCheckTitle(String ctitle) {
        cTitle = ctitle;
    }

    public String getCheckDesc() {
        return cDesc;
    }

    public void setCheckDesc(String desc) {
        cDesc = desc;
    }

    public String getCheckDoctor() {
        return cDoctor;
    }

    public void setCheckDoctor(String doctor) {
        cDoctor = doctor;
    }

    public String getCheckLocation() {
        return cLocation;
    }

    public void setCheckLocation(String location) {
        cLocation = location;
    }

    public String getCheckTime() {
        return cTime;
    }

    public void setCheckTime(String time) {
        cTime = time;
    }

    public String getCheckDate() {
        return cDate;
    }

    public void setCheckDate(String date) {
        cDate = date;
    }

    public String getCheckActive() {
        return cActive;
    }

    public void setCheckActive(String active) {
        cActive = active;
    }
}
