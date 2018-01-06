package census.com.census.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.AsyncLayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import census.com.census.Environment;
import census.com.census.R;

public class EnvironmentFragment extends Fragment {

    private View view;
    private ArrayAdapter spinnerArrayAdapter;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private Spinner spinnerToilet;
    private Spinner spinnerWater;
    private Spinner spinnerElectricity;
    private Spinner spinnerHouse;
    private Spinner spinnerLot;
    private Spinner spinnerStructure;
    private Spinner spinnerExternal;
    private Spinner spinnerRoof;
    private Spinner spinnerFloor;
    private Spinner spinnerLightning;
    private Spinner spinnerCooking;
    private Spinner spinnerGarbage;
    private Spinner spinnerLocation;
    private Spinner spinnerEcological;

    private ArrayList listToilet;
    private ArrayList listWater;
    private ArrayList listElectricity;
    private ArrayList listHouse;
    private ArrayList listLot;
    private ArrayList listStructure;
    private ArrayList listExternal;
    private ArrayList listRoof;
    private ArrayList listFloor;
    private ArrayList listLightning;
    private ArrayList listCooking;
    private ArrayList listGarbage;
    private ArrayList listLocation;
    private ArrayList listEcological;

    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_environment, container, false);

        sharedPreferences = getActivity().getSharedPreferences("census.com.census", Context.MODE_PRIVATE);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        initViews();

        getToilet();
        useArrayAdapter(listToilet);
        spinnerToilet.setAdapter(spinnerArrayAdapter);

        getWater();
        useArrayAdapter(listWater);
        spinnerWater.setAdapter(spinnerArrayAdapter);

        getElectricity();
        useArrayAdapter(listElectricity);
        spinnerElectricity.setAdapter(spinnerArrayAdapter);

        getHouse();
        useArrayAdapter(listHouse);
        spinnerHouse.setAdapter(spinnerArrayAdapter);

        getLot();
        useArrayAdapter(listLot);
        spinnerLot.setAdapter(spinnerArrayAdapter);

        getStructure();
        useArrayAdapter(listStructure);
        spinnerStructure.setAdapter(spinnerArrayAdapter);

        getExternal();
        useArrayAdapter(listExternal);
        spinnerExternal.setAdapter(spinnerArrayAdapter);

        getRoof();
        useArrayAdapter(listRoof);
        spinnerRoof.setAdapter(spinnerArrayAdapter);

        getFloor();
        useArrayAdapter(listFloor);
        spinnerFloor.setAdapter(spinnerArrayAdapter);

        getLightning();
        useArrayAdapter(listLightning);
        spinnerLightning.setAdapter(spinnerArrayAdapter);

        getCooking();
        useArrayAdapter(listCooking);
        spinnerCooking.setAdapter(spinnerArrayAdapter);

        getGarbage();
        useArrayAdapter(listGarbage);
        spinnerGarbage.setAdapter(spinnerArrayAdapter);

        getLocation();
        useArrayAdapter(listLocation);
        spinnerLocation.setAdapter(spinnerArrayAdapter);

        getEcological();
        useArrayAdapter(listEcological);
        spinnerEcological.setAdapter(spinnerArrayAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadReference();
    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveReference();
        sendData();
    }

    private void onLoadReference(){
        int toilet = sharedPreferences.getInt("toilet", 0);
        int water = sharedPreferences.getInt("water", 0);
        int electricity = sharedPreferences.getInt("electricity", 0);
        int house = sharedPreferences.getInt("houses", 0);
        int lot = sharedPreferences.getInt("lot", 0);
        int structure = sharedPreferences.getInt("structure", 0);
        int external = sharedPreferences.getInt("external", 0);
        int roof = sharedPreferences.getInt("roof", 0);
        int floor = sharedPreferences.getInt("floor", 0);
        int lightning = sharedPreferences.getInt("lightning", 0);
        int cooking = sharedPreferences.getInt("cooking", 0);
        int garbage = sharedPreferences.getInt("garbage", 0);
        int location = sharedPreferences.getInt("location", 0);
        int ecological = sharedPreferences.getInt("ecological", 0);

        spinnerToilet.setSelection(toilet);
        spinnerWater.setSelection(water);
        spinnerElectricity.setSelection(electricity);
        spinnerHouse.setSelection(house);
        spinnerLot.setSelection(lot);
        spinnerStructure.setSelection(structure);
        spinnerExternal.setSelection(external);
        spinnerRoof.setSelection(roof);
        spinnerFloor.setSelection(floor);
        spinnerLightning.setSelection(lightning);
        spinnerCooking.setSelection(cooking);
        spinnerGarbage.setSelection(garbage);
        spinnerLocation.setSelection(location);
        spinnerEcological.setSelection(ecological);
        //spinnerToilet.setSelection(1);
    }

    private void onSaveReference(){
        sharedPreferences.edit().putInt("toilet", spinnerToilet.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("water", spinnerWater.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("electricity", spinnerElectricity.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("houses", spinnerHouse.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("lot", spinnerLot.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("structure", spinnerStructure.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("external", spinnerExternal.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("roof", spinnerRoof.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("floor", spinnerFloor.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("lightning", spinnerLightning.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("cooking", spinnerCooking.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("garbage", spinnerGarbage.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("location", spinnerLocation.getSelectedItemPosition()).apply();
        sharedPreferences.edit().putInt("ecological", spinnerEcological.getSelectedItemPosition()).apply();
    }

    private void initViews(){
        spinnerToilet = (Spinner) view.findViewById(R.id.spinnerToilet);
        spinnerWater = (Spinner) view.findViewById(R.id.spinnerWater);
        spinnerElectricity = (Spinner) view.findViewById(R.id.spinnerElectricity);
        spinnerHouse = (Spinner) view.findViewById(R.id.spinnerHouse);
        spinnerLot = (Spinner) view.findViewById(R.id.spinnerLot);
        spinnerStructure = (Spinner) view.findViewById(R.id.spinnerStructure);
        spinnerExternal = (Spinner) view.findViewById(R.id.spinnerExternal);
        spinnerRoof = (Spinner) view.findViewById(R.id.spinnerRoof);
        spinnerFloor = (Spinner) view.findViewById(R.id.spinnerFloor);
        spinnerLightning = (Spinner) view.findViewById(R.id.spinnerLightning);
        spinnerCooking = (Spinner) view.findViewById(R.id.spinnerCooking);
        spinnerGarbage = (Spinner) view.findViewById(R.id.spinnerGarbage);
        spinnerLocation = (Spinner) view.findViewById(R.id.spinnerLocation);
        spinnerEcological = (Spinner) view.findViewById(R.id.spinnerEcological);
    }

    private void useArrayAdapter(ArrayList arrayList){
        spinnerArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, arrayList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    }

    private void getToilet(){
        listToilet = new ArrayList<>(Arrays.asList(
                "Water sealed w/sealed tank",
                "Water sealed w/o sealed tank",
                "Covered pit",
                "Open pit"
        ));
    }

    private void getWater(){
        listWater = new ArrayList<>(Arrays.asList(
                "Dug well",
                "Community water system",
                "Tubed piped/deep well",
                "Spring/lake/river/rain",
                "Peddler",
                "Buy mineral water"
        ));
    }

    private void getElectricity(){
        listElectricity = new ArrayList<>(Arrays.asList(
                "Barangay generator",
                "Battery",
                "Biogas",
                "Own generator",
                "Solar"
        ));
    }

    private void getHouse(){
        listHouse = new ArrayList<>(Arrays.asList(
                "Owned",
                "Rented",
                "Amortized",
                "Occupied free",
                "Caretaker",
                "Family owned",
                "Government owned"
        ));
    }

    private void getLot(){
        listLot = new ArrayList<>(Arrays.asList(
                "Owned",
                "Rented",
                "Amortized",
                "Occupied free",
                "Caretaker",
                "Family owned",
                "Government owned",
                "PNR lot"
        ));
    }

    private void getStructure(){
        listStructure = new ArrayList<>(Arrays.asList(
                "Duplex",
                "Commercial/Industrial",
                "Extension",
                "Institutional residence",
                "Single house"
        ));
    }

    private void getExternal(){
        listExternal = new ArrayList<>(Arrays.asList(
                "Glass",
                "Bamboo, Sawali, Cogon, Nipa",
                "Concrete/Brick/Stone",
                "Galvanized Iron/Aluminum",
                "Half-concrete/Brick/Stone/Half wood",
                "Makeshift/Salvaged improvised",
                "Wood",
                "No walls/not reported"
        ));
    }

    private void getRoof(){
        listRoof = new ArrayList<>(Arrays.asList(
                "Bamboo/Sawali,Cogon,Nipa",
                "Galvanized iron",
                "Makeshift/Salvaged improvised",
                "Mixed materials"
        ));
    }

    private void getFloor(){
        listFloor = new ArrayList<>(Arrays.asList(
                "Bamboo",
                "Cement",
                "Cemented with linoleum",
                "Cemented with tiles",
                "Woods",
                "Soil",
                "Soil with linoleum"
        ));
    }

    private void getLightning(){
        listLightning = new ArrayList<>(Arrays.asList(
                "Battery",
                "Candle",
                "Solar power",
                "Kerosene",
                "Generator",
                "Electricity"
        ));
    }

    private void getCooking(){
        listCooking = new ArrayList<>(Arrays.asList(
                "Charcoal",
                "Electricity",
                "Kerosene",
                "LPG",
                "Wood",
                "Biogas"
        ));
    }

    private void getGarbage(){
        listGarbage = new ArrayList<>(Arrays.asList(
                "Burned",
                "Collected",
                "Composting",
                "Open pit",
                "River",
                "Thrown anywhere",
                "Waste segregation"
        ));
    }

    private void getLocation(){
        listLocation = new ArrayList<>(Arrays.asList(
                "Irrigation canal",
                "Lakeshore",
                "Railroad",
                "Riverbank",
                "Seashore",
                "Hill",
                "Mountain",
                "Rice field"
        ));
    }

    private void getEcological(){
        listEcological = new ArrayList<>(Arrays.asList(
                "Coastal",
                "Lowland",
                "Upland"
        ));
    }

    private void sendData(){
        String uid = mAuth.getCurrentUser().getUid();

        DatabaseReference environmentRef = mDatabase.child("environment").child(uid);

        Environment environment = new Environment();
        environment.setToilet(spinnerToilet.getSelectedItemPosition());
        environment.setWater(spinnerWater.getSelectedItemPosition());
        environment.setElectricity(spinnerElectricity.getSelectedItemPosition());
        environment.setAcquisition(spinnerHouse.getSelectedItemPosition());
        environment.setLot(spinnerLot.getSelectedItemPosition());
        environment.setStructure(spinnerStructure.getSelectedItemPosition());
        environment.setWalls(spinnerExternal.getSelectedItemPosition());
        environment.setRoof(spinnerRoof.getSelectedItemPosition());
        environment.setFloor(spinnerFloor.getSelectedItemPosition());
        environment.setLight(spinnerLightning.getSelectedItemPosition());
        environment.setCook(spinnerCooking.getSelectedItemPosition());
        environment.setGarbage(spinnerGarbage.getSelectedItemPosition());
        environment.setLocation(spinnerLocation.getSelectedItemPosition());
        environment.setEcological(spinnerEcological.getSelectedItemPosition());

        environmentRef.setValue(environment).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

}
