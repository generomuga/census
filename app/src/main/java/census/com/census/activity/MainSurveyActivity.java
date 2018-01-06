package census.com.census.activity;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import census.com.census.Environment;
import census.com.census.Family;
import census.com.census.R;
import census.com.census.fragment.EnvironmentFragment;
import census.com.census.fragment.FamilyFragment;
import census.com.census.fragment.FamilyIdentificationFragment;
import census.com.census.fragment.HealthFragment;

public class MainSurveyActivity extends AppCompatActivity {

    //views
    private Toolbar mToolBarSurvey;

    //fragments
    FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

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
                //mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonFamily:
                //check if the fields are complete
                boolean isIdentificationComplete = checkIdentificationFieldsComplete();
                if (!isIdentificationComplete){
                    Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                else {
                    getSupportActionBar().setTitle("Family");
                    FamilyFragment familyFragment = new FamilyFragment();
                    mTransaction.replace(R.id.fragmentMain, familyFragment);
                    //mTransaction.addToBackStack(null);
                    mTransaction.commit();
                }
                break;

            case R.id.imageButtonHealth:
                //check if the fields are complete
                boolean isFamilyComplete = checkFamilyFieldsComplete();
                boolean isValidYear = checkYear();
                if (!isFamilyComplete){
                    Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!isValidYear){
                    Toast.makeText(this, "Not valid year!", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    getSupportActionBar().setTitle("Health");
                    HealthFragment healthFragment = new HealthFragment();
                    mTransaction.replace(R.id.fragmentMain, healthFragment);
                    //mTransaction.addToBackStack(null);
                    mTransaction.commit();
                }
                break;

            case R.id.imageButtonEnvironment:
                getSupportActionBar().setTitle("Environment");
                EnvironmentFragment environmentFragment = new EnvironmentFragment();
                mTransaction.replace(R.id.fragmentMain, environmentFragment);
                //mTransaction.addToBackStack(null);
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

}
