package census.com.census.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import census.com.census.FamilyIdentification;
import census.com.census.R;
import census.com.census.activity.MainSurveyActivity;

public class FamilyIdentificationFragment extends Fragment {

    //views
    private View view;
    public static EditText mFname;
    public static EditText mMname;
    public static EditText mLname;
    public static EditText mRegion;
    public static EditText mProvince;
    public static EditText mMunicipality;
    public static EditText mBarangay;
    public static EditText mHouseNo;
    public static EditText mStreetNo;
    public static RadioButton mResident;
    public static RadioButton mNonResident;
    public static RadioButton mOwner;
    public static RadioButton mExtended;
    public static RadioButton mActive;
    public static RadioButton mInActive;

    //shared pref
    SharedPreferences mSharedPreference;

    //firebase
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        //shared pref
        mSharedPreference = getActivity().getSharedPreferences("census.com.census",Context.MODE_PRIVATE);

        mFname = (EditText) view.findViewById(R.id.editTextFname);
        mMname = (EditText) view.findViewById(R.id.editTextMname);
        mLname = (EditText) view.findViewById(R.id.editTextLname);
        mRegion = (EditText) view.findViewById(R.id.editTextRegion);
        mProvince = (EditText) view.findViewById(R.id.editTextProvince);
        mMunicipality = (EditText) view.findViewById(R.id.editTextMunicipality);
        mBarangay = (EditText) view.findViewById(R.id.editTextBarangay);
        mHouseNo = (EditText) view.findViewById(R.id.editTextHouseNo);
        mStreetNo = (EditText) view.findViewById(R.id.editTextStreetNo);

        mResident = (RadioButton) view.findViewById(R.id.radioButtonResident);
        mNonResident = (RadioButton) view.findViewById(R.id.radioButtonNonResident);
        mOwner = (RadioButton) view.findViewById(R.id.radioButtonOwner);
        mExtended = (RadioButton) view.findViewById(R.id.radioButtonExtended);
        mActive = (RadioButton) view.findViewById(R.id.radioButtonActive);
        mInActive = (RadioButton) view.findViewById(R.id.radioButtonInactive);

        mFname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    if (mFname.getText().toString().equals("")) {
                        mFname.setError("Required field");
                    }
                    else{
                        mFname.setError(null);
                    }
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mMname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    if (mMname.getText().toString().equals("")) {
                        mMname.setError("Required field");
                    }
                    else{
                        mMname.setError(null);
                    }
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mLname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    if (mLname.getText().toString().equals("")) {
                        mLname.setError("Required field");
                    }
                    else{
                        mLname.setError(null);
                    }
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mHouseNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    if (mHouseNo.getText().toString().equals("")) {
                        mHouseNo.setError("Required field");
                    }
                    else{
                        mHouseNo.setError(null);
                    }
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mStreetNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    if (mStreetNo.getText().toString().equals("")) {
                        mStreetNo.setError("Required field");
                    }
                    else{
                        mStreetNo.setError(null);
                    }
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mNonResident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mExtended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        mInActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkComplete()){
                    MainSurveyActivity.mFamily.setEnabled(true);
                }
                else{
                    MainSurveyActivity.mFamily.setEnabled(false);
                }
            }
        });

        return view;
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
        mSharedPreference.edit().putString("fname", mFname.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("mname", mMname.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("lname", mLname.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("house", mHouseNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putString("street", mStreetNo.getText().toString().trim()).apply();
        mSharedPreference.edit().putBoolean("resident", mResident.isChecked()).apply();
        mSharedPreference.edit().putBoolean("nonResident", mNonResident.isChecked()).apply();
        mSharedPreference.edit().putBoolean("owner", mOwner.isChecked()).apply();
        mSharedPreference.edit().putBoolean("extended", mExtended.isChecked()).apply();
        mSharedPreference.edit().putBoolean("active", mActive.isChecked()).apply();
        mSharedPreference.edit().putBoolean("inactive", mInActive.isChecked()).apply();
    }

    private void loadPreference(){
        mFname.setText(mSharedPreference.getString("fname",""));
        mMname.setText(mSharedPreference.getString("mname",""));
        mLname.setText(mSharedPreference.getString("lname",""));
        mHouseNo.setText(mSharedPreference.getString("house",""));
        mStreetNo.setText(mSharedPreference.getString("street", ""));

        mResident.setChecked(mSharedPreference.getBoolean("resident",true));
        mNonResident.setChecked(mSharedPreference.getBoolean("nonResident",false));
        mOwner.setChecked(mSharedPreference.getBoolean("owner",true));
        mExtended.setChecked(mSharedPreference.getBoolean("extended",false));
        mActive.setChecked(mSharedPreference.getBoolean("active",true));
        mInActive.setChecked(mSharedPreference.getBoolean("inactive",false));
    }

    private boolean checkComplete(){
        if (mFname.getText().toString().equals("")){
            return false;
        }
        if (mMname.getText().toString().equals("")){
            return false;
        }
        if (mLname.getText().toString().equals("")){
            return false;
        }
        if (mHouseNo.getText().toString().equals("")){
            return false;
        }
        if (mStreetNo.getText().toString().equals("")){
            return false;
        }
        return true;
    }

}





