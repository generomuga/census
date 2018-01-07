package census.com.census.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

import census.com.census.R;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener{

    //views
    private Toolbar mToolbar;
    FloatingActionButton mSurvey;

    //firebase components
    private FirebaseAuth mAuth;

    int countOwn = 0;
    int countExtended = 0;

    BarChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        mToolbar = (Toolbar) findViewById(R.id.toolBarMain);
        //mSurvey = (FloatingActionButton) findViewById(R.id.fabSurvey);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Census");

        //click fan
        /*mSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainSurveyActivity.class));
            }
        });*/

        mChart = (BarChart) findViewById(R.id.barChart);
        sample();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override   
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_logout:
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void sample(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String uid = mAuth.getCurrentUser().getUid();

        DatabaseReference identificationRef = mDatabase.child("familyIdentification");

        identificationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){
                    countOwn = 0;
                    countExtended = 0;

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Map<String, Object> object1 = (Map<String, Object>) dataSnapshot1.getValue();

                        int ownership = Integer.parseInt(object1.get("ownership").toString());
                        if (ownership == 0){
                            countOwn++;
                        }
                        if (ownership == 1){
                            countExtended++;
                        }


                        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
                        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();


                        yVals1.add(new BarEntry(0, countOwn));
                        yVals2.add(new BarEntry(1, countExtended));

                        BarDataSet set1 = new BarDataSet(yVals1, "Dates");
                        set1.setLabel("Own");
                        set1.setColors(Color.RED);
                        set1.setValueTextColor(Color.WHITE);

                        BarDataSet set2 = new BarDataSet(yVals2, "Dates");
                        set2.setLabel("Extended");
                        set2.setColors(Color.GREEN);
                        set2.setValueTextColor(Color.WHITE);

                        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
                        dataSets.add(set1);
                        dataSets.add(set2);

                        BarData data = new BarData(dataSets);

                        //data.setValueTextSize(10f);
                        //data.setBarWidth(0.9f);

                        mChart.setTouchEnabled(false);
                        mChart.setData(data);
                        mChart.getAxisLeft().setTextColor(Color.WHITE);
                        mChart.getAxisRight().setTextColor(Color.WHITE);
                        mChart.getXAxis().setTextColor(Color.WHITE);
                        mChart.getLegend().setTextColor(Color.WHITE);
                        Description description = new Description();
                        description.setText("");
                        mChart.setDescription(description);
                        mChart.notifyDataSetChanged();
                        mChart.invalidate();

                        Toast.makeText(getApplicationContext(), Integer.toString(countOwn), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
