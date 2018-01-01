package census.com.census.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import census.com.census.R;

public class FamilyIdentificationFragment extends Fragment {

    private View view;
    public static EditText mFname;
    public static EditText mMname;
    public static EditText mLname;
    public static EditText mHouseNo;
    public static EditText mStreetNo;

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
    }

    private void loadPreference(){
        mFname.setText(mSharedPreference.getString("fname",""));
        mMname.setText(mSharedPreference.getString("mname",""));
        mLname.setText(mSharedPreference.getString("lname",""));
        mHouseNo.setText(mSharedPreference.getString("house",""));
        mStreetNo.setText(mSharedPreference.getString("street", ""));

    }

}





