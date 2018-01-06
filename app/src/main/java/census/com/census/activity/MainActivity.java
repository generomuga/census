package census.com.census.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        sample();


        mChart = (BarChart) findViewById(R.id.barChart);
        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);



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
                countOwn = 0;
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){
                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                        Map<String, Object> object1 = (Map<String, Object>) dataSnapshot1.getValue();

                        int ownership = Integer.parseInt(object1.get("ownership").toString());
                        if (ownership == 0){
                            countOwn++;
                        }
                        if (ownership == 1){
                            countExtended++;
                        }

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
