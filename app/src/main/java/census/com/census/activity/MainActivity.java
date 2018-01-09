package census.com.census.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    int countResident = 0;
    int countNonResident = 0;

    int countActive = 0;
    int countInactive = 0;

    BarChart mChartResidency;
    BarChart mChartOwnership;
    BarChart mChartStatus;

    SharedPreferences mSharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreference = getSharedPreferences("census.com.census", Context.MODE_PRIVATE);
        mSharedPreference.edit().clear().apply();

        //init views
        mToolbar = (Toolbar) findViewById(R.id.toolBarMain);
        mSurvey = (FloatingActionButton) findViewById(R.id.fabSurvey);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Census");

        //click fan
        mSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainSurveyActivity.class));
            }
        });

        mChartOwnership = (BarChart) findViewById(R.id.barChartOwnership);
        mChartResidency = (BarChart) findViewById(R.id.barChartResidency);
        mChartStatus = (BarChart) findViewById(R.id.barChartStatus);

        graphIdentification();

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


    private void graphIdentification(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference identificationRef = mDatabase.child("familyIdentification");

        identificationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){
                    countOwn = 0;
                    countExtended = 0;

                    countResident = 0;
                    countNonResident = 0;

                    countActive = 0;
                    countInactive = 0;

                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Map<String, Object> object1 = (Map<String, Object>) dataSnapshot1.getValue();

                        int residency = Integer.parseInt(object1.get("residency").toString());
                        int ownership = Integer.parseInt(object1.get("ownership").toString());
                        int familyStatus = Integer.parseInt(object1.get("familyStatus").toString());

                        if (residency == 0){
                            countResident++;
                        }
                        if (residency == 1){
                            countNonResident++;
                        }

                        graphResidency();

                        if (ownership == 0){
                            countOwn++;
                        }
                        if (ownership == 1){
                            countExtended++;
                        }

                        graphOwnership();

                        if (familyStatus == 0){
                            countActive++;
                        }
                        if (familyStatus == 1){
                            countInactive++;
                        }

                        graphStatus();

                        //Toast.makeText(getApplicationContext(), Integer.toString(countNonResident), Toast.LENGTH_SHORT).show();
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


    private void graphResidency(){
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();


        yVals1.add(new BarEntry(0, countResident));
        yVals2.add(new BarEntry(1, countNonResident));

        BarDataSet set1 = new BarDataSet(yVals1, "Dates");
        set1.setLabel("Resident");
        set1.setColors(-33);
        set1.setValueTextColor(Color.WHITE);

        BarDataSet set2 = new BarDataSet(yVals2, "Dates");
        set2.setLabel("Non-resident");
        set2.setColors(Color.GREEN);
        set2.setValueTextColor(Color.WHITE);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);

        mChartResidency.setTouchEnabled(false);
        mChartResidency.setData(data);
        mChartResidency.getAxisLeft().setTextColor(Color.WHITE);
        mChartResidency.getAxisRight().setTextColor(Color.WHITE);
        mChartResidency.getXAxis().setTextColor(Color.WHITE);
        mChartResidency.getLegend().setTextColor(Color.WHITE);

        Description description = new Description();
        description.setText("");

        mChartResidency.setDescription(description);
        mChartResidency.notifyDataSetChanged();
        mChartResidency.animateY(1000);
        mChartResidency.invalidate();
    }

    private void graphOwnership(){
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, countOwn));
        yVals2.add(new BarEntry(1, countExtended));

        BarDataSet set1 = new BarDataSet(yVals1, "Dates");
        set1.setLabel("Own");
        set1.setColors(-33);
        set1.setValueTextColor(Color.WHITE);

        BarDataSet set2 = new BarDataSet(yVals2, "Dates");
        set2.setLabel("Extended");
        set2.setColors(Color.GREEN);
        set2.setValueTextColor(Color.WHITE);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);

        mChartOwnership.setTouchEnabled(false);
        mChartOwnership.setData(data);
        mChartOwnership.getAxisLeft().setTextColor(Color.WHITE);
        mChartOwnership.getAxisRight().setTextColor(Color.WHITE);
        mChartOwnership.getXAxis().setTextColor(Color.WHITE);
        mChartOwnership.getLegend().setTextColor(Color.WHITE);

        Description description = new Description();
        description.setText("");

        mChartOwnership.setDescription(description);
        mChartOwnership.notifyDataSetChanged();
        mChartOwnership.animateY(1000);
        mChartOwnership.invalidate();
    }

    private void graphStatus(){
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, countActive));
        yVals2.add(new BarEntry(1, countInactive));

        BarDataSet set1 = new BarDataSet(yVals1, "Dates");
        set1.setLabel("Active");
        set1.setColors(-33);
        set1.setValueTextColor(Color.WHITE);

        BarDataSet set2 = new BarDataSet(yVals2, "Dates");
        set2.setLabel("Inactive");
        set2.setColors(Color.GREEN);
        set2.setValueTextColor(Color.WHITE);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);

        BarData data = new BarData(dataSets);

        mChartStatus.setTouchEnabled(false);
        mChartStatus.setData(data);
        mChartStatus.getAxisLeft().setTextColor(Color.WHITE);
        mChartStatus.getAxisRight().setTextColor(Color.WHITE);
        mChartStatus.getXAxis().setTextColor(Color.WHITE);
        mChartStatus.getLegend().setTextColor(Color.WHITE);

        Description description = new Description();
        description.setText("");

        mChartStatus.setDescription(description);
        mChartStatus.notifyDataSetChanged();
        mChartStatus.animateY(1000);
        mChartStatus.invalidate();
    }


}
