package census.com.census;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    public static EditText editTextFName;
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
    private ArrayList regions;
    private ArrayList provinces;
    private ArrayList region4AProvince;
    private ArrayAdapter spinnerArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_family_identification, container, false);

        //init views
        initViews();

        useArrayAdapter(Locations.regions);
        spinnerRegions.setAdapter(spinnerArrayAdapter);

        //select region
        spinnerRegionEvent();

        //select provinces
        return view;
    }

    private void initViews(){
        editTextFName = (EditText) view.findViewById(R.id.editTextFname);
        editTextMName = (EditText) view.findViewById(R.id.editTextMname);
        editTextLName = (EditText) view.findViewById(R.id.editTextLname);
        editTextHouseNo = (EditText) view.findViewById(R.id.editTextHouseNo);
        editTextStreetNo = (EditText) view.findViewById(R.id.editTextStreetNo);
        //editTextBarangay = (EditText) view.findViewById(R.id.editTextBrgy);
        //editTextMunicipality = (EditText) view.findViewById(R.id.editTextMunicipality);
        //editTextProvince = (EditText) view.findViewById(R.id.editTextProvince);
        radioButtonResident = (RadioButton) view.findViewById(R.id.radioButtonResident);
        radioButtonNonResident = (RadioButton) view.findViewById(R.id.radioButtonNonResident);
        radioButtonOwner = (RadioButton) view.findViewById(R.id.radioButtonOwner);
        radioButtonExtended = (RadioButton) view.findViewById(R.id.radioButtonExtended);
        radioButtonActive = (RadioButton) view.findViewById(R.id.radioButtonActive);
        radioButtonInactive = (RadioButton) view.findViewById(R.id.radioButtonInactive);
        spinnerRegions = (Spinner) view.findViewById(R.id.spinnerRegions);
        spinnerProvinces = (Spinner) view.findViewById(R.id.spinnerProvince);
    }

    private void useArrayAdapter(ArrayList arrayList){
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.spinner_item,arrayList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    }

    private void spinnerRegionEvent(){
        spinnerRegions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkSelectedRegion(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void spinnerProvinceEvent(){
        spinnerProvinces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkSelectedRegion(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void checkSelectedRegion(Integer region){
        switch(region){
            case 3:
                useArrayAdapter(Locations.region4Provinces);
                spinnerProvinces.setAdapter(spinnerArrayAdapter);
                break;
            case 4:
                break;
        }

    }

    private void checkSelectedProvince(Integer region, Integer province){
        switch(region){
            case 3:

        }
    }

}
