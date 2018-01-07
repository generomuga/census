package census.com.census.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        mDatabase = FirebaseDatabase.getInstance().getReference();

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
                getSupportActionBar().setTitle("Family Identification");
                FamilyIdentificationFragment familyIdentificationFragment = new FamilyIdentificationFragment();
                mTransaction.replace(R.id.fragmentMain, familyIdentificationFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonFamily:
                getSupportActionBar().setTitle("Family");
                FamilyFragment familyFragment = new FamilyFragment();
                mTransaction.replace(R.id.fragmentMain, familyFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonHealth:
                getSupportActionBar().setTitle("Health");
                HealthFragment healthFragment = new HealthFragment();
                mTransaction.replace(R.id.fragmentMain, healthFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonEnvironment:
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
            //goToIndentification();
            FamilyIdentificationFragment.mFname.setError("Required field");
            return false;
        }
        if (FamilyIdentificationFragment.mMname.getText().toString().equals("")){
            //goToIndentification();
            FamilyIdentificationFragment.mMname.setError("Required field");
            return false;
        }
        if (FamilyIdentificationFragment.mLname.getText().toString().equals("")){
            //goToIndentification();
            FamilyIdentificationFragment.mLname.setError("Required field");
            return false;
        }
        if (FamilyIdentificationFragment.mHouseNo.getText().toString().equals("")){
            //goToIndentification();
            FamilyIdentificationFragment.mHouseNo.setError("Required field");
            return false;
        }
        if (FamilyIdentificationFragment.mStreetNo.getText().toString().equals("")){
            //goToIndentification();
            FamilyIdentificationFragment.mStreetNo.setError("Required field");
            return false;
        }
        return true;
    }

    private boolean checkFamilyFieldsComplete(){
        if (FamilyFragment.mMaleMember.getText().toString().equals("")){
            return false;
        }

        if (FamilyFragment.mFemaleMember.getText().toString().equals("")){
            return false;
        }

        if (FamilyFragment.mYearOrigin.getText().toString().equals("")){
            return false;
        }

        if (FamilyFragment.mPlaceOrigin.getText().toString().equals("")){
            return false;
        }

        if (FamilyFragment.mNoVoters.getText().toString().equals("")){
            return false;
        }

        if (FamilyFragment.mBicycle.isChecked()){
            if (FamilyFragment.mBicycleNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mBoat.isChecked()){
            if (FamilyFragment.mBoatNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mBus.isChecked()){
            if (FamilyFragment.mBusNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mCar.isChecked()){
            if (FamilyFragment.mCarNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mJeep.isChecked()){
            if (FamilyFragment.mJeepNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mMotorBoat.isChecked()){
            if (FamilyFragment.mMotorBoatNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mMotorcycle.isChecked()){
            if (FamilyFragment.mMotorcycleNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mOwner.isChecked()){
            if (FamilyFragment.mOwnerNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mPedicab.isChecked()){
            if (FamilyFragment.mPedicabNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mPickUp.isChecked()){
            if (FamilyFragment.mPickUpNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mPumpboat.isChecked()){
            if (FamilyFragment.mPumpboatNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mRaft.isChecked()){
            if (FamilyFragment.mRaftNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mSuv.isChecked()){
            if (FamilyFragment.mSuvNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mTricycle.isChecked()){
            if (FamilyFragment.mTricycleNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mTruck.isChecked()){
            if (FamilyFragment.mTruckNo.getText().toString().equals("")){
                return false;
            }
        }

        if (FamilyFragment.mVan.isChecked()){
            if (FamilyFragment.mVanNo.getText().toString().equals("")){
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                sendDataIndentification();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendDataIndentification(){

        boolean complete = checkIdentificationFieldsComplete();

        if (complete) {
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

            refFamilyIdentification.push().setValue(familyIdentification).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                    }
                }
            });
        }
        else{
            Toast.makeText(this, "Please go back to family identification", Toast.LENGTH_LONG).show();
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
