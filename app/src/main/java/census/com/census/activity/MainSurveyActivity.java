package census.com.census.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import census.com.census.Environment;
import census.com.census.Family;
import census.com.census.FamilyIdentification;
import census.com.census.Health;
import census.com.census.R;
import census.com.census.fragment.EnvironmentFragment;
import census.com.census.fragment.FamilyFragment;
import census.com.census.fragment.FamilyIdentificationFragment;
import census.com.census.fragment.HealthFragment;

public class MainSurveyActivity extends AppCompatActivity {

    //views
    private Toolbar mToolBarSurvey;

    private DatabaseReference mDatabase;

    //fragments
    FragmentTransaction mTransaction;

    String key = null;

    public static ImageButton mFamily;
    public static ImageButton mHealth;
    public static ImageButton mEnvironment;

    public static MenuItem mSave;

    private boolean isLastFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        key = mDatabase.push().getKey();

        mFamily = (ImageButton) findViewById(R.id.imageButtonFamily);
        mFamily.setEnabled(false);

        mHealth = (ImageButton) findViewById(R.id.imageButtonHealth);
        mHealth.setEnabled(false);

        mEnvironment = (ImageButton) findViewById(R.id.imageButtonEnvironment);
        mEnvironment.setEnabled(false);

        //to add toolbar in the activity
        mToolBarSurvey = (Toolbar) findViewById(R.id.toolBarSurvey);
        setSupportActionBar(mToolBarSurvey);
        getSupportActionBar().setTitle("Family Identification");

        //fragment
        if (savedInstanceState != null){
            return;
        }

