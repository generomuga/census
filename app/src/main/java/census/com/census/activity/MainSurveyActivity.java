package census.com.census.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import census.com.census.FamilyIdentification;
import census.com.census.R;
import census.com.census.fragment.FamilyFragment;
import census.com.census.fragment.FamilyIdentificationFragment;

public class MainSurveyActivity extends AppCompatActivity {

    //views
    private Toolbar mToolBarSurvey;

    //fragments
    FragmentTransaction mTransaction;

    private String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        //to add toolbar in the activity
        mToolBarSurvey = (Toolbar) findViewById(R.id.toolBarSurvey);

        setSupportActionBar(mToolBarSurvey);
        getSupportActionBar().setTitle("Identification");

        //fragment
        if (savedInstanceState != null){
            return;
        }

        //set default fragment
        FamilyIdentificationFragment familyIdentificationFragment = new FamilyIdentificationFragment();
        familyIdentificationFragment.setArguments(getIntent().getExtras());
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.add(R.id.fragmentMain, familyIdentificationFragment).commit();
    }

    public void changeFragment(View view){

        mTransaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.imageButtonFamilyId:
                FamilyIdentificationFragment familyIdentificationFragment = new FamilyIdentificationFragment();
                mTransaction.replace(R.id.fragmentMain, familyIdentificationFragment);
                mTransaction.addToBackStack(null);
                mTransaction.commit();
                break;

            case R.id.imageButtonFamily:
                //check if the fields are complete
                boolean isComplete = checkFieldsComplete();
                Toast.makeText(this, Boolean.toString(isComplete), Toast.LENGTH_LONG).show();
                if (!isComplete){
                    Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    FamilyFragment familyFragment = new FamilyFragment();
                    mTransaction.replace(R.id.fragmentMain, familyFragment);
                    mTransaction.addToBackStack(null);
                    mTransaction.commit();
                }
                break;
        }
    }

    private boolean checkFieldsComplete(){
        return !FamilyIdentificationFragment.mFname.getText().toString().equals("");
    }

}
