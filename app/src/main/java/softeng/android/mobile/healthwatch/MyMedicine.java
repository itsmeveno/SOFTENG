package softeng.android.mobile.healthwatch;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyMedicine extends AppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = MainActivity.class.getSimpleName();
    DBHelper mydb;
    Button add;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicine);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setTitle("My Medicine");
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyMedicine.this, Home.class);
                startActivity(i);
            }
        });

        add = (Button) findViewById(R.id.addmedicine);
        message = (TextView) findViewById(R.id.message);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyMedicine.this, AddMedicine.class);
                startActivity(i);
            }
        });

        mydb = new DBHelper(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new Adapter());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor res = mydb.medicine();
        if (res.getCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            message.setVisibility(View.VISIBLE);
        }
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        @SuppressWarnings("unused")

        private List<Item> items;


        public Adapter() {
            super();

            items = new ArrayList<>();
            Cursor res = mydb.medicine();
            while (res.moveToNext()) {
                items.add(new Item(res.getString(1), res.getString(3)));
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Item item = items.get(position);

            holder.title.setText(item.getTitle());
            holder.subtitle.setText(item.getSubtitle());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView subtitle;

            public ViewHolder(View itemView) {
                super(itemView);

                title = itemView.findViewById(R.id.title);
                subtitle = itemView.findViewById(R.id.subtitle);
            }
        }

    }
}

