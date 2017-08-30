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

    }

    private void onSaveReference(){
        //sharedPreferences.edit().putString("fname",editTextFName.getText().toString().trim()).apply();
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
