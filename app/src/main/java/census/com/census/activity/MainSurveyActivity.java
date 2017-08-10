package census.com.census.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import census.com.census.fragment.FamilyFragment;
import census.com.census.fragment.FamilyIdentificationFragment;
import census.com.census.presenter.SurveyPresenter;
import census.com.census.R;
import census.com.census.presenter_impl.SurveyPresenterImpl;
import census.com.census.view.SurveyView;

public class MainSurveyActivity extends AppCompatActivity implements SurveyView.OnFamilyIdentification{

    private Toolbar toolBarSurvey;
    private SharedPreferences sharedPreferences;
    private String tag;
    private Fragment fragment;

    private ProgressDialog mProgress;

    SurveyPresenter.OnFamilyIdentification familyIdentificationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        sharedPreferences = this.getSharedPreferences("census.com.census",MODE_PRIVATE);
        onClearSharedReference();

        mProgress = new ProgressDialog(this);

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

        familyIdentificationListener = new SurveyPresenterImpl(this);
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

    /*private void checkActiveFragment(String tag){
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
    }*/

    private void sendFamilyIdentification(){
        if(familyIdentificationListener.checkFname(FamilyIdentificationFragment.editTextFName.getText().toString().trim())  &&
                familyIdentificationListener.checkMname(FamilyIdentificationFragment.editTextMName.getText().toString().trim()) &&
                familyIdentificationListener.checkLname(FamilyIdentificationFragment.editTextLName.getText().toString().trim()) &&
                familyIdentificationListener.checkHouseNo(FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim()) &&
                familyIdentificationListener.checkStreetNo(FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim()))
                {
                    mProgress.setMessage("Sending information...");
                    mProgress.setCancelable(false);
                    mProgress.show();
                    familyIdentificationListener.sendValue(FamilyIdentificationFragment.editTextFName.getText().toString().trim(),
                            FamilyIdentificationFragment.editTextMName.getText().toString().trim(),
                            FamilyIdentificationFragment.editTextLName.getText().toString().trim(),
                            FamilyIdentificationFragment.spinnerRegions.getSelectedItem().toString().trim(),
                            FamilyIdentificationFragment.spinnerProvinces.getSelectedItem().toString().trim(),
                            FamilyIdentificationFragment.spinnerMunicipal.getSelectedItem().toString().trim(),
                            FamilyIdentificationFragment.spinnerBarangay.getSelectedItem().toString().trim(),
                            FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim(),
                            FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim(),
                            1,1,1);
                }
    }

    @Override
    public void setErrorFname(String message) {
        FamilyIdentificationFragment.editTextFName.setError(message);
    }

    @Override
    public void setErrorMname(String message) {
        mProgress.dismiss();
        FamilyIdentificationFragment.editTextMName.setError(message);
    }

    @Override
    public void setErrorLname(String message) {
        mProgress.dismiss();
        FamilyIdentificationFragment.editTextLName.setError(message);
    }

    @Override
    public void setErrorHouseNo(String message) {
        mProgress.dismiss();
        FamilyIdentificationFragment.editTextHouseNo.setError(message);
    }

    @Override
    public void setErrorStreetNo(String message) {
        mProgress.dismiss();
        FamilyIdentificationFragment.editTextStreetNo.setError(message);
    }

    @Override
    public void onSuccess(String message) {
        mProgress.dismiss();
        startActivity(new Intent(MainSurveyActivity.this,MainActivity.class));
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
