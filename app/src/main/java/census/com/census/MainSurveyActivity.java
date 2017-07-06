package census.com.census;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);
    }

    public void changeFragment(View view){
        Fragment fragment;

        switch (view.getId()){
            case R.id.imageButtonFamily:
                fragment = new FamilyFragment();
                getSupportFragmentManager().beginTransaction()
                        //.replace(R.id.fragmentMain,fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        .replace(R.id.fragmentMain,fragment,null).commit();
                break;
            case R.id.imageButtonHealth:
                fragment = new HealthFragment();
                getSupportFragmentManager().beginTransaction()
                        //.replace(R.id.fragmentMain,fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
                        .replace(R.id.fragmentMain,fragment,null).commit();
                break;
        }
    }

}
