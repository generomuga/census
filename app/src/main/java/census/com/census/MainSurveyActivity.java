package census.com.census;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import census.com.census.presenter.MainSurveyPresenter;
import census.com.census.views.MainSurveyView;

public class MainSurveyActivity extends AppCompatActivity implements MainSurveyView {

    private Toolbar toolBarSurvey;
    String tag;
    Fragment fragment;

    private SharedPreferences sharedPreferences;

    MainSurveyPresenter mainSurveyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        sharedPreferences = this.getSharedPreferences("census.com.census",MODE_PRIVATE);

        //to add toolbar in the activity
        toolBarSurvey = (Toolbar) findViewById(R.id.toolBarSurvey);
        setSupportActionBar(toolBarSurvey);
        getSupportActionBar().setTitle("Identification");

        //checks for save instance state
        if(savedInstanceState == null){
            tag = "FamilyIdentification";
            fragment = new FamilyIdentificationFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentMain,fragment,tag).commit();
        }

        //implement listener
        mainSurveyPresenter = new MainSurveyPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClearSharedReference();
    }

    private void onClearSharedReference(){
        //sharedPreferences = this.getSharedPreferences("census.com.census",MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public void changeFragment(View view){
        switch (view.getId()){
            case R.id.imageButtonFamilyId:
                tag = "FamilyIdentification";
                getSupportActionBar().setTitle("Identification");
                fragment = new FamilyIdentificationFragment();
                switchFragment(fragment,tag);
                break;

            case R.id.imageButtonFamily:
                tag = "Family";
                getSupportActionBar().setTitle("Family");
                fragment = new FamilyFragment();
                switchFragment(fragment,"Family");
                break;

            case R.id.imageButtonHealth:
                tag = "Health";
                getSupportActionBar().setTitle("Health");
                //fragment = new HealthFragment();
                //switchFragment(fragment,"Health");
                break;

            case R.id.imageButtonEnvironment:
                tag = "Environment";
                getSupportActionBar().setTitle("Environment");
                //fragment = new EnvironmentFragment();
                //switchFragment(fragment,"Environment");
                break;
        }
    }

    public void switchFragment(Fragment fragment,String tag){
        getSupportFragmentManager().beginTransaction()
                //.replace(R.id.fragmentMain,fragment,fragment.getClass().getSimpleName()).addToBackStack(tag).commit();
                .replace(R.id.fragmentMain,fragment,tag).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_survey,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                //checkActiveFragment(tag);
                //onSaveFamilyIdentification();
                //Toast.makeText(this,FamilyIdentificationFragment.editTextFName.getText().toString(),Toast.LENGTH_SHORT).show();
                sendFamilyIdentification();
                //sendFamily();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkActiveFragment(String tag){
        Fragment fragment =  getSupportFragmentManager().findFragmentByTag(tag);

        if (fragment != null) {
            if (fragment.isVisible()) {
                switch (tag){
                    case "FamilyIdentification":
                        //saveObjectFamilyIdentification();
                        break;
                    case "Family":
                        //saveObjectFamily();
                        break;
                    case "Health":
                        Toast.makeText(this,"Health",Toast.LENGTH_SHORT).show();
                        break;
                    case "Environment":
                        Toast.makeText(this,"Environment",Toast.LENGTH_SHORT).show();
                        break;
                }
            } else {
                Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void sendFamilyIdentification(){

        int residency = 0;
        int ownership = 0;
        int status = 0;

        mainSurveyPresenter.checkFamilyIdentification(FamilyIdentificationFragment.editTextFName.getText().toString().trim(),
                                        FamilyIdentificationFragment.editTextMName.getText().toString().trim(),
                                        FamilyIdentificationFragment.editTextLName.getText().toString().trim(),
                                        FamilyIdentificationFragment.spinnerRegions.getSelectedItem().toString().trim(),
                                        FamilyIdentificationFragment.spinnerProvinces.getSelectedItem().toString().trim(),
                                        FamilyIdentificationFragment.spinnerMunicipal.getSelectedItem().toString().trim(),
                                        FamilyIdentificationFragment.spinnerBarangay.getSelectedItem().toString().trim(),
                                        FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim(),
                                        FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim(),
                                        residency,ownership,status
                             );
    }

    private void sendFamily(){
        //String a = FamilyFragment.spinne
        mainSurveyPresenter.checkFamily("PLDT");
    }


    @Override
    public void onError(String message) {

    }

}
