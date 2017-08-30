package census.com.census.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import census.com.census.R;

public class HealthFragment extends Fragment {

    View view;

    public static RadioButton radioButtonEatYes;
    public static RadioButton radioButtonEatNo;
    public static RadioButton radioButtonHerbalYes;
    public static RadioButton radioButtonHerbalNo;
    public static RadioButton radioButtonVegYes;
    public static RadioButton radioButtonVegNo;
    public static RadioButton radioButtonIodizeYes;
    public static RadioButton radioButtonIodizeNo;
    public static RadioButton radioButtonFamilyYes;
    public static RadioButton radioButtonFamilyNo;
    public static RadioButton radioButtonFamilyNa;

    private SharedPreferences sharedPreferences;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_health, container, false);

        //init views
        initViews();

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveReference();
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadData();
    }

    private void onLoadData(){
        sharedPreferences = getActivity().getSharedPreferences("census.com.census", Context.MODE_PRIVATE);

        radioButtonEatYes.setChecked(sharedPreferences.getBoolean("eatYes",true));
        radioButtonEatNo.setChecked(sharedPreferences.getBoolean("eatNo",false));
        radioButtonHerbalYes.setChecked(sharedPreferences.getBoolean("herbalYes",true));
        radioButtonHerbalNo.setChecked(sharedPreferences.getBoolean("herbalNo",false));
        radioButtonIodizeYes.setChecked(sharedPreferences.getBoolean("iodizeYes",true));
        radioButtonIodizeNo.setChecked(sharedPreferences.getBoolean("iodizeNo",false));
    }

    private void onSaveReference(){
        /*eatComplete;
        private int plantHerbal;
        private int vegGarden;
        private int useIodize;
        private int familyPlan;
        private int basal;
        private int cervical;
        private int lactation;
        private int rhtythm;
        private int standard;
        private int sympho;
        private int withdrawal;
        private int condom;
        private int depo;
        private int iud;
        private int tubal;
        private int pills;
        private int vasectomy;
        private int others;*/

        sharedPreferences.edit().putBoolean("eatYes",radioButtonEatYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("eatNo",radioButtonEatNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("herbalYes",radioButtonHerbalYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("herbalNo",radioButtonHerbalNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iodizeYes",radioButtonIodizeYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iodizeNo",radioButtonIodizeNo.isChecked()).apply();
    }

    private void initViews(){
        radioButtonEatYes = (RadioButton) view.findViewById(R.id.radioButtonEatYes);
        radioButtonEatNo = (RadioButton) view.findViewById(R.id.radioButtonEatNo);
        radioButtonHerbalYes = (RadioButton) view.findViewById(R.id.radioButtonHerbalYes);
        radioButtonHerbalNo = (RadioButton) view.findViewById(R.id.radioButtonHerbalNo);
        radioButtonVegYes = (RadioButton) view.findViewById(R.id.radioButtonVegYes);
        radioButtonVegNo = (RadioButton) view.findViewById(R.id.radioButtonVegNo);
        radioButtonIodizeYes = (RadioButton) view.findViewById(R.id.radioButtonIodizeYes);
        radioButtonIodizeNo = (RadioButton) view.findViewById(R.id.radioButtonIodizeNo);
        radioButtonFamilyYes = (RadioButton) view.findViewById(R.id.radioButtonFamilyYes);
        radioButtonFamilyNo = (RadioButton) view.findViewById(R.id.radioButtonFamilyNo);
        radioButtonFamilyNa = (RadioButton) view.findViewById(R.id.radioButtonFamilyNa);
    }

}
