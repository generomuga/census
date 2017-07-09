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

import java.util.ArrayList;

import static census.com.census.R.*;


public class FamilyFragment extends Fragment {

    private Spinner spinnerYear;
    private ArrayList years;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //get reference of widgets from XML layout
        View view = inflater.inflate(layout.fragment_family,container,false);
        spinnerYear = (Spinner) view.findViewById(id.spinnerYear);

        //get spinner values
        getYears();

        //Initializing array adapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, years);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerYear.setAdapter(spinnerArrayAdapter);

        //return inflater.inflate(layout.fragment_family, container, false);
        return  view;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getYears(){
        years = new ArrayList<String>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for(int year = 1990;year<=currentYear;year++){
            years.add(Integer.toString(year));
        }

    }



}
