package census.com.census.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import census.com.census.Family;
import census.com.census.FamilyIdentification;
import census.com.census.fragment.EnvironmentFragment;
import census.com.census.fragment.FamilyFragment;
import census.com.census.fragment.FamilyIdentificationFragment;
import census.com.census.fragment.HealthFragment;
import census.com.census.model_impl.SurveyModelImpl;
import census.com.census.presenter.SurveyPresenter;
import census.com.census.R;
import census.com.census.presenter_impl.SurveyPresenterImpl;
import census.com.census.view.SurveyView;

public class MainSurveyActivity extends AppCompatActivity implements SurveyView.OnFamilyIdentification,SurveyView.OnFamily,SurveyView.OnHealth,SurveyView.OnEnvironment{

    private Toolbar toolBarSurvey;
    private SharedPreferences sharedPreferences;
    private String tag;
    private Fragment fragment;

    private ProgressDialog mProgress;

    private FirebaseAuth mAuth;

    private boolean isOpenFamilyIdentification;
    private boolean isOpenFamily;
    private boolean isOpenHealth;
    private boolean isOpenEnvironment;

    SurveyPresenter.OnFamilyIdentification familyIdentificationListener;
    SurveyPresenter.OnFamily familyListener;
    SurveyPresenter.OnHealth healthListener;
    SurveyPresenter.OnEnvironment environmentListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        mAuth = FirebaseAuth.getInstance();

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
            isOpenFamilyIdentification = true;
            fragment = new FamilyIdentificationFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentMain,fragment,tag).commit();
        }

        familyIdentificationListener = (SurveyPresenter.OnFamilyIdentification) (familyListener = (SurveyPresenter.OnFamily) (healthListener = (SurveyPresenter.OnHealth) (environmentListener = new SurveyPresenterImpl(this,this,this,this))));
    }

    private void onClearSharedReference(){
        //sharedPreferences = this.getSharedPreferences("census.com.census",MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public void changeFragment(View view){
        switch (view.getId()){
            case R.id.imageButtonFamilyId:
                tag = "FamilyIdentification";
                isOpenFamilyIdentification = true;
                getSupportActionBar().setTitle("Identification");
                fragment = new FamilyIdentificationFragment();
                switchFragment(fragment,tag);
                break;

            case R.id.imageButtonFamily:
                tag = "Family";
                isOpenFamily = true;
                getSupportActionBar().setTitle("Family");
                fragment = new FamilyFragment();
                switchFragment(fragment,"Family");
                break;

            case R.id.imageButtonHealth:
                tag = "Health";
                isOpenHealth = true;
                getSupportActionBar().setTitle("Health");
                fragment = new HealthFragment();
                switchFragment(fragment,"Health");
                break;

            case R.id.imageButtonEnvironment:
                tag = "Environment";
                isOpenEnvironment = true;
                getSupportActionBar().setTitle("Environment");
                fragment = new EnvironmentFragment();
                switchFragment(fragment,"Environment");
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
                sendData();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String blockCharacterSet = "1234567890";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    private void sendData(){
        boolean isFamilyIdentificationComplete = false;
        boolean isFamilyComplete = false;
        boolean isHealthComplete = false;
        boolean isEnvironmentComplete = false;


        if(isOpenFamilyIdentification) {
            if (familyIdentificationListener.checkFname(FamilyIdentificationFragment.editTextFName.getText().toString().trim()) &&
                    familyIdentificationListener.checkMname(FamilyIdentificationFragment.editTextMName.getText().toString().trim()) &&
                    familyIdentificationListener.checkLname(FamilyIdentificationFragment.editTextLName.getText().toString().trim()) &&
                    familyIdentificationListener.checkHouseNo(FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim()) &&
                    familyIdentificationListener.checkStreetNo(FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim())
                    && familyIdentificationListener.checkBicycleNo(FamilyFragment.checkBoxBicycle.isChecked(),FamilyFragment.editTextBicycleNo.getText().toString())) {
                isFamilyIdentificationComplete = true;
            }
        }
        if(isOpenFamily){
            if (familyListener.checkNoFamily(FamilyFragment.seekBarNoFamMembers.getProgress())) {
                isFamilyComplete = true;
            }
        }
        if(isOpenHealth){
            isHealthComplete = true;
        }
        if(isOpenEnvironment){
            isEnvironmentComplete = true;
        }

        if(isFamilyIdentificationComplete && isFamilyComplete && isHealthComplete && isEnvironmentComplete){
            mProgress.setMessage("Sending data...");
            mProgress.setCancelable(true);
            mProgress.show();

            int resident = (FamilyIdentificationFragment.radioButtonResident.isChecked()) ? 1 : 0;
            int ownership = (FamilyIdentificationFragment.mOwner.isChecked()) ? 1 : 0;
            int familyStatus = (FamilyIdentificationFragment.mActive.isChecked()) ? 1 : 0;

            familyIdentificationListener.sendFamilyIdentificationValue(FamilyIdentificationFragment.editTextFName.getText().toString().trim(),
                    FamilyIdentificationFragment.editTextMName.getText().toString().trim(),
                    FamilyIdentificationFragment.editTextLName.getText().toString().trim(),
                    FamilyIdentificationFragment.spinnerRegions.getSelectedItem().toString(),
                    FamilyIdentificationFragment.spinnerProvinces.getSelectedItem().toString(),
                    FamilyIdentificationFragment.spinnerMunicipal.getSelectedItem().toString(),
                    FamilyIdentificationFragment.spinnerBarangay.getSelectedItem().toString(),
                    FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim(),
                    FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim(),
                    resident,ownership,familyStatus, mAuth.getCurrentUser().getEmail());


            int selectBicycle = (FamilyFragment.checkBoxBicycle.isChecked()) ? 1 : 0;
            int qBicycle = (selectBicycle > 0) ? Integer.parseInt(FamilyFragment.editTextBicycleNo.getText().toString()) : 0;
            int selectBoat = (FamilyFragment.checkBoxBoat.isChecked())? 1 : 0;
            int qBoat = (selectBoat > 0) ? Integer.parseInt(FamilyFragment.editTextBoatNo.getText().toString()) : 0;
            int selectBus = (FamilyFragment.checkBoxBus.isChecked())? 1 : 0;
            int qBus = (selectBus > 0) ? Integer.parseInt(FamilyFragment.editTextBusNo.getText().toString()) : 0;
            int selectCar = (FamilyFragment.checkBoxCar.isChecked())? 1 : 0;
            int qCar = (selectCar > 0) ? Integer.parseInt(FamilyFragment.editTextCarNo.getText().toString()) : 0;
            int selectJeep = (FamilyFragment.checkBoxJeep.isChecked())? 1 : 0;
            int qJeep = (selectJeep > 0) ? Integer.parseInt(FamilyFragment.editTextJeepNo.getText().toString()) : 0;
            int selectMotorBoat = (FamilyFragment.checkBoxMotorBoat.isChecked())? 1 : 0;
            int qMotorBoat = (selectMotorBoat > 0) ? Integer.parseInt(FamilyFragment.editTextMotorBoatNo.getText().toString()) : 0;
            int selectMotorcycle = (FamilyFragment.checkBoxMotorcycle.isChecked())? 1 : 0;
            int qMotorcycle = (selectMotorcycle > 0) ? Integer.parseInt(FamilyFragment.editTextMotorcycleNo.getText().toString()) : 0;
            int selectOwner = (FamilyFragment.checkBoxOwner.isChecked())? 1 : 0;
            int qOwner = (selectOwner > 0) ? Integer.parseInt(FamilyFragment.editTextOwnerNo.getText().toString()) : 0;
            int selectPedicab = (FamilyFragment.checkBoxPedicab.isChecked())? 1 : 0;
            int qPedicab = (selectPedicab > 0) ? Integer.parseInt(FamilyFragment.editTextPedicabNo.getText().toString()) : 0;
            int selectPickup = (FamilyFragment.checkBoxPickup.isChecked())? 1 : 0;
            int qPickup = (selectPickup > 0) ? Integer.parseInt(FamilyFragment.editTextPickupNo.getText().toString()) : 0;
            int selectPumpBoat = (FamilyFragment.checkBoxPumpBoat.isChecked())? 1 : 0;
            int qPumpBoat = (selectPumpBoat > 0) ? Integer.parseInt(FamilyFragment.editTextPumpBoatNo.getText().toString()) : 0;
            int selectRaft = (FamilyFragment.checkBoxRaft.isChecked())? 1 : 0;
            int qRaft = (selectRaft > 0) ? Integer.parseInt(FamilyFragment.editTextRaftNo.getText().toString()) : 0;
            int selectSuv = (FamilyFragment.checkBoxSuv.isChecked())? 1 : 0;
            int qSuv = (selectSuv > 0) ? Integer.parseInt(FamilyFragment.editTextSuvNo.getText().toString()) : 0;
            int selectTric = (FamilyFragment.checkBoxTricycle.isChecked())? 1 : 0;
            int qTric = (selectTric > 0) ? Integer.parseInt(FamilyFragment.editTextTricycleNo.getText().toString()) : 0;
            int selectTruck = (FamilyFragment.checkBoxTruck.isChecked())? 1 : 0;
            int qTruck = (selectTruck > 0) ? Integer.parseInt(FamilyFragment.editTextTruckNo.getText().toString()) : 0;
            int selectVan = (FamilyFragment.checkBoxVan.isChecked())? 1 : 0;
            int qVan = (selectVan > 0) ? Integer.parseInt(FamilyFragment.editTextVanNo.getText().toString()) : 0;

            familyListener.sendFamilyValue(FamilyFragment.seekBarNoFamMembers.getProgress(),
                    Integer.parseInt(FamilyFragment.spinnerYear.getSelectedItem().toString()),FamilyFragment.spinnerRegion.getSelectedItem().toString(),
                    FamilyFragment.spinnerProvince.getSelectedItem().toString(),FamilyFragment.spinnerMunicipal.getSelectedItem().toString(),
                    FamilyFragment.spinnerBarangay.getSelectedItem().toString(),FamilyFragment.spinnerISP.getSelectedItem().toString(),
                    selectBicycle,qBicycle,selectBoat,qBoat,selectBus,qBus,selectCar,qCar,selectJeep,qJeep,selectMotorBoat,qMotorBoat,selectMotorcycle,qMotorcycle,
                    selectOwner,qOwner,selectPedicab,qPedicab,
                    selectPickup,qPickup,selectPumpBoat,qPumpBoat,selectRaft,qRaft,selectSuv,qSuv,selectTric,qTric,selectTruck,qTruck,selectVan,qVan);

            healthListener.sendHealthValue(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19);
            environmentListener.sendEnvironmentValue(1,1,1,1,1,1,1,1,1,1,1,1,1);
            }
        else {
            Toast.makeText(this,"Please complete all the forms",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkActiveFragment(String tag){
            Fragment fragment =  getSupportFragmentManager().findFragmentByTag(tag);
            boolean check = false;

            if (fragment != null) {
                if (fragment.isVisible()) {
                    switch (tag){
                        case "FamilyIdentification":
                            //saveObjectFamilyIdentification();
                            check = true;
                            break;
                        case "Family":
                            //saveObjectFamily();
                            check = true;
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
        return check;
    }

    @Override
    public void onBackPressed() {
        finish();
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
    public void onSuccessFamilyIdentification(String message) {
        mProgress.dismiss();
    }

    @Override
    public void onErrorFamilyIdentification(String message) {
        mProgress.dismiss();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorNumFam(String message) {
        mProgress.dismiss();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorBicycleNo(String message) {
        mProgress.dismiss();
    }

    @Override
    public void onSuccessFamily(String message) {
        mProgress.dismiss();
        startActivity(new Intent(MainSurveyActivity.this,MainActivity.class));
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorFamily(String message) {
        mProgress.dismiss();
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessHealth(String message) {
        mProgress.dismiss();
    }

    @Override
    public void onErrorHealth(String message) {

    }

    @Override
    public void onSuccessEnvironment(String message) {
        mProgress.dismiss();
    }

    @Override
    public void onErrorEnvironment(String message) {

    }
}
