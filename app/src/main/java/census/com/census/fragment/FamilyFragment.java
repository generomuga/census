package census.com.census.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import census.com.census.Family;
import census.com.census.R;
import census.com.census.activity.MainSurveyActivity;

import static census.com.census.R.*;

public class FamilyFragment extends Fragment {

    //views
    public static CheckBox mBicycle;
    public static CheckBox mBoat;
    public static CheckBox mBus;
    public static CheckBox mCar;
    public static CheckBox mJeep;
    public static CheckBox mMotorBoat;
    public static CheckBox mMotorcycle;
    public static CheckBox mOwner;
    public static CheckBox mPedicab;
    public static CheckBox mPickUp;
    public static CheckBox mPumpboat;
    public static CheckBox mRaft;
    public static CheckBox mSuv;
    public static CheckBox mTricycle;
    public static CheckBox mTruck;
    public static CheckBox mVan;

    public static EditText mBicycleNo;
    public static EditText mBoatNo;
    public static EditText mBusNo;
    public static EditText mCarNo;
    public static EditText mJeepNo;
    public static EditText mMotorBoatNo;
    public static EditText mMotorcycleNo;
    public static EditText mOwnerNo;
    public static EditText mPedicabNo;
    public static EditText mPickUpNo;
    public static EditText mPumpboatNo;
    public static EditText mRaftNo;
    public static EditText mSuvNo;
    public static EditText mTricycleNo;
    public static EditText mTruckNo;
    public static EditText mVanNo;

    public static EditText mMaleMember;
    public static EditText mFemaleMember;
    public static EditText mYearOrigin;
    public static EditText mPlaceOrigin;
    public static EditText mNoVoters;

    //firebase
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    //shared pref
    public static SharedPreferences mSharedPreference;

    private View view;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(layout.fragment_family,container,false);

        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //shared pref
        mSharedPreference = getActivity().getSharedPreferences("census.com.census", Context.MODE_PRIVATE);

        mBicycleNo =  (EditText) view.findViewById(id.editTextBicycleNo);
        mBoatNo = (EditText) view.findViewById(id.editTextBoatNo);
        mBusNo = (EditText) view.findViewById(id.editTextBusNo);
        mCarNo = (EditText) view.findViewById(id.editTextCarNo);
        mJeepNo = (EditText) view.findViewById(id.editTextJeepNo);
        mMotorBoatNo = (EditText) view.findViewById(id.editTextMotorBoatNo);
        mMotorcycleNo = (EditText) view.findViewById(id.editTextMotorcycleNo);
        mOwnerNo = (EditText) view.findViewById(id.editTextOwnerNo);
        mPedicabNo = (EditText) view.findViewById(id.editTextPedicabNo);
        mPickUpNo = (EditText) view.findViewById(id.editTextPickUpNo);
        mPumpboatNo = (EditText) view.findViewById(id.editTextPumpBoatNo);
        mRaftNo = (EditText) view.findViewById(id.editTextRaftNo);
        mSuvNo = (EditText) view.findViewById(id.editTextSuvNo);
        mTricycleNo = (EditText) view.findViewById(id.editTextTricycleNo);
        mTruckNo = (EditText) view.findViewById(id.editTextTruckNo);
        mVanNo = (EditText) view.findViewById(id.editTextVanNo);

        mBicycleNo.setEnabled(false);
        mBoatNo.setEnabled(false);
        mBusNo.setEnabled(false);
        mCarNo.setEnabled(false);
        mJeepNo.setEnabled(false);
        mMotorBoatNo.setEnabled(false);
        mMotorcycleNo.setEnabled(false);
        mOwnerNo.setEnabled(false);
        mPedicabNo.setEnabled(false);
        mPickUpNo.setEnabled(false);
        mPumpboatNo.setEnabled(false);
        mRaftNo.setEnabled(false);
        mSuvNo.setEnabled(false);
        mTricycleNo.setEnabled(false);
        mTruckNo.setEnabled(false);
        mVanNo.setEnabled(false);

