package softeng.android.mobile.healthwatch;

/**
 * Created by Jayvy on 9/30/2017.
 */

public class Item {
    private String title;
    private String subtitle;

    Item(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}