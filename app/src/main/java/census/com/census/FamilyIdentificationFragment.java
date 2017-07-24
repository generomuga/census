package census.com.census;

import android.app.Activity;
import android.content.Context;
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

    private DbUtils dbUtils;

    private ArrayAdapter spinnerArrayAdapter;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try{
            mListener = (OnFragmentInteractionListener) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }*/
        //mListener = (OnFragmentInteractionListener) activity;

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

        //to load regions from sql
        loadRegions();

        //select region
        spinnerRegionEvent();

        //select province
        spinnerProvinceEvent();

        //select municipal
        spinnerMunicipalEvent();

        mListener = (OnFragmentInteractionListener) getActivity();
        mListener.onFragmentInteraction("Gene");

        //mListener.onFragmentInteraction("Gne");

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

}
