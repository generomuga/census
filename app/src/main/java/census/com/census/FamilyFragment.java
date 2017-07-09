package census.com.census;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static census.com.census.R.*;


public class FamilyFragment extends Fragment {

    private Spinner spinnerYear;
    private Spinner spinnerISP;
    private ArrayList years;
    private List<String> isps;
    private ArrayAdapter<String> spinnerArrayAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get reference of widgets from XML layout
        View view = inflater.inflate(layout.fragment_family,container,false);
        spinnerYear = (Spinner) view.findViewById(id.spinnerYear);
        spinnerISP = (Spinner) view.findViewById(id.spinnerISP);

        //get spinner values for years
        getYears();
        useArrayAdapter(years);
        spinnerYear.setAdapter(spinnerArrayAdapter);

        //get spinner values for isp
        getISP();
        useArrayAdapter((ArrayList) isps);
        spinnerISP.setAdapter(spinnerArrayAdapter);

        //return inflater.inflate(layout.fragment_family, container, false);
        return  view;
    }

    private void useArrayAdapter(ArrayList arrayList){
        spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.spinner_item,arrayList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getYears(){
        years = new ArrayList<String>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
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





}
