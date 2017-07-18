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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static EditText editTextOrigin;
    public static EditText editTextContactNo;
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
    public static Spinner spinnerYear;
    public static Spinner spinnerISP;
    public static SeekBar seekBarNoFamMembers;
    private static TextView textViewNoFamMembers;
    private ArrayList years;
    private List<String> isps;
    private ArrayAdapter<String> spinnerArrayAdapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(layout.fragment_family,container,false);

        //init views
        initViews();

        //get the seek bar value
        getFamilyNoSeekBarVal();

        //get spinner values for years
        getYears();
        useArrayAdapter(years);
        spinnerYear.setAdapter(spinnerArrayAdapter);

        //get spinner values for isp
        getISP();
        useArrayAdapter((ArrayList) isps);
        spinnerISP.setAdapter(spinnerArrayAdapter);

        return  view;
    }

    private void initViews(){
        editTextOrigin = (EditText) view.findViewById(id.editTextOrigin);
        editTextContactNo = (EditText) view.findViewById(id.editTextContactNo);

        spinnerYear = (Spinner) view.findViewById(id.spinnerYear);
        spinnerISP = (Spinner) view.findViewById(id.spinnerISP);
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
        seekBarNoFamMembers = (SeekBar) view.findViewById(id.seekBarNoFamilyMembers);
        textViewNoFamMembers = (TextView) view.findViewById(id.textViewNoFamMembers);
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
