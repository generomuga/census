package census.com.census;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainSurveyActivity extends AppCompatActivity {

    private Toolbar toolBarSurvey;
    String tag;
    String errorMsgReq = "This field is required!";
    String errorMsgNum = "Not a number";
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);

        //to add toolbar in the activity
        toolBarSurvey = (Toolbar) findViewById(R.id.toolBarSurvey);
        setSupportActionBar(toolBarSurvey);
        getSupportActionBar().setTitle("Family Information");

        //checks for save instance state
        if(savedInstanceState == null){
            tag = "FamilyIdentification";
            fragment = new FamilyIdentificationFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentMain,fragment,tag).commit();
        }
    }

    public void changeFragment(View view){
        switch (view.getId()){
            case R.id.imageButtonFamilyId:
                tag = "FamilyIdentification";
                getSupportActionBar().setTitle("Family Identification");
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
                fragment = new HealthFragment();
                switchFragment(fragment,"Health");
                break;

            case R.id.imageButtonEnvironment:
                tag = "Environment";
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
                checkActiveFragment(tag);
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
                        //Toast.makeText(this,"FamilyIdentification",Toast.LENGTH_SHORT).show();
                        saveObjectFamilyIdentification();
                        break;
                    case "Family":
                        Toast.makeText(this,"Family",Toast.LENGTH_SHORT).show();
                        saveObjectFamily();
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

    private void saveObjectFamilyIdentification(){
        FamilyIdentification familyIdentification = new FamilyIdentification();

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextFName.getText().toString().trim())){
            familyIdentification.setfName(FamilyIdentificationFragment.editTextFName.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextFName.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextMName.getText().toString().trim())) {
            familyIdentification.setmName(FamilyIdentificationFragment.editTextMName.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextMName.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextLName.getText().toString().trim())) {
            familyIdentification.setlName(FamilyIdentificationFragment.editTextLName.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextLName.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim())){
            familyIdentification.setHouseNp(FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextHouseNo.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim())){
            familyIdentification.setStreetNo(FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextHouseNo.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextBarangay.getText().toString().trim())){
            familyIdentification.setBarangay(FamilyIdentificationFragment.editTextBarangay.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextBarangay.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextMunicipality.getText().toString().trim())) {
            familyIdentification.setMunicipality(FamilyIdentificationFragment.editTextMunicipality.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextMunicipality.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextProvince.getText().toString().trim())) {
            familyIdentification.setProvince(FamilyIdentificationFragment.editTextProvince.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextProvince.setError(errorMsgReq);
        }

        if(FamilyIdentificationFragment.radioButtonResident.isChecked()) {
            familyIdentification.setResidency(1);
        }
        else {
            familyIdentification.setResidency(0);
        }

        if(FamilyIdentificationFragment.radioButtonOwner.isChecked()){
            familyIdentification.setOwnership(1);
        }
        else{
            familyIdentification.setOwnership(0);
        }

        if(FamilyIdentificationFragment.radioButtonActive.isChecked()){
            familyIdentification.setFamilyStatus(1);
        }
        else{
            familyIdentification.setFamilyStatus(0);
        }
    }

    private void saveObjectFamily(){
        Family family = new Family();

        /*private int noFamilyMembers;
        private int yearResided;
        private String placeOrigin;
        private String contactNo;
        private String isp;
        private int selectBicycle;
        private int noBicycle;
        private int selectBoat;
        private int noBoat;
        private int selectBus;
        private int noBus;
        private int selectCar;
        private int noCar;
        private int selectJeepney;
        private int noJeepney;
        private int selectMotorboat;
        private int noMotorboat;
        private int selectMotorcycle;
        private int noMotorCycle;
        private int selectOwnerJeep;
        private int noOwnerJeep;
        private int selectPedicab;
        private int noPedicab;
        private int selectPickup;
        private int noPickup;
        private int selectPumpBoat;
        private int noPumpBoat;
        private int selectRaft;
        private int noRaft;
        private int selectSuv;
        private int noSuv;
        private int selectTricycle;
        private int noTricycle;
        private int selectTruck;
        private int noTruck;
        private int selectVan;
        private int noVan;*/


        if(FamilyFragment.seekBarNoFamMembers.getProgress() !=0 ){
            family.setNoFamilyMembers(FamilyFragment.seekBarNoFamMembers.getProgress());
        }
        else{
            //show error
        }

        if(!TextUtils.isEmpty(FamilyFragment.editTextOrigin.getText().toString().trim())){
            family.setPlaceOrigin(FamilyFragment.editTextOrigin.getText().toString().trim());
        }
        else{
            FamilyFragment.editTextOrigin.setError(errorMsgReq);
        }

        if(!TextUtils.isEmpty(FamilyFragment.editTextContactNo.getText().toString().trim())){
            family.setContactNo(FamilyFragment.editTextContactNo.getText().toString().trim());
        }
        else{
            FamilyFragment.editTextContactNo.setError(errorMsgReq);
        }

        family.setIsp(FamilyFragment.spinnerISP.getSelectedItem().toString());

        if(FamilyFragment.checkBoxBicycle.isChecked()){
            family.setSelectBicycle(1);
            //check if integer
            if(TextUtils.isDigitsOnly(FamilyFragment.editTextBicycleNo.getText().toString().trim())) {
                family.setNoBicycle(Integer.parseInt(FamilyFragment.editTextBicycleNo.getText().toString().trim()));
            }
            else {
                FamilyFragment.editTextBicycleNo.setError(errorMsgNum);
            }
        }
        else{
            family.setSelectBicycle(0);
        }
    }
}
