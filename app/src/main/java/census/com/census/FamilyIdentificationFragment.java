package census.com.census;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FamilyIdentificationFragment extends Fragment {

    private View view;
    /*public static EditText editTextFName;
    public static EditText editTextMName;
    public static EditText editTextLName;
    public static EditText editTextHouseNo;
    public static EditText editTextStreetNo;
    public static EditText editTextBarangay;
    public static EditText editTextMunicipality;
    public static EditText editTextProvince;
    public static RadioButton radioButtonResident;
    public static RadioButton radioButtonNonResident;
    public static RadioButton radioButtonOwner;
    public static RadioButton radioButtonExtended;
    public static RadioButton radioButtonActive;
    public static RadioButton radioButtonInactive;
    public static Spinner spinnerRegions;
    public static Spinner spinnerProvinces;
    public static Spinner spinnerMunicipal;
    public static Spinner spinnerBarangay;*/
    private EditText editTextFName;
    private EditText editTextMName;
    private EditText editTextLName;
    private EditText editTextHouseNo;
    private EditText editTextStreetNo;
    private EditText editTextBarangay;
    private EditText editTextMunicipality;
    private EditText editTextProvince;
    private RadioButton radioButtonResident;
    private RadioButton radioButtonNonResident;
    private RadioButton radioButtonOwner;
    private RadioButton radioButtonExtended;
    private RadioButton radioButtonActive;
    private RadioButton radioButtonInactive;
    private Spinner spinnerRegions;
    private Spinner spinnerProvinces;
    private Spinner spinnerMunicipal;
    private Spinner spinnerBarangay;

    private Button buttonSave;

    private DbUtils dbUtils;

    private ArrayAdapter spinnerArrayAdapter;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences sharedPreferences;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener = (OnFragmentInteractionListener) activity;
            mListener.onFragmentInteraction("Gene");
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
        //mListener = (OnFragmentInteractionListener) activity;
        //mListener = (OnFragmentInteractionListener) getActivity();
        //mListener.onFragmentInteraction("Gene");

    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadData();
    }

    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(String uri);
    }

    public void onFragmentInteraction(String uri){
        Toast.makeText(getActivity(),uri,Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        //init views
        initViews();

        //load data from shared preferences
        //onLoadData();

        //to load regions from sql
        loadRegions();

        //select region
        spinnerRegionEvent();

        //select province
        spinnerProvinceEvent();

        //select municipal
        spinnerMunicipalEvent();

        //shared preferences
        //sampleSP();


        //onSaveData();

        return view;
    }

    private void initViews(){
        editTextFName = (EditText) view.findViewById(R.id.editTextFname);
        editTextMName = (EditText) view.findViewById(R.id.editTextMname);
        editTextLName = (EditText) view.findViewById(R.id.editTextLname);
        editTextHouseNo = (EditText) view.findViewById(R.id.editTextHouseNo);
        editTextStreetNo = (EditText) view.findViewById(R.id.editTextStreetNo);
        radioButtonResident = (RadioButton) view.findViewById(R.id.radioButtonResident);
        radioButtonNonResident = (RadioButton) view.findViewById(R.id.radioButtonNonResident);
        radioButtonOwner = (RadioButton) view.findViewById(R.id.radioButtonOwner);
        radioButtonExtended = (RadioButton) view.findViewById(R.id.radioButtonExtended);
        radioButtonActive = (RadioButton) view.findViewById(R.id.radioButtonActive);
        radioButtonInactive = (RadioButton) view.findViewById(R.id.radioButtonInactive);
        spinnerRegions = (Spinner) view.findViewById(R.id.spinnerRegions);
        spinnerProvinces = (Spinner) view.findViewById(R.id.spinnerProvince);
        spinnerMunicipal = (Spinner) view.findViewById(R.id.spinnerMunicipal);
        spinnerBarangay = (Spinner) view.findViewById(R.id.spinnerBarangay);

        buttonSave = (Button) view.findViewById(R.id.buttonSave);

    }

    private void loadRegions(){
        ArrayList listRegion;
        String query = "SELECT DISTINCT(region) FROM locations";
        dbUtils = new DbUtils(getActivity());
        listRegion = dbUtils.queryLocation(query,"region");

        if(!listRegion.isEmpty()) {
            useArrayAdapter(listRegion);
            spinnerRegions.setAdapter(spinnerArrayAdapter);
        }
        else{
            Log.i("regions:","empty");
        }
    }

    private void spinnerRegionEvent(){
        spinnerRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getProvinces(spinnerRegions.getSelectedItem().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void getProvinces(String region){
        String query = "SELECT province FROM locations where region='"+region+"'";
        ArrayList listProvince;
        dbUtils = new DbUtils(getActivity());
        listProvince = dbUtils.queryLocation(query,"province");
        if(!listProvince.isEmpty()) {
            useArrayAdapter(listProvince);
            spinnerProvinces.setAdapter(spinnerArrayAdapter);
        }
        else{
            Log.i("provinces:","empty");
        }
    }

    private void spinnerProvinceEvent(){
        spinnerProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMunicipal(spinnerProvinces.getSelectedItem().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void getMunicipal(String province){
        String query = "SELECT municipality FROM locations where province='"+province+"'";
        ArrayList listMunicipal;
        dbUtils = new DbUtils(getActivity());
        listMunicipal = dbUtils.queryLocation(query,"municipality");
        if(!listMunicipal.isEmpty()) {
            useArrayAdapter(listMunicipal);
            spinnerMunicipal.setAdapter(spinnerArrayAdapter);
        }
        else{
            Log.i("municipals:","empty");
        }
    }

    private void spinnerMunicipalEvent(){
        spinnerMunicipal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getBarangay(spinnerMunicipal.getSelectedItem().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void getBarangay(String municipal){
        String query = "SELECT barangay FROM locations where municipality='"+municipal+"'";
        ArrayList listBarangay;
        dbUtils = new DbUtils(getActivity());
        listBarangay = dbUtils.queryLocation(query,"barangay");
        if(!listBarangay.isEmpty()) {
            useArrayAdapter(listBarangay);
            spinnerBarangay.setAdapter(spinnerArrayAdapter);
        }
        else{
            Log.i("barangays:","empty");
        }
    }

    private void useArrayAdapter(ArrayList arrayList){
        spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, arrayList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    }

    private void onSaveData(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveReference();
            }
        });
    }

    private void onLoadData(){
        sharedPreferences = getActivity().getSharedPreferences("census.com.census",Context.MODE_PRIVATE);
        //sharedPreferences.edit().remove("fname").apply();
        //sharedPreferences.edit().putString("fname","Gene Romuga").apply();

        //String fname = sharedPreferences.getString("fname","");

        editTextFName.setText(sharedPreferences.getString("fname",""));
        editTextMName.setText(sharedPreferences.getString("mname",""));
        editTextLName.setText(sharedPreferences.getString("lname",""));
        editTextHouseNo.setText(sharedPreferences.getString("houseno",""));
        editTextStreetNo.setText(sharedPreferences.getString("streetno",""));
        /*editTextBarangay.setText(sharedPreferences.getString("barangay",""));
        /*editTextMunicipality.setText(sharedPreferences.getString("municipality",""));
        editTextMunicipality.setText(sharedPreferences.getString("province",""));*/

        //sharedPreferences.edit().putString("province",editTextMunicipality.getText().toString());
    }

    private void onSaveReference(){
        sharedPreferences.edit().putString("fname",editTextFName.getText().toString().trim()).apply();
        sharedPreferences.edit().putString("mname",editTextMName.getText().toString()).apply();
        sharedPreferences.edit().putString("lname",editTextLName.getText().toString()).apply();
        sharedPreferences.edit().putString("houseno",editTextHouseNo.getText().toString()).apply();
        sharedPreferences.edit().putString("streetno",editTextStreetNo.getText().toString()).apply();
        /*sharedPreferences.edit().putString("barangay",editTextBarangay.getText().toString());
        sharedPreferences.edit().putString("municipality",editTextMunicipality.getText().toString());
        sharedPreferences.edit().putString("province",editTextMunicipality.getText().toString());
        */
        //String fname = sharedPreferences.getString("fname","");
        //Log.i("fname",fname);
        /*private EditText editTextFName;
        private EditText editTextMName;
        private EditText editTextLName;
        private EditText editTextHouseNo;
        private EditText editTextStreetNo;
        private EditText editTextBarangay;
        private EditText editTextMunicipality;
        private EditText editTextProvince;
        private RadioButton radioButtonResident;
        private RadioButton radioButtonNonResident;
        private RadioButton radioButtonOwner;
        private RadioButton radioButtonExtended;
        private RadioButton radioButtonActive;
        private RadioButton radioButtonInactive;
        private Spinner spinnerRegions;
        private Spinner spinnerProvinces;
        private Spinner spinnerMunicipal;
        private Spinner spinnerBarangay;*/

    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveReference();
    }

}
