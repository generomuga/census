package census.com.census.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

    public static CheckBox mBasal;
    public static CheckBox mCervical;
    public static CheckBox mLactation;
    public static CheckBox mRhythm;
    public static CheckBox mStandard;
    public static CheckBox mSympho;
    public static CheckBox mWithdrawal;
    public static CheckBox mCondom;
    public static CheckBox mDepo;
    public static CheckBox mIud;
    public static CheckBox mTubal;
    public static CheckBox mPills;
    public static CheckBox mVasectomy;
    public static CheckBox mOthers;

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
        radioButtonVegYes.setChecked(sharedPreferences.getBoolean("vegYes",true));
        radioButtonVegNo.setChecked(sharedPreferences.getBoolean("vegNo",false));
        radioButtonIodizeYes.setChecked(sharedPreferences.getBoolean("iodizeYes",true));
        radioButtonIodizeNo.setChecked(sharedPreferences.getBoolean("iodizeNo",false));
        radioButtonFamilyYes.setChecked(sharedPreferences.getBoolean("familyYes",true));
        radioButtonFamilyNo.setChecked(sharedPreferences.getBoolean("familyNo",false));
        radioButtonFamilyNa.setChecked(sharedPreferences.getBoolean("familyNa",false));

        mBasal.setChecked(sharedPreferences.getBoolean("basal",false));
        mCervical.setChecked(sharedPreferences.getBoolean("cervical",false));
        mLactation.setChecked(sharedPreferences.getBoolean("lactation",false));
        mRhythm.setChecked(sharedPreferences.getBoolean("rhythm",false));
        mStandard.setChecked(sharedPreferences.getBoolean("standard",false));
        mSympho.setChecked(sharedPreferences.getBoolean("sympho",false));
        mWithdrawal.setChecked(sharedPreferences.getBoolean("withdrawal",false));
        mCondom.setChecked(sharedPreferences.getBoolean("condom",false));
        mDepo.setChecked(sharedPreferences.getBoolean("depo",false));
        mIud.setChecked(sharedPreferences.getBoolean("iud",false));
        mTubal.setChecked(sharedPreferences.getBoolean("tubal",false));
        mPills.setChecked(sharedPreferences.getBoolean("pills",false));
        mVasectomy.setChecked(sharedPreferences.getBoolean("vasectomy",false));
        mOthers.setChecked(sharedPreferences.getBoolean("others",false));

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
        sharedPreferences.edit().putBoolean("vegYes",radioButtonVegYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("vegNo",radioButtonVegNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iodizeYes",radioButtonIodizeYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iodizeNo",radioButtonIodizeNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("familyYes",radioButtonFamilyYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("familyNo",radioButtonFamilyNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("familyNa",radioButtonFamilyNa.isChecked()).apply();
        sharedPreferences.edit().putBoolean("basal",mBasal.isChecked()).apply();
        sharedPreferences.edit().putBoolean("cervical",mCervical.isChecked()).apply();
        sharedPreferences.edit().putBoolean("lactation",mLactation.isChecked()).apply();
        sharedPreferences.edit().putBoolean("rhythm",mRhythm.isChecked()).apply();
        sharedPreferences.edit().putBoolean("standard",mStandard.isChecked()).apply();
        sharedPreferences.edit().putBoolean("sympho",mSympho.isChecked()).apply();
        sharedPreferences.edit().putBoolean("withdrawal",mWithdrawal.isChecked()).apply();
        sharedPreferences.edit().putBoolean("condom",mCondom.isChecked()).apply();
        sharedPreferences.edit().putBoolean("depo",mDepo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iud",mIud.isChecked()).apply();
        sharedPreferences.edit().putBoolean("tubal",mTubal.isChecked()).apply();
        sharedPreferences.edit().putBoolean("pills",mPills.isChecked()).apply();
        sharedPreferences.edit().putBoolean("vasectomy",mVasectomy.isChecked()).apply();
        sharedPreferences.edit().putBoolean("others",mOthers.isChecked()).apply();
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

        mBasal = (CheckBox) view.findViewById(R.id.checkboxBasal);
        mCervical = (CheckBox) view.findViewById(R.id.checkboxCervical);
        mLactation = (CheckBox) view.findViewById(R.id.checkboxLactation);
        mRhythm = (CheckBox) view.findViewById(R.id.checkboxRhythm);
        mStandard = (CheckBox) view.findViewById(R.id.checkboxStandard);
        mSympho = (CheckBox) view.findViewById(R.id.checkboxSympho);
        mWithdrawal = (CheckBox) view.findViewById(R.id.checkboxWithdrawal);
        mCondom = (CheckBox) view.findViewById(R.id.checkboxCondom);
        mDepo = (CheckBox) view.findViewById(R.id.checkboxDepo);
        mIud = (CheckBox) view.findViewById(R.id.checkboxIud);
        mTubal = (CheckBox) view.findViewById(R.id.checkboxTubal);
        mPills = (CheckBox) view.findViewById(R.id.checkboxPill);
        mVasectomy = (CheckBox) view.findViewById(R.id.checkboxVasectomy);
        mOthers = (CheckBox) view.findViewById(R.id.checkboxOthers);
    }

}