        mBicycle =  (CheckBox) view.findViewById(id.checkboxBicycle);
        mBoat = (CheckBox) view.findViewById(id.checkboxBoat);
        mBus = (CheckBox) view.findViewById(id.checkboxBus);
        mCar = (CheckBox) view.findViewById(id.checkboxCar);
        mJeep = (CheckBox) view.findViewById(id.checkboxJeep);
        mMotorBoat = (CheckBox) view.findViewById(id.checkboxMotorBoat);
        mMotorcycle = (CheckBox) view.findViewById(id.checkboxMotorcycle);
        mOwner = (CheckBox) view.findViewById(id.checkboxOwner);
        mPedicab = (CheckBox) view.findViewById(id.checkboxPedicab);
        mPickUp = (CheckBox) view.findViewById(id.checkboxPickUp);
        mPumpboat = (CheckBox) view.findViewById(id.checkboxPumpBoat);
        mRaft = (CheckBox)   view.findViewById(id.checkboxRaft);
        mSuv = (CheckBox) view.findViewById(id.checkboxSuv);
        mTricycle = (CheckBox) view.findViewById(id.checkboxTricycle);
        mTruck = (CheckBox) view.findViewById(id.checkboxTruck);
        mVan = (CheckBox) view.findViewById(id.checkboxVan);

        mMaleMember = (EditText) view.findViewById(id.editTextNoMaleMember);
        mFemaleMember = (EditText) view.findViewById(id.editTextNoFemaleMember);
        mYearOrigin = (EditText) view.findViewById(id.editTextYearResided);
        mPlaceOrigin = (EditText) view.findViewById(id.editTextOrigin);
        mNoVoters = (EditText) view.findViewById(id.editTextNoVoters);


