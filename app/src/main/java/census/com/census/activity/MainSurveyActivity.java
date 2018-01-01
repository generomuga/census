package census.com.census.activity;


import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import census.com.census.R;
import census.com.census.fragment.FamilyIdentificationFragment;

public class MainSurveyActivity extends AppCompatActivity {

    //views
    private Toolbar mToolBarSurvey;

    FamilyIdentificationFragment mIdentification;
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
        if (savedInstanceState == null) {
            mTag = "familyIdentification";
            mIdentification = new FamilyIdentificationFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentMain,mIdentification,mTag).commit();
        }

    }

}
