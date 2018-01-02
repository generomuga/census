package census.com.census.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import census.com.census.R;

public class FamilyIdentificationFragment extends Fragment {

    private View view;
    public static EditText mFname;
    public static EditText mMname;
    public static EditText mLname;
    public static EditText mHouseNo;
    public static EditText mStreetNo;
    public static RadioButton mResident;
    public static RadioButton mNonResident;
    public static RadioButton mOwner;
    public static RadioButton mExtended;
    public static RadioButton mActive;
    public static RadioButton mInActive;

    SharedPreferences mSharedPreference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        //shared pref
        mSharedPreference = getActivity().getSharedPreferences("census.com.census",Context.MODE_PRIVATE);

        mFname = (EditText) view.findViewById(R.id.editTextFname);
        mMname = (EditText) view.findViewById(R.id.editTextMname);
        mLname = (EditText) view.findViewById(R.id.editTextLname);
        mHouseNo = (EditText) view.findViewById(R.id.editTextHouseNo);
        mStreetNo = (EditText) view.findViewById(R.id.editTextStreetNo);

        mResident = (RadioButton) view.findViewById(R.id.radioButtonResident);
        mNonResident = (RadioButton) view.findViewById(R.id.radioButtonNonResident);
        mOwner = (RadioButton) view.findViewById(R.id.radioButtonOwner);
        mExtended = (RadioButton) view.findViewById(R.id.radioButtonExtended);
        mActive = (RadioButton) view.findViewById(R.id.radioButtonActive);
        mInActive = (RadioButton) view.findViewById(R.id.radioButtonInactive);

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

}