        mMaleMember.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mMaleMember.getText().toString().equals("")) {
                        mMaleMember.setError("Required field");
                    }
                    else{
                        mMaleMember.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mFemaleMember.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mFemaleMember.getText().toString().equals("")) {
                        mFemaleMember.setError("Required field");
                    }
                    else{
                        mFemaleMember.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mYearOrigin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mYearOrigin.getText().toString().equals("")) {
                        mYearOrigin.setError("Required field");
                    }
                    else{
                        if (Integer.parseInt(mYearOrigin.getText().toString()) >= 1900 && Integer.parseInt(mYearOrigin.getText().toString()) <= 2018){
                            mYearOrigin.setError(null);
                        }
                        else
                        {
                            mYearOrigin.setError("Invalid year");
                        }
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mPlaceOrigin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mPlaceOrigin.getText().toString().equals("")) {
                        mPlaceOrigin.setError("Required field");
                    }
                    else{
                        mPlaceOrigin.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mNoVoters.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mNoVoters.getText().toString().equals("")) {
                        mNoVoters.setError("Required field");
                    }
                    else{
                        mNoVoters.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox bicycle
        mBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBicycle.isChecked()){
                    mBicycleNo.setEnabled(true);
                }
                else{
                    mBicycleNo.setText(null);
                    mBicycleNo.setError(null);
                    mBicycleNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mBicycleNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mBicycleNo.getText().toString().equals("")) {
                        mBicycleNo.setError("Required field");
                    }
                    else{
                        mBicycleNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox boat
        mBoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBoat.isChecked()){
                    mBoatNo.setEnabled(true);
                }
                else{
                    mBoatNo.setText(null);
                    mBoatNo.setError(null);
                    mBoatNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mBoatNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mBoatNo.getText().toString().equals("")) {
                        mBoatNo.setError("Required field");
                    }
                    else{
                        mBoatNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox bus
        mBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBus.isChecked()){
                    mBusNo.setEnabled(true);
                }
                else{
                    mBusNo.setText(null);
                    mBusNo.setError(null);
                    mBusNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mBusNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mBusNo.getText().toString().equals("")) {
                        mBusNo.setError("Required field");
                    }
                    else{
                        mBusNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox car
        mCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCar.isChecked()){
                    mCarNo.setEnabled(true);
                }
                else{
                    mCarNo.setText(null);
                    mCarNo.setError(null);
                    mCarNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mCarNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mCarNo.getText().toString().equals("")) {
                        mCarNo.setError("Required field");
                    }
                    else{
                        mCarNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox jeep
        mJeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mJeep.isChecked()){
                    mJeepNo.setEnabled(true);
                }
                else{
                    mJeepNo.setText(null);
                    mJeepNo.setError(null);
                    mJeepNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mJeepNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mJeepNo.getText().toString().equals("")) {
                        mJeepNo.setError("Required field");
                    }
                    else{
                        mJeepNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox motorboat
        mMotorBoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMotorBoat.isChecked()){
                    mMotorBoatNo.setEnabled(true);
                }
                else{
                    mMotorBoatNo.setText(null);
                    mMotorBoatNo.setError(null);
                    mMotorBoatNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mMotorBoatNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mMotorBoatNo.getText().toString().equals("")) {
                        mMotorBoatNo.setError("Required field");
                    }
                    else{
                        mMotorBoatNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox motorcycle
        mMotorcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMotorcycle.isChecked()){
                    mMotorcycleNo.setEnabled(true);
                }
                else{
                    mMotorcycleNo.setText(null);
                    mMotorcycleNo.setError(null);
                    mMotorcycleNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mMotorcycleNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mMotorcycleNo.getText().toString().equals("")) {
                        mMotorcycleNo.setError("Required field");
                    }
                    else{
                        mMotorcycleNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox owner
        mOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOwner.isChecked()){
                    mOwnerNo.setEnabled(true);
                }
                else{
                    mOwnerNo.setText(null);
                    mOwnerNo.setError(null);
                    mOwnerNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mOwnerNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mOwnerNo.getText().toString().equals("")) {
                        mOwnerNo.setError("Required field");
                    }
                    else{
                        mOwnerNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox pedicab
        mPedicab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPedicab.isChecked()){
                    mPedicabNo.setEnabled(true);
                }
                else{
                    mPedicabNo.setText(null);
                    mPedicabNo.setError(null);
                    mPedicabNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mPedicabNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mPedicabNo.getText().toString().equals("")) {
                        mPedicabNo.setError("Required field");
                    }
                    else{
                        mPedicabNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox pedicab
        mPickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPickUp.isChecked()){
                    mPickUpNo.setEnabled(true);
                }
                else{
                    mPickUpNo.setText(null);
                    mPickUpNo.setError(null);
                    mPickUpNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mPickUpNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mPickUpNo.getText().toString().equals("")) {
                        mPickUpNo.setError("Required field");
                    }
                    else{
                        mPickUpNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox pumpboat
        mPumpboat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPumpboat.isChecked()){
                    mPumpboatNo.setEnabled(true);
                }
                else{
                    mPumpboatNo.setText(null);
                    mPumpboatNo.setError(null);
                    mPumpboatNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mPumpboatNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mPumpboatNo.getText().toString().equals("")) {
                        mPumpboatNo.setError("Required field");
                    }
                    else{
                        mPumpboatNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox raft
        mRaft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRaft.isChecked()){
                    mRaftNo.setEnabled(true);
                }
                else{
                    mRaftNo.setText(null);
                    mRaftNo.setError(null);
                    mRaftNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mRaftNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mRaftNo.getText().toString().equals("")) {
                        mRaftNo.setError("Required field");
                    }
                    else{
                        mRaftNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox suv
        mSuv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSuv.isChecked()){
                    mSuvNo.setEnabled(true);
                }
                else{
                    mSuvNo.setText(null);
                    mSuvNo.setError(null);
                    mSuvNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mSuvNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mSuvNo.getText().toString().equals("")) {
                        mSuvNo.setError("Required field");
                    }
                    else{
                        mSuvNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox tricycle
        mTricycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTricycle.isChecked()){
                    mTricycleNo.setEnabled(true);
                }
                else{
                    mTricycleNo.setText(null);
                    mTricycleNo.setError(null);
                    mTricycleNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mTricycleNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mTricycleNo.getText().toString().equals("")) {
                        mTricycleNo.setError("Required field");
                    }
                    else{
                        mTricycleNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox truck
        mTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTruck.isChecked()){
                    mTruckNo.setEnabled(true);
                }
                else{
                    mTruckNo.setText(null);
                    mTruckNo.setError(null);
                    mTruckNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mTruckNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mTruckNo.getText().toString().equals("")) {
                        mTruckNo.setError("Required field");
                    }
                    else{
                        mTruckNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        //checkbox van
        mVan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVan.isChecked()){
                    mVanNo.setEnabled(true);
                }
                else{
                    mVanNo.setText(null);
                    mVanNo.setError(null);
                    mVanNo.setEnabled(false);
                }
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        mVanNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (checkFamilyFieldsComplete()){
                    MainSurveyActivity.mHealth.setEnabled(true);
                }
                else{
                    if (mVanNo.getText().toString().equals("")) {
                        mVanNo.setError("Required field");
                    }
                    else{
                        mVanNo.setError(null);
                    }
                    MainSurveyActivity.mHealth.setEnabled(false);
                }
            }
        });

        return  view;
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreference();
        //sendData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreference();
    }

    private void savePreference(){
        mSharedPreference.edit().putString("noMale", mMaleMember.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("noFemale", mFemaleMember.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("yearResided", mYearOrigin.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("placeOrigin", mPlaceOrigin.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("noVoters", mNoVoters.getText().toString().trim()).apply();

        mSharedPreference.edit().putBoolean("checkBicycle", mBicycle.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkBoat", mBoat.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkBus", mBus.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkCar", mCar.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkJeep", mJeep.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkMotorboat", mMotorBoat.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkMotorcycle", mMotorcycle.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkOwner", mOwner.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkPedicab", mPedicab.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkPickup", mPickUp.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkPumpBoat", mPumpboat.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkRaft", mRaft.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkSuv", mSuv.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkTricycle", mTricycle.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkTruck", mTruck.isChecked()).apply();
        mSharedPreference.edit().putBoolean("checkVan", mVan.isChecked()).apply();

        mSharedPreference.edit().putString("bicycleNo", mBicycleNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("boatNo", mBoatNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("busNo", mBusNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("carNo", mCarNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("jeepNo", mJeepNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("motorBoatNo", mMotorBoatNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("motorCycleNo", mMotorcycleNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("ownerNo", mOwnerNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("pedicabNo", mPedicabNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("pickUpNo", mPickUpNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("pumpBoatNo", mPumpboatNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("raftNo", mRaftNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("suvNo", mSuvNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("tricycleNo", mTricycleNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("truckNo", mTruckNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("vanNo", mVanNo.getText().toString().trim()).apply();
    }

    private void loadPreference(){
        mMaleMember.setText(mSharedPreference.getString("noMale",""));
        mFemaleMember.setText(mSharedPreference.getString("noFemale",""));
        mYearOrigin.setText(mSharedPreference.getString("yearResided",""));
        mPlaceOrigin.setText(mSharedPreference.getString("placeOrigin",""));
        mNoVoters.setText(mSharedPreference.getString("noVoters", ""));

        mBicycle.setChecked(mSharedPreference.getBoolean("checkBicycle",false));
        mBoat.setChecked(mSharedPreference.getBoolean("checkBoat",false));
        mBus.setChecked(mSharedPreference.getBoolean("checkBus",false));
        mCar.setChecked(mSharedPreference.getBoolean("checkCar",false));
        mJeep.setChecked(mSharedPreference.getBoolean("checkJeep",false));
        mMotorBoat.setChecked(mSharedPreference.getBoolean("checkMotorboat",false));
        mMotorcycle.setChecked(mSharedPreference.getBoolean("checkMotorcycle",false));
        mOwner.setChecked(mSharedPreference.getBoolean("checkOwner",false));
        mPedicab.setChecked(mSharedPreference.getBoolean("checkPedicab",false));
        mPickUp.setChecked(mSharedPreference.getBoolean("checkPickup",false));
        mPumpboat.setChecked(mSharedPreference.getBoolean("checkPumpBoat",false));
        mRaft.setChecked(mSharedPreference.getBoolean("checkRaft",false));
        mSuv.setChecked(mSharedPreference.getBoolean("checkSuv",false));
        mTricycle.setChecked(mSharedPreference.getBoolean("checkTricycle",false));
        mTruck.setChecked(mSharedPreference.getBoolean("checkTruck",false));
        mVan.setChecked(mSharedPreference.getBoolean("checkVan",false));

        mBicycleNo.setText(mSharedPreference.getString("bicycleNo", ""));
        mBoatNo.setText(mSharedPreference.getString("boatNo", ""));
        mBusNo.setText(mSharedPreference.getString("busNo", ""));
        mCarNo.setText(mSharedPreference.getString("carNo", ""));
        mJeepNo.setText(mSharedPreference.getString("jeepNo", ""));
        mMotorBoatNo.setText(mSharedPreference.getString("motorBoatNo", ""));
        mMotorcycleNo.setText(mSharedPreference.getString("motorCycleNo", ""));
        mOwnerNo.setText(mSharedPreference.getString("ownerNo", ""));
        mPedicabNo.setText(mSharedPreference.getString("pedicabNo", ""));
        mPickUpNo.setText(mSharedPreference.getString("pickUpNo", ""));
        mPumpboatNo.setText(mSharedPreference.getString("pumpBoatNo", ""));
        mRaftNo.setText(mSharedPreference.getString("raftNo", ""));
        mSuvNo.setText(mSharedPreference.getString("suvNo", ""));
        mTricycleNo.setText(mSharedPreference.getString("tricycleNo", ""));
        mTruckNo.setText(mSharedPreference.getString("truckNo", ""));
        mVanNo.setText(mSharedPreference.getString("vanNo", ""));
    }

    private boolean checkFamilyFieldsComplete(){

        if (mMaleMember.getText().toString().equals("")) {
            return false;
        }

        if (mFemaleMember.getText().toString().equals("")) {
            return false;
        }

        if (mYearOrigin.getText().toString().equals("")) {
            return false;
        }

        if (mPlaceOrigin.getText().toString().equals("")) {
            return false;
        }

        if (mNoVoters.getText().toString().equals("")) {
            return false;
        }

        if (mBicycle.isChecked()) {
            if (mBicycleNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (mBoat.isChecked()) {
            if (mBoatNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (mBus.isChecked()) {
            if (mBusNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mCar.isChecked()) {
            if (FamilyFragment.mCarNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mJeep.isChecked()) {
            if (FamilyFragment.mJeepNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mMotorBoat.isChecked()) {
            if (FamilyFragment.mMotorBoatNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mMotorcycle.isChecked()) {
            if (FamilyFragment.mMotorcycleNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mOwner.isChecked()) {
            if (FamilyFragment.mOwnerNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mPedicab.isChecked()) {
            if (FamilyFragment.mPedicabNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mPickUp.isChecked()) {
            if (FamilyFragment.mPickUpNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mPumpboat.isChecked()) {
            if (FamilyFragment.mPumpboatNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mRaft.isChecked()) {
            if (FamilyFragment.mRaftNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mSuv.isChecked()) {
            if (FamilyFragment.mSuvNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mTricycle.isChecked()) {
            if (FamilyFragment.mTricycleNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mTruck.isChecked()) {
            if (FamilyFragment.mTruckNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (FamilyFragment.mVan.isChecked()) {
            if (FamilyFragment.mVanNo.getText().toString().equals("")) {
                return false;
            }
        }

        if (Integer.parseInt(FamilyFragment.mYearOrigin.getText().toString()) >= 1900 && Integer.parseInt(FamilyFragment.mYearOrigin.getText().toString()) <= 2018){
        }
        else
        {
            return false;
        }


        return true;
    }

}
