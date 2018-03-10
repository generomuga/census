package census.com.census.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import census.com.census.R;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener{

    //views
    private Toolbar mToolbar;
    private TextView mTotalVoters;
    FloatingActionButton mSurvey;

    //firebase components
    private FirebaseAuth mAuth;

    int countOwn = 0;
    int countExtended = 0;

    int countResident = 0;
    int countNonResident = 0;

    int countActive = 0;
    int countInactive = 0;

    int countMale = 0;
    int countFemale = 0;

    int countVoters = 0;

    int countPlanYes = 0;
    int countPlanNo = 0;
    int countPlanNa = 0;

    int countBasal = 0;
    int countCervical = 0;
    int countCondom = 0;
    int countDepo = 0;
    int countIud = 0;
    int countLactation = 0;
    int countPills = 0;
    int countRhtym = 0;
    int countStandard = 0;
    int countSympho = 0;
    int countTubal = 0;
    int countVasectomy = 0;
    int countWithdrawal = 0;

    String top, mid, bottom = null;
    int topVal, midVal, bottomVal = 0;

    int countEatCompleteYes = 0;
    int countEatCompleteNo = 0;

    int countDugWell = 0;
    int countCommunity = 0;
    int countTubed = 0;
    int countSpring = 0;
    int countPeddler = 0;
    int countMineral = 0;

    BarChart mChartResidency;
    BarChart mChartOwnership;
    BarChart mChartStatus;
    BarChart mChartTopFamilyPlan;
    PieChart mChartPopulation;
    PieChart mChartFamilyPlan;
    PieChart mChartEatComplete;
    PieChart mChartSourceWater;

    SharedPreferences mSharedPreference;

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreference = getSharedPreferences("census.com.census", Context.MODE_PRIVATE);
        mSharedPreference.edit().clear().apply();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //init views
        mTotalVoters = (TextView) findViewById(R.id.textViewTotalVoters);
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

        mChartOwnership = (BarChart) findViewById(R.id.barChartOwnership);
        mChartResidency = (BarChart) findViewById(R.id.barChartResidency);
        mChartStatus = (BarChart) findViewById(R.id.barChartStatus);
        mChartTopFamilyPlan = (BarChart) findViewById(R.id.barChartTopFamilyPlan);
        mChartPopulation = (PieChart) findViewById(R.id.pieChartPopulation);
        mChartFamilyPlan = (PieChart) findViewById(R.id.pieChartFamilyPlan);
        mChartEatComplete = (PieChart) findViewById(R.id.pieChartEatFamilyComplete);
        mChartSourceWater = (PieChart) findViewById(R.id.pieChartEatSourceWater);

        graphIdentification();
        graphFamiy();
        graphHealth();
        graphEnvironment();

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
            case R.id.menu_add:
                startActivity(new Intent(MainActivity.this, MainSurveyActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void graphIdentification(){
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

    private void graphFamiy(){
        DatabaseReference familyRef = mDatabase.child("family");

        familyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){

                    countMale = 0;
                    countFemale = 0;
                    countVoters = 0;

                    for (DataSnapshot dataSnapshotFamily: dataSnapshot.getChildren()){
                        Map<String, Object> objectFamily = (Map<String, Object>) dataSnapshotFamily.getValue();

                        int noMale = Integer.parseInt(objectFamily.get("noMale").toString());
                        int noFemale = Integer.parseInt(objectFamily.get("noFemale").toString());
                        int noVoters = Integer.parseInt(objectFamily.get("noVoters").toString());

                        countMale = countMale + noMale;
                        countFemale = countFemale + noFemale;
                        countVoters = countVoters + noVoters;

                        //Toast.makeText(getApplicationContext(), Integer.toString(countVoters), Toast.LENGTH_LONG).show();
                        graphPopulation();
                        graphVoters();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void graphHealth(){
        DatabaseReference familyRef = mDatabase.child("health");

        familyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0){

                    countPlanYes = 0;
                    countPlanNo = 0;
                    countPlanNa = 0;

                    countBasal = 0;
                    countCervical = 0;
                    countCondom = 0;
                    countDepo = 0;
                    countIud = 0;
                    countLactation = 0;
                    countPills = 0;
                    countRhtym = 0;
                    countStandard = 0;
                    countSympho = 0;
                    countTubal = 0;
                    countVasectomy = 0;
                    countWithdrawal = 0;

                    countEatCompleteYes = 0;
                    countEatCompleteNo = 0;

                    for (DataSnapshot dataSnapshotHealth: dataSnapshot.getChildren()){
                        Map<String, Object> objectHealth = (Map<String, Object>) dataSnapshotHealth.getValue();

                        int noPlan = Integer.parseInt(objectHealth.get("familyPlan").toString());
                        if (noPlan == 1){
                            countPlanYes = countPlanYes + 1;
                        }

                        if (noPlan == 2){
                            countPlanNa = countPlanNa + 1;
                        }

                        if (noPlan == 0){
                            countPlanNo = countPlanNo + 1;
                        }

                        int noEatComplete = Integer.parseInt(objectHealth.get("eatComplete").toString());
                        if (noEatComplete == 0){
                            countEatCompleteNo = countEatCompleteNo + 1;
                        }

                        if (noEatComplete == 1){
                            countEatCompleteYes = countEatCompleteYes + 1;
                        }

                        int noBasal = Integer.parseInt(objectHealth.get("basal").toString());
                        if (noBasal == 1){
                            countBasal = countBasal + 1;
                        }

                        int noCervical = Integer.parseInt(objectHealth.get("cervical").toString());
                        if (noCervical == 1){
                            countCervical = countCervical + 1;
                        }

                        int noCondom = Integer.parseInt(objectHealth.get("condom").toString());
                        if (noCondom == 1){
                            countCondom = countCondom + 1;
                        }

                        int noDepo = Integer.parseInt(objectHealth.get("depo").toString());
                        if (noDepo == 1){
                            countDepo = countDepo + 1;
                        }

                        int noIud = Integer.parseInt(objectHealth.get("iud").toString());
                        if (noIud == 1){
                            countIud = countIud + 1;
                        }

                        int noLactation = Integer.parseInt(objectHealth.get("lactation").toString());
                        if (noLactation == 1){
                            countLactation = countLactation + 1;
                        }

                        int noPills = Integer.parseInt(objectHealth.get("pills").toString());
                        if (noPills == 1){
                            countPills = countPills + 1;
                        }

                        int noRhytm = Integer.parseInt(objectHealth.get("rhtythm").toString());
                        if (noRhytm == 1){
                            countRhtym = countRhtym + 1;
                        }

                        int noStandard = Integer.parseInt(objectHealth.get("standard").toString());
                        if (noStandard == 1){
                            countStandard = countStandard + 1;
                        }

                        int noSympho = Integer.parseInt(objectHealth.get("sympho").toString());
                        if (noSympho == 1){
                            countSympho = countSympho + 1;
                        }

                        int noTubal = Integer.parseInt(objectHealth.get("tubal").toString());
                        if (noTubal == 1){
                            countTubal = countTubal + 1;
                        }

                        int noVasectomy = Integer.parseInt(objectHealth.get("vasectomy").toString());
                        if (noVasectomy == 1){
                            countVasectomy = countVasectomy + 1;
                        }

                        int noWithdrawal = Integer.parseInt(objectHealth.get("withdrawal").toString());
                        if (noWithdrawal == 1){
                            countWithdrawal = countWithdrawal + 1;
                        }


                        int countTubal = 0;
                        int countVasectomy = 0;
                        int countWithdrawal = 0;
                        HashMap<String, Integer> map = new HashMap<>();
                        map.put("Basal", countBasal);
                        map.put("Cervical", countCervical);
                        map.put("Condom", countCondom);
                        map.put("Depo", countDepo);
                        map.put("Iud", countIud);
                        map.put("Lactation", countLactation);
                        map.put("Pills", countPills);
                        map.put("Rhythm", countRhtym);
                        map.put("Standard", countStandard);
                        map.put("Sympho", countSympho);
                        map.put("Tubal", countTubal);
                        map.put("Vasectomy", countVasectomy);
                        map.put("Withdrawal", countWithdrawal);

                        TreeMap<String, Integer> sortedMap = sortMapByValue(map);
                        //Log.i("sorted", sortedMap.values().toArray()[7].toString());
                        top = sortedMap.firstEntry().getKey();
                        topVal = sortedMap.firstEntry().getValue();

                        mid =  sortedMap.keySet().toArray()[7].toString();
                        midVal = Integer.parseInt(sortedMap.values().toArray()[7].toString());

                        bottom = sortedMap.lastEntry().getKey();
                        bottomVal = sortedMap.lastEntry().getValue();

                        graphFamilyPlan();
                        graphEatComplete();
                        graphTopFamilyPlan();
                        //Toast.makeText(getApplicationContext(), Integer.toString(countPlanNo), Toast.LENGTH_LONG).show();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void graphEnvironment(){
        DatabaseReference ennvironmentRef = mDatabase.child("environment");
        ennvironmentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists() && dataSnapshot.getChildrenCount() > 0 ){

                    countDugWell = 0;
                    countCommunity = 0;
                    countTubed = 0;
                    countSpring = 0;
                    countPeddler = 0;
                    countMineral = 0;

                    for (DataSnapshot dataSnapshotEnvironment: dataSnapshot.getChildren()) {
                        Map<String, Object> objectEnvironment = (Map<String, Object>) dataSnapshotEnvironment.getValue();

                        int noWater = Integer.parseInt(objectEnvironment.get("water").toString());

                        if (noWater == 0){
                            countDugWell = countDugWell + 1;
                        }
                        if (noWater == 1){
                            countCommunity = countCommunity + 1;
                        }
                        if (noWater == 2){
                            countTubed = countTubed + 1;
                        }
                        if (noWater == 3){
                            countSpring = countSpring + 1;
                        }
                        if (noWater == 4){
                            countPeddler = countPeddler + 1;
                        }
                        if (noWater == 5){
                            countMineral = countMineral + 1;
                        }

                        graphWaterSource();
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
        mChartResidency.getXAxis().setTextColor(0);
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
        mChartOwnership.getXAxis().setTextColor(0);
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
        mChartStatus.getXAxis().setTextColor(0);
        mChartStatus.getLegend().setTextColor(Color.WHITE);

        Description description = new Description();
        description.setText("");

        mChartStatus.setDescription(description);
        mChartStatus.notifyDataSetChanged();
        mChartStatus.animateY(1000);
        mChartStatus.invalidate();
    }

    private void graphPopulation(){
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(countMale, "Male"));
        pieEntries.add(new PieEntry(countFemale, "Female"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Population");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data = new PieData(dataSet);

        //dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        mChartPopulation.setData(data);
        mChartPopulation.invalidate();
        mChartPopulation.animateY(1000);
        mChartPopulation.getLegend().setTextColor(Color.WHITE);
        Description description = new Description();
        description.setText("");
        mChartPopulation.setDescription(description);
    }

    private void graphFamilyPlan(){
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(countPlanYes, "Yes"));
        pieEntries.add(new PieEntry(countPlanNo, "No"));
        pieEntries.add(new PieEntry(countPlanNa, "NA"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Family plan usage");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data = new PieData(dataSet);

        mChartFamilyPlan.setData(data);
        mChartFamilyPlan.invalidate();
        mChartFamilyPlan.animateY(1000);
        mChartFamilyPlan.getLegend().setTextColor(Color.WHITE);
        Description description = new Description();
        description.setText("");
        mChartFamilyPlan.setDescription(description);
    }

    private void graphTopFamilyPlan(){
        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        yVals1.add(new BarEntry(0, topVal));
        yVals2.add(new BarEntry(1, midVal));
        yVals3.add(new BarEntry(2, bottomVal));

        BarDataSet set1 = new BarDataSet(yVals1, "Dates");
        set1.setLabel(top+" (Top)");
        set1.setColors(-33);
        set1.setValueTextColor(Color.WHITE);

        BarDataSet set2 = new BarDataSet(yVals2, "Dates");
        set2.setLabel(mid+" (Mid)");
        set2.setColors(Color.GREEN);
        set2.setValueTextColor(Color.WHITE);

        BarDataSet set3 = new BarDataSet(yVals3, "Dates");
        set3.setLabel(bottom+" (Bottom)");
        set3.setColors(Color.RED);
        set3.setValueTextColor(Color.WHITE);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BarData data = new BarData(dataSets);

        mChartTopFamilyPlan.setTouchEnabled(false);
        mChartTopFamilyPlan.setData(data);
        mChartTopFamilyPlan.getAxisLeft().setTextColor(Color.WHITE);
        mChartTopFamilyPlan.getAxisRight().setTextColor(Color.WHITE);
        mChartTopFamilyPlan.getXAxis().setTextColor(Color.WHITE);
        mChartTopFamilyPlan.getLegend().setTextColor(Color.WHITE);

        Description description = new Description();
        description.setText("");

        mChartTopFamilyPlan.setDescription(description);
        mChartTopFamilyPlan.notifyDataSetChanged();
        mChartTopFamilyPlan.animateY(1000);
        mChartTopFamilyPlan.invalidate();
    }

    private void graphVoters(){
        mTotalVoters.setText(Integer.toString(countVoters));
    }

    private void graphEatComplete(){
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(countEatCompleteYes, "Complete"));
        pieEntries.add(new PieEntry(countEatCompleteNo, "Incomplete"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Population");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data = new PieData(dataSet);

        mChartEatComplete.setData(data);
        mChartEatComplete.invalidate();
        mChartEatComplete.animateY(1000);
        mChartEatComplete.getLegend().setTextColor(Color.WHITE);
        Description description = new Description();
        description.setText("");
        mChartEatComplete.setDescription(description);
    }

    private void graphWaterSource(){
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(countDugWell, "Dug well"));
        pieEntries.add(new PieEntry(countCommunity, "Community"));
        pieEntries.add(new PieEntry(countTubed, "Tube"));
        pieEntries.add(new PieEntry(countSpring, "Spring"));
        //pieEntries.add(new PieEntry(countPeddler, "Peddler"));
        //pieEntries.add(new PieEntry(countMineral, "Mineral"));

        PieDataSet dataSet = new PieDataSet(pieEntries, "Water system");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        PieData data = new PieData(dataSet);

        mChartSourceWater.setData(data);
        mChartSourceWater.invalidate();
        mChartSourceWater.animateY(1000);
        mChartSourceWater.getLegend().setTextColor(Color.WHITE);
        Description description = new Description();
        description.setText("");
        mChartSourceWater.setDescription(description);
    }

    public static TreeMap<String, Integer> sortMapByValue(HashMap<String, Integer> map){
        Comparator<String> comparator = new ValueComparator(map);
        //TreeMap is a map sorted by its keys.
        //The comparator is used to sort the TreeMap by keys.
        TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
        result.putAll(map);
        return result;
    }

}

class ValueComparator implements Comparator<String>{

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    public ValueComparator(HashMap<String, Integer> map){
        this.map.putAll(map);
    }

    @Override
    public int compare(String s1, String s2) {
        if(map.get(s1) >= map.get(s2)){
            return -1;
        }else{
            return 1;
        }
    }
}