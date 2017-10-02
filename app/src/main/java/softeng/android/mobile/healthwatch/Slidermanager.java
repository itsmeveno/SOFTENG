package softeng.android.mobile.healthwatch;

import android.content.Context;
import android.content.SharedPreferences;


public class Slidermanager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public Slidermanager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences("first", 0);
        editor = pref.edit();
    }

    public void setFirst(boolean isFirst) {
        editor.putBoolean("check", isFirst);
        editor.commit();
    }

    public boolean Check() {
        return pref.getBoolean("check", true);
    }
}