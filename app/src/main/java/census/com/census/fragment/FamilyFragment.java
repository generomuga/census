package census.com.census.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import census.com.census.DbUtils;
import census.com.census.R;

import static census.com.census.R.*;


public class FamilyFragment extends Fragment {

    private View view;
    public static CheckBox checkBoxBicycle;
    public static CheckBox checkBoxBoat;
    public static CheckBox checkBoxBus;
    public static CheckBox checkBoxCar;
    public static CheckBox checkBoxJeep;
    public static CheckBox checkBoxMotorBoat;
    public static CheckBox checkBoxMotorcycle;
    public static CheckBox checkBoxOwner;
    public static CheckBox checkBoxPedicab;
    public static CheckBox checkBoxPickup;
    public static CheckBox checkBoxPumpBoat;
    public static CheckBox checkBoxRaft;
    public static CheckBox checkBoxSuv;
    public static CheckBox checkBoxTricycle;
    public static CheckBox checkBoxTruck;
    public static CheckBox checkBoxVan;

    public static EditText editTextBicycleNo;
    public static EditText editTextBoatNo;
    public static EditText editTextBusNo;
    public static EditText editTextCarNo;
    public static EditText editTextJeepNo;
    public static EditText editTextMotorBoatNo;
    public static EditText editTextMotorcycleNo;
    public static EditText editTextOwnerNo;
    public static EditText editTextPedicabNo;
    public static EditText editTextPickupNo;
    public static EditText editTextPumpBoatNo;
    public static EditText editTextRaftNo;
    public static EditText editTextSuvNo;
    public static EditText editTextTricycleNo;
    public static EditText editTextTruckNo;
    public static EditText editTextVanNo;

    public static Spinner spinnerYear;
    public static Spinner spinnerISP;

    public static Spinner spinnerRegion;
    public static Spinner spinnerProvince;
    public static Spinner spinnerMunicipal;
    public static Spinner spinnerBarangay;

    public static SeekBar seekBarNoFamMembers;

    public static TextView textViewNoFamMembers;
    private ArrayList years;
    private ArrayList isps;
    private ArrayAdapter<String> spinnerArrayAdapter;

    private DbUtils dbUtils;

    private SharedPreferences sharedPreferences;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(layout.fragment_family,container,false);

        //init views
        initViews();

        //get the seek bar value
        getFamilyNoSeekBarVal();

        //get spinner values for years
        getYears();
        useArrayAdapter(years);
        spinnerYear.setAdapter(spinnerArrayAdapter);

        //load regions
        loadRegions();

        //load province
        spinnerRegionEvent();

        //load municipality
        spinnerProvinceEvent();

        //load barangay
        spinnerMunicipalEvent();


        //get spinner values for isp
        getISP();
        useArrayAdapter((ArrayList) isps);
        spinnerISP.setAdapter(spinnerArrayAdapter);


        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveReference();
    }

    private void onLoadData(){
        sharedPreferences = getActivity().getSharedPreferences("census.com.census", Context.MODE_PRIVATE);

        seekBarNoFamMembers.setProgress(sharedPreferences.getInt("noFam",0));
    }

    private void onSaveReference(){
        //sharedPreferences.edit().putString("fname",editTextFName.getText().toString().trim()).apply();
        sharedPreferences.edit().putInt("noFam",seekBarNoFamMembers.getProgress()).apply();
    }

    private void initViews(){
        //editTextOrigin = (EditText) view.findViewById(id.editTextOrigin);

        //editTextContactNo = (TextInputEditText) view.findViewById(id.editTextContactNo);

        spinnerYear = (Spinner) view.findViewById(id.spinnerYear);
        spinnerISP = (Spinner) view.findViewById(id.spinnerISP);

        spinnerRegion = (Spinner) view.findViewById(id.spinnerRegions);
        spinnerProvince = (Spinner) view.findViewById(id.spinnerProvince);
        spinnerMunicipal = (Spinner) view.findViewById(id.spinnerMunicipal);
        spinnerBarangay = (Spinner) view.findViewById(id.spinnerBarangay);


        checkBoxBicycle = (CheckBox) view.findViewById(id.checkboxBicycle);
        checkBoxBoat = (CheckBox) view.findViewById(id.checkboxBoat);
        checkBoxBus = (CheckBox) view.findViewById(id.checkboxBus);
        checkBoxCar = (CheckBox) view.findViewById(id.checkboxCar);
        checkBoxJeep = (CheckBox) view.findViewById(id.checkboxJeep);
        checkBoxMotorBoat = (CheckBox) view.findViewById(id.checkboxMotorBoat);
        checkBoxMotorcycle = (CheckBox) view.findViewById(id.checkboxMotorcycle);
        checkBoxOwner = (CheckBox) view.findViewById(id.checkboxOwner);
        checkBoxPedicab = (CheckBox) view.findViewById(id.checkboxPedicab);
        checkBoxPickup = (CheckBox) view.findViewById(id.checkboxPickup);
        checkBoxPumpBoat = (CheckBox) view.findViewById(id.checkboxPumpBoat);
        checkBoxRaft = (CheckBox) view.findViewById(id.checkboxRaft);
        checkBoxSuv = (CheckBox) view.findViewById(id.checkboxSuv);
        checkBoxTricycle = (CheckBox) view.findViewById(id.checkboxTricycle);
        checkBoxTruck = (CheckBox) view.findViewById(id.checkboxTruck);
        checkBoxVan = (CheckBox) view.findViewById(id.checkboxVan);
        editTextBicycleNo = (EditText) view.findViewById(id.editTextBicycleNo);
        editTextBoatNo = (EditText) view.findViewById(id.editTextBoatNo);
        editTextBusNo = (EditText) view.findViewById(id.editTextBusNo);
        editTextCarNo = (EditText) view.findViewById(id.editTextCarNo);
        editTextJeepNo = (EditText) view.findViewById(id.editTextJeepNo);
        editTextMotorBoatNo = (EditText) view.findViewById(id.editTextMotorBoatNo);
        editTextMotorcycleNo = (EditText) view.findViewById(id.editTextMotorcycleNo);
        editTextOwnerNo = (EditText) view.findViewById(id.editTextOwnerNo);
        editTextPedicabNo = (EditText) view.findViewById(id.editTextPedicabNo);
        editTextPickupNo = (EditText) view.findViewById(id.editTextPickupNo);
        editTextPumpBoatNo = (EditText) view.findViewById(id.editTextPumpBoatNo);
        editTextRaftNo = (EditText) view.findViewById(id.editTextRaftNo);
        editTextSuvNo = (EditText) view.findViewById(id.editTextSuvNo);
        editTextTricycleNo = (EditText) view.findViewById(id.editTextTricycleNo);
        editTextTruckNo = (EditText) view.findViewById(id.editTextTruckNo);
        editTextVanNo = (EditText) view.findViewById(id.editTextVanNo);
        seekBarNoFamMembers = (SeekBar) view.findViewById(id.seekBarNoFamilyMembers);
        textViewNoFamMembers = (TextView) view.findViewById(id.textViewNoFamMembers);
    }

    private void loadRegions(){
        ArrayList listRegion;
        String query = "SELECT DISTINCT(region) FROM locations";
        //DbUtils dbUtils = new DbUtils(getActivity());
        dbUtils = new DbUtils(getActivity());

        listRegion = dbUtils.queryLocation(query,"region");

        if(!listRegion.isEmpty()) {
            useArrayAdapter(listRegion);
            spinnerRegion.setAdapter(spinnerArrayAdapter);
        }
        else{
            Log.i("regions:","empty");
        }
    }

    private void spinnerRegionEvent(){
        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getProvinces(spinnerRegion.getSelectedItem().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void getProvinces(String region){
        ArrayList listProvince;
        String query = "SELECT province FROM locations where region='"+region+"'";
        //DbUtils dbUtils = new DbUtils(getActivity());
        dbUtils = new DbUtils(getActivity());
        listProvince = dbUtils.queryLocation(query,"province");
        if(!listProvince.isEmpty()) {
            useArrayAdapter(listProvince);
            spinnerProvince.setAdapter(spinnerArrayAdapter);
        }
        else{
            Log.i("provinces:","empty");
        }
    }

    private void spinnerProvinceEvent(){
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getMunicipal(spinnerProvince.getSelectedItem().toString().trim());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });
    }

    private void getMunicipal(String province){
        ArrayList listMunicipal;
        String query = "SELECT municipality FROM locations where province='"+province+"'";
        //DbUtils dbUtils = new DbUtils(getActivity());
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
        ArrayList listBarangay;
        String query = "SELECT barangay FROM locations where municipality='"+municipal+"'";
        //DbUtils dbUtils = new DbUtils(getActivity());
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
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item,arrayList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    }

    private void getYears(){
        years = new ArrayList<String>();
        int currentYear = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            currentYear = Calendar.getInstance().get(Calendar.YEAR);
        }
        else{
            currentYear = 2017;
        }
        for(int year = 1980;year<=currentYear;year++){
            years.add(Integer.toString(year));
        }
    }

    private void getISP(){
        isps = new ArrayList<>(Arrays.asList(
                "PLDT/Smart/Sun",
                "Globe/Innove",
                "Eastern Telecoms",
                "Bayantel",
                "Wi-Tribe",
                "Destiny Cable Internet",
                "Sky Broadband"
        ));
    }

    private void getFamilyNoSeekBarVal(){
        seekBarNoFamMembers.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewNoFamMembers.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

}
