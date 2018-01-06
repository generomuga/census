package census.com.census.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import census.com.census.Health;
import census.com.census.R;

public class HealthFragment extends Fragment {

    View view;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public static RadioButton mEatYes;
    public static RadioButton mEatNo;
    public static RadioButton mHerbalYes;
    public static RadioButton mHerbalNo;
    public static RadioButton mVegYes;
    public static RadioButton mVegNo;
    public static RadioButton mIodizeYes;
    public static RadioButton mIodizeNo;
    public static RadioButton mFamilyYes;
    public static RadioButton mFamilyNo;
    public static RadioButton mFamilyNa;

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

    public static LinearLayout mContra;

    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_health, container, false);

        //shared pref
        sharedPreferences = getActivity().getSharedPreferences("census.com.census", Context.MODE_PRIVATE);

        //firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mEatYes = (RadioButton) view.findViewById(R.id.radioButtonEatYes);
        mEatNo = (RadioButton) view.findViewById(R.id.radioButtonEatNo);
        mHerbalYes = (RadioButton) view.findViewById(R.id.radioButtonHerbalYes);
        mHerbalNo = (RadioButton) view.findViewById(R.id.radioButtonHerbalNo);
        mVegYes = (RadioButton) view.findViewById(R.id.radioButtonVegYes);
        mVegNo = (RadioButton) view.findViewById(R.id.radioButtonVegNo);
        mIodizeYes = (RadioButton) view.findViewById(R.id.radioButtonIodizeYes);
        mIodizeNo = (RadioButton) view.findViewById(R.id.radioButtonIodizeNo);
        mFamilyYes = (RadioButton) view.findViewById(R.id.radioButtonFamilyYes);
        mFamilyNo = (RadioButton) view.findViewById(R.id.radioButtonFamilyNo);
        mFamilyNa = (RadioButton) view.findViewById(R.id.radioButtonFamilyNa);

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

        mContra = (LinearLayout) view.findViewById(R.id.linearLayoutContra);
        setEnable(false);

        mFamilyYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    setEnable(true);
            }
        });

        mFamilyNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnable(false);
            }
        });

        mFamilyNa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnable(false);
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        onSaveReference();
        sendData();
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadData();
    }

    private void onLoadData(){
        mEatYes.setChecked(sharedPreferences.getBoolean("eatYes",true));
        mEatNo.setChecked(sharedPreferences.getBoolean("eatNo",false));
        mHerbalYes.setChecked(sharedPreferences.getBoolean("herbalYes",true));
        mHerbalNo.setChecked(sharedPreferences.getBoolean("herbalNo",false));
        mVegYes.setChecked(sharedPreferences.getBoolean("vegYes",true));
        mVegNo.setChecked(sharedPreferences.getBoolean("vegNo",false));
        mIodizeYes.setChecked(sharedPreferences.getBoolean("iodizeYes",true));
        mIodizeNo.setChecked(sharedPreferences.getBoolean("iodizeNo",false));
        mFamilyYes.setChecked(sharedPreferences.getBoolean("familyYes",true));
        mFamilyNo.setChecked(sharedPreferences.getBoolean("familyNo",false));
        mFamilyNa.setChecked(sharedPreferences.getBoolean("familyNa",false));

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
        sharedPreferences.edit().putBoolean("eatYes",mEatYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("eatNo",mEatNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("herbalYes",mHerbalYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("herbalNo",mHerbalNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("vegYes",mVegYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("vegNo",mVegNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iodizeYes",mIodizeYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("iodizeNo",mIodizeNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("familyYes",mFamilyYes.isChecked()).apply();
        sharedPreferences.edit().putBoolean("familyNo",mFamilyNo.isChecked()).apply();
        sharedPreferences.edit().putBoolean("familyNa",mFamilyNa.isChecked()).apply();
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

    private void setEnable(boolean enable){
        for (int i = 0; i < mContra.getChildCount(); i++){
            View viewContra = mContra.getChildAt(i);
            viewContra.setEnabled(enable);
        }
    }

    private void sendData(){
        String uid = mAuth.getCurrentUser().getUid();
        DatabaseReference healthRef = mDatabase.child("health").child(uid);

        int eat = (mEatYes.isChecked()) ? 1 : 0;
        int herbal = (mHerbalYes.isChecked()) ? 1 : 0;
        int vegetable = (mVegYes.isChecked()) ? 1 : 0;
        int salt = (mIodizeYes.isChecked()) ? 1 : 0;

        int familyPlanning = 0;
        if (mFamilyYes.isChecked())
            familyPlanning = 1;
        if (mFamilyYes.isChecked())
            familyPlanning = 0;
        if (mFamilyNa.isChecked())
            familyPlanning = 2;

        Health health = new Health();

        health.setEatComplete(eat);
        health.setPlantHerbal(herbal);
        health.setVegGarden(vegetable);
        health.setFamilyPlan(familyPlanning);

        healthRef.setValue(health).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }

}
