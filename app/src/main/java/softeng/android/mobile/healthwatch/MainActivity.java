package softeng.android.mobile.healthwatch;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    Slidermanager slidermanager;
    ImageView arrow;
    int[] layouts;
    TextView[] dots;
    LinearLayout dotsLayouts;
    Button next, skip;
    DBHelper mydb;
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addButtomDots(position);
            if (position == layouts.length - 1) {
                next.setText("PROCEED");
                skip.setVisibility(View.GONE);
                arrow.setVisibility(View.GONE);

            } else {
                next.setText("NEXT");
                skip.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        slidermanager = new Slidermanager(this);
        mydb = new DBHelper(this);

        Cursor res = mydb.patientName();
        if (res.getCount() == 1) {
            Intent home = new Intent(MainActivity.this, Home.class);
            startActivity(home);
        }
        if (!slidermanager.Check()) {
            slidermanager.setFirst(false);
            Intent i = new Intent(MainActivity.this, Registration.class);
            startActivity(i);
            finish();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayouts = (LinearLayout) findViewById(R.id.linearDots);
        skip = (Button) findViewById(R.id.btn_skip);
        arrow = (ImageView) findViewById(R.id.ibtnarrow);
        next = (Button) findViewById(R.id.btn_next);

        layouts = new int[]{R.layout.activity_screen_1, R.layout.activity_screen_2, R.layout.activity_screen_3, R.layout.activity_screen_4, R.layout.activity_screen_5};
        addButtomDots(0);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Registration.class);
                startActivity(i);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if (current < layouts.length) {
                    viewPager.setCurrentItem(current);
                } else {
                    Intent i = new Intent(MainActivity.this, Registration.class);
                    startActivity(i);
                    finish();
                }
            }
        });


    }

    private void addButtomDots(int position) {
        dots = new TextView[layouts.length];
        int[] colorActive = getResources().getIntArray(R.array.dot_active);
        int[] colorNotActive = getResources().getIntArray(R.array.dot_inactive);
        dotsLayouts.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorNotActive[position]);
            dotsLayouts.addView(dots[i]);

        }

        if (dots.length > 0)
            dots[position].setTextColor(colorActive[position]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + 1;
    }

    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(layouts[position], container, false);
            container.addView(v);

            return v;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }
}

