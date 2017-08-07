package census.com.census.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.AsyncLayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;

import census.com.census.R;


public class EnvironmentFragment extends Fragment {

    private View view;
    private ArrayAdapter spinnerArrayAdapter;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_environment, container, false);

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

}
