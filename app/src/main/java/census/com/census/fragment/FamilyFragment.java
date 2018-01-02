package census.com.census.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

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
    public static CheckBox mPickup;
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
    public static EditText mPickupNo;
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

    //shared pref
    SharedPreferences mSharedPreference;

    private View view;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(layout.fragment_family,container,false);

        //shared pref
        mSharedPreference = getActivity().getSharedPreferences("census.com.census", Context.MODE_PRIVATE);

        mBicycleNo =  (EditText) view.findViewById(id.editTextBicycleNo);
        mBoatNo = (EditText) view.findViewById(id.editTextBoatNo);
        mBusNo = (EditText) view.findViewById(id.editTextBusNo);
        mCarNo = (EditText) view.findViewById(id.editTextCarNo);
        mJeepNo = (EditText) view.findViewById(id.editTextJeepNo);
        mMotorBoatNo = (EditText) view.findViewById(id.editTextBoatNo);
        mMotorcycleNo = (EditText) view.findViewById(id.editTextMotorcycleNo);
        mOwnerNo = (EditText) view.findViewById(id.editTextOwnerNo);
        mPedicabNo = (EditText) view.findViewById(id.editTextPedicabNo);
        mPickupNo = (EditText) view.findViewById(id.editTextPickUpNo);
        mPumpboatNo = (EditText) view.findViewById(id.editTextPumpBoatNo);
        mRaftNo = (EditText) view.findViewById(id.editTextRaftNo);
        mSuvNo = (EditText) view.findViewById(id.editTextSuvNo);
        mTricycleNo = (EditText) view.findViewById(id.editTextTricycleNo);
        mTruckNo = (EditText) view.findViewById(id.editTextTruckNo);
        mVanNo = (EditText) view.findViewById(id.editTextVanNo);

        mBicycle =  (CheckBox) view.findViewById(id.checkboxBicycle);
        mBoat = (CheckBox) view.findViewById(id.checkboxBoat);
        mBus = (CheckBox) view.findViewById(id.checkboxBus);
        mCar = (CheckBox) view.findViewById(id.checkboxCar);
        mJeep = (CheckBox) view.findViewById(id.checkboxJeep);
        mMotorBoat = (CheckBox) view.findViewById(id.checkboxMotorBoat);
        mMotorcycle = (CheckBox) view.findViewById(id.checkboxMotorcycle);
        mOwner = (CheckBox) view.findViewById(id.checkboxOwner);
        mPedicab = (CheckBox) view.findViewById(id.checkboxPedicab);
        mPickup = (CheckBox) view.findViewById(id.checkboxPickup);
        mPumpboat = (CheckBox) view.findViewById(id.checkboxPumpBoat);
        mRaft = (CheckBox) view.findViewById(id.checkboxRaft);
        mSuv = (CheckBox) view.findViewById(id.checkboxSuv);
        mTricycle = (CheckBox) view.findViewById(id.checkboxTricycle);
        mTruck = (CheckBox) view.findViewById(id.checkboxTruck);
        mVan = (CheckBox) view.findViewById(id.checkboxVan);

        mMaleMember = (EditText) view.findViewById(id.editTextNoMaleMember);
        mFemaleMember = (EditText) view.findViewById(id.editTextNoFemaleMember);
        mYearOrigin = (EditText) view.findViewById(id.editTextYearResided);
        mPlaceOrigin = (EditText) view.findViewById(id.editTextOrigin);
        mNoVoters = (EditText) view.findViewById(id.editTextNoVoters);

        return  view;
    }

    @Override
    public void onPause() {
        super.onPause();
        savePreference();
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
    }

    private void loadPreference(){
        mMaleMember.setText(mSharedPreference.getString("noMale",""));
        mFemaleMember.setText(mSharedPreference.getString("noFemale",""));
        mYearOrigin.setText(mSharedPreference.getString("yearResided",""));
        mPlaceOrigin.setText(mSharedPreference.getString("placeOrigin",""));
        mNoVoters.setText(mSharedPreference.getString("noVoters", ""));
    }

}
