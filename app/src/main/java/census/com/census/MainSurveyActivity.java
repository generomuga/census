package census.com.census;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

public class MainSurveyActivity extends AppCompatActivity {

    private Toolbar toolBarSurvey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        toolBarSurvey = (Toolbar) findViewById(R.id.toolBarSurvey);
        setSupportActionBar(toolBarSurvey);
        getSupportActionBar().setTitle("Family Information");
    }

    public void changeFragment(View view){
        Fragment fragment;
        switch (view.getId()){
            case R.id.imageButtonFamilyId:
                getSupportActionBar().setTitle("Family Identification");
                fragment = new FamilyIdentificationFragment();
                switchFragment(fragment);
                break;

            case R.id.imageButtonFamily:
                getSupportActionBar().setTitle("Family Information");
                fragment = new FamilyFragment();
                switchFragment(fragment);
                break;

            case R.id.imageButtonHealth:
                getSupportActionBar().setTitle("Education Information");
                fragment = new HealthFragment();
                switchFragment(fragment);
                break;
        }
    }

    public void switchFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                //.replace(R.id.fragmentMain,fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                .replace(R.id.fragmentMain,fragment,null).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_survey,menu);
        return true;
    }

}