        //set default fragment
        FamilyIdentificationFragment familyIdentificationFragment = new FamilyIdentificationFragment();
        familyIdentificationFragment.setArguments(getIntent().getExtras());
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.add(R.id.fragmentMain, familyIdentificationFragment).commit();
    }

    public void changeFragment(View view){

        mTransaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.imageButtonFamilyId:
                isLastFragment = false;
                getSupportActionBar().setTitle("Family Identification");
                FamilyIdentificationFragment familyIdentificationFragment = new FamilyIdentificationFragment();
                mTransaction.replace(R.id.fragmentMain, familyIdentificationFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonFamily:
                isLastFragment = false;
                getSupportActionBar().setTitle("Family");
                FamilyFragment familyFragment = new FamilyFragment();
                mTransaction.replace(R.id.fragmentMain, familyFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonHealth:
                isLastFragment = false;
                getSupportActionBar().setTitle("Health");
                HealthFragment healthFragment = new HealthFragment();
                mTransaction.replace(R.id.fragmentMain, healthFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonEnvironment:
                isLastFragment = true;
                getSupportActionBar().setTitle("Environment");
                EnvironmentFragment environmentFragment = new EnvironmentFragment();
                mTransaction.replace(R.id.fragmentMain, environmentFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;
        }
    }

    private boolean checkIdentificationFieldsComplete(){
        if (FamilyIdentificationFragment.mFname.getText().toString().equals("")){
            return false;
        }
        if (FamilyIdentificationFragment.mMname.getText().toString().equals("")){
            return false;
        }
        if (FamilyIdentificationFragment.mLname.getText().toString().equals("")){
            return false;
        }
        if (FamilyIdentificationFragment.mHouseNo.getText().toString().equals("")){
            return false;
        }
        if (FamilyIdentificationFragment.mStreetNo.getText().toString().equals("")){
            return false;
        }
        return true;
    }

    private boolean checkFamilyFieldsComplete(){

        if (FamilyFragment.mMaleMember.getText().toString().equals("")) {
            FamilyFragment.mMaleMember.setError("Required field");
            return false;
        }

        if (FamilyFragment.mFemaleMember.getText().toString().equals("")) {
            FamilyFragment.mFemaleMember.setError("Required field");
            return false;
        }

        if (FamilyFragment.mYearOrigin.getText().toString().equals("")) {
            FamilyFragment.mYearOrigin.setError("Required field");
            return false;
        }

        if (FamilyFragment.mPlaceOrigin.getText().toString().equals("")) {
            FamilyFragment.mPlaceOrigin.setError("Required field");
            return false;
        }

        if (FamilyFragment.mNoVoters.getText().toString().equals("")) {
            FamilyFragment.mNoVoters.setError("Required field");
            return false;
        }

        if (FamilyFragment.mBicycle.isChecked()) {
            if (FamilyFragment.mBicycleNo.getText().toString().equals("")) {
                FamilyFragment.mBicycleNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mBoat.isChecked()) {
            if (FamilyFragment.mBoatNo.getText().toString().equals("")) {
                FamilyFragment.mBoatNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mBus.isChecked()) {
            if (FamilyFragment.mBusNo.getText().toString().equals("")) {
                FamilyFragment.mBusNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mCar.isChecked()) {
            if (FamilyFragment.mCarNo.getText().toString().equals("")) {
                FamilyFragment.mCarNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mJeep.isChecked()) {
            if (FamilyFragment.mJeepNo.getText().toString().equals("")) {
                FamilyFragment.mJeepNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mMotorBoat.isChecked()) {
            if (FamilyFragment.mMotorBoatNo.getText().toString().equals("")) {
                FamilyFragment.mMotorBoatNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mMotorcycle.isChecked()) {
            if (FamilyFragment.mMotorcycleNo.getText().toString().equals("")) {
                FamilyFragment.mMotorcycleNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mOwner.isChecked()) {
            if (FamilyFragment.mOwnerNo.getText().toString().equals("")) {
                FamilyFragment.mOwnerNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mPedicab.isChecked()) {
            if (FamilyFragment.mPedicabNo.getText().toString().equals("")) {
                FamilyFragment.mPedicabNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mPickUp.isChecked()) {
            if (FamilyFragment.mPickUpNo.getText().toString().equals("")) {
                FamilyFragment.mPedicabNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mPumpboat.isChecked()) {
            if (FamilyFragment.mPumpboatNo.getText().toString().equals("")) {
                FamilyFragment.mPumpboatNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mRaft.isChecked()) {
            if (FamilyFragment.mRaftNo.getText().toString().equals("")) {
                FamilyFragment.mRaftNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mSuv.isChecked()) {
            if (FamilyFragment.mSuvNo.getText().toString().equals("")) {
                FamilyFragment.mSuvNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mTricycle.isChecked()) {
            if (FamilyFragment.mTricycleNo.getText().toString().equals("")) {
                FamilyFragment.mTricycleNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mTruck.isChecked()) {
            if (FamilyFragment.mTruckNo.getText().toString().equals("")) {
                FamilyFragment.mTruckNo.setError("Required field");
                return false;
            }
        }

        if (FamilyFragment.mVan.isChecked()) {
            if (FamilyFragment.mVanNo.getText().toString().equals("")) {
                FamilyFragment.mVanNo.setError("Required field");
                return false;
            }
        }


        return true;
    }

    private boolean checkYear(){
        int year = Integer.parseInt(FamilyFragment.mYearOrigin.getText().toString());
        if (year >= 1980 && year <= 2018){
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_survey,menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        mSave = menu.findItem(R.id.action_save);
        mSave.setVisible(isLastFragment);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                //if (checkIdentificationFieldsComplete() && checkFamilyFieldsComplete()) {

                if (checkIdentificationFieldsComplete() && checkFamilyFieldsComplete()){
                    sendDataIdentification();
                    sendDataFamily();
                    startActivity(new Intent(MainSurveyActivity.this, MainActivity.class));
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendDataIdentification(){

        if (checkIdentificationFieldsComplete()) {
            int residency;
            int ownership;
            int familyStatus;

            if (FamilyIdentificationFragment.mResident.isChecked())
                residency = 0;
            else
                residency = 1;

            if (FamilyIdentificationFragment.mOwner.isChecked())
                ownership = 0;
            else
                ownership = 1;

            if (FamilyIdentificationFragment.mActive.isChecked())
                familyStatus = 0;
            else
                familyStatus = 1;

            FamilyIdentification familyIdentification = new FamilyIdentification();
            familyIdentification.setfName(FamilyIdentificationFragment.mFname.getText().toString().trim());
            familyIdentification.setmName(FamilyIdentificationFragment.mMname.getText().toString().trim());
            familyIdentification.setlName(FamilyIdentificationFragment.mLname.getText().toString().trim());
            familyIdentification.setRegion(FamilyIdentificationFragment.mRegion.getText().toString().trim());
            familyIdentification.setProvince(FamilyIdentificationFragment.mProvince.getText().toString().trim());
            familyIdentification.setMunicipality(FamilyIdentificationFragment.mMunicipality.getText().toString().trim());
            familyIdentification.setBarangay(FamilyIdentificationFragment.mBarangay.getText().toString().trim());
            familyIdentification.setHouseNo(FamilyIdentificationFragment.mHouseNo.getText().toString().trim());
            familyIdentification.setStreetNo(FamilyIdentificationFragment.mStreetNo.getText().toString().trim());
            familyIdentification.setResidency(residency);
            familyIdentification.setOwnership(ownership);
            familyIdentification.setFamilyStatus(familyStatus);

            DatabaseReference refFamilyIdentification = mDatabase.child("familyIdentification");

            refFamilyIdentification.child(key).setValue(familyIdentification).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "Please complete Family Identification (1)", Toast.LENGTH_SHORT).show();
        }
    }


    private void sendDataFamily(){

        /*private int noMale;
        private int noFemale;
        private String yearResided;
        private String placeOrigin;
        private String noVoters;
        private int selectBicycle;
        private int noBicycle;
        private int selectBoat;
        private int noBoat;
        private int selectBus;
        private int noBus;
        private int selectCar;
        private int noCar;
        private int selectJeepney;
        private int noJeep;
        private int selectMotorboat;
        private int noMotorboat;
        private int selectMotorcycle;
        private int noMotorCycle;
        private int selectOwnerJeep;
        private int noOwnerJeep;
        private int selectPedicab;
        private int noPedicab;
        private int selectPickup;
        private int noPickup;
        private int selectPumpBoat;
        private int noPumpBoat;
        private int selectRaft;
        private int noRaft;
        private int selectSuv;
        private int noSuv;
        private int selectTricycle;
        private int noTricycle;
        private int selectTruck;
        private int noTruck;
        private int selectVan;
        private int noVan;
        private String timeStamp;*/

        if (checkFamilyFieldsComplete()) {

            int selectBicycle = (FamilyFragment.mBicycle.isChecked()) ? 1 : 0;
            int selectBoat = (FamilyFragment.mBoat.isChecked()) ? 1 : 0;
            int selectBus = (FamilyFragment.mBus.isChecked()) ? 1 : 0;
            int selectCar = (FamilyFragment.mCar.isChecked()) ? 1 : 0;
            int selectJeep = (FamilyFragment.mJeep.isChecked()) ? 1 : 0;
            int selectMotorboat = (FamilyFragment.mMotorBoat.isChecked()) ? 1 : 0;
            int selectMotorcycle = (FamilyFragment.mMotorcycle.isChecked()) ? 1 : 0;
            int selectOwner = (FamilyFragment.mOwner.isChecked()) ? 1 : 0;
            int selectPedicab = (FamilyFragment.mPedicab.isChecked()) ? 1 : 0;
            int selectPickup = (FamilyFragment.mPickUp.isChecked()) ? 1 : 0;
            int selectPump = (FamilyFragment.mPumpboat.isChecked()) ? 1 : 0;
            int selectRaft = (FamilyFragment.mRaft.isChecked()) ? 1 : 0;
            int selectSuv = (FamilyFragment.mSuv.isChecked()) ? 1 : 0;
            int selectTricycle = (FamilyFragment.mTricycle.isChecked()) ? 1 : 0;
            int selectTruck = (FamilyFragment.mTruck.isChecked()) ? 1 : 0;
            int selectVan = (FamilyFragment.mVan.isChecked()) ? 1 : 0;

            int noBicycle = (FamilyFragment.mBicycleNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mBicycleNo.getText().toString());
            int noBoat = (FamilyFragment.mBoatNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mBoatNo.getText().toString());
            int noBus = (FamilyFragment.mBusNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mBusNo.getText().toString());
            int noCar = (FamilyFragment.mCarNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mCarNo.getText().toString());
            int noJeep = (FamilyFragment.mJeepNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mJeepNo.getText().toString());
            int noMotorboat = (FamilyFragment.mMotorBoatNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mMotorBoatNo.getText().toString());
            int noMotorCycle = (FamilyFragment.mMotorcycleNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mMotorcycleNo.getText().toString());
            int noOwner = (FamilyFragment.mOwnerNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mOwnerNo.getText().toString());
            int noPedicab = (FamilyFragment.mPedicabNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mPedicabNo.getText().toString());
            int noPickup = (FamilyFragment.mPickUpNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mPickUpNo.getText().toString());
            int noPump = (FamilyFragment.mPumpboatNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mPumpboatNo.getText().toString());
            int noRaft = (FamilyFragment.mRaftNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mRaftNo.getText().toString());
            int noSuv = (FamilyFragment.mSuvNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mSuvNo.getText().toString());
            int noTricycle = (FamilyFragment.mTricycleNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mTricycleNo.getText().toString());
            int noTruck = (FamilyFragment.mTruckNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mTruckNo.getText().toString());
            int noVan = (FamilyFragment.mVanNo.getText().toString().equals("")) ? 0 : Integer.parseInt(FamilyFragment.mVanNo.getText().toString());

            Family family = new Family();
            family.setNoMale(Integer.parseInt(FamilyFragment.mMaleMember.getText().toString()));
            family.setNoFemale(Integer.parseInt(FamilyFragment.mFemaleMember.getText().toString()));
            family.setYearResided(Integer.parseInt(FamilyFragment.mYearOrigin.getText().toString()));
            family.setPlaceOrigin(FamilyFragment.mPlaceOrigin.getText().toString().trim());
            family.setNoVoters(Integer.parseInt(FamilyFragment.mNoVoters.getText().toString()));
            family.setSelectBicycle(selectBicycle);
            family.setSelectBoat(selectBoat);
            family.setSelectBus(selectBus);
            family.setSelectCar(selectCar);
            family.setSelectJeepney(selectJeep);
            family.setSelectMotorboat(selectMotorboat);
            family.setSelectMotorcycle(selectMotorcycle);
            family.setSelectOwnerJeep(selectOwner);
            family.setSelectPedicab(selectPedicab);
            family.setSelectPickup(selectPickup);
            family.setSelectPumpBoat(selectPump);
            family.setSelectRaft(selectRaft);
            family.setSelectSuv(selectSuv);
            family.setSelectTricycle(selectTricycle);
            family.setSelectTruck(selectTruck);
            family.setSelectVan(selectVan);

            family.setNoBicycle(noBicycle);
            family.setNoBoat(noBoat);
            family.setNoBus(noBus);
            family.setNoCar(noCar);
            family.setNoJeep(noJeep);
            family.setNoMotorboat(noMotorboat);
            family.setNoMotorCycle(noMotorCycle);
            family.setNoOwnerJeep(noOwner);
            family.setNoPedicab(noPedicab);
            family.setNoPickup(noPickup);
            family.setNoPumpBoat(noPump);
            family.setNoRaft(noRaft);
            family.setNoSuv(noSuv);
            family.setNoTricycle(noTricycle);
            family.setNoTruck(noTruck);
            family.setNoVan(noVan);

            DatabaseReference familyFragmentRef = mDatabase.child("family");
            familyFragmentRef.child(key).setValue(family).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                    }
                }
            });
        }
        else {
            Toast.makeText(this, "Please complete Family (2)", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MainSurveyActivity.this, MainActivity.class));
    }

    private void goToIndentification(){
        getSupportActionBar().setTitle("Family Identification");
        FamilyIdentificationFragment familyIdentificationFragment = new FamilyIdentificationFragment();
        mTransaction.replace(R.id.fragmentMain, familyIdentificationFragment);
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }
}
