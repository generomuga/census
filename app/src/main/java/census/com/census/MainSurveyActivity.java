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
            if(!TextUtils.isEmpty(FamilyFragment.editTextBicycleNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextBicycleNo.getText().toString().trim())) {
                    family.setNoBicycle(Integer.parseInt(FamilyFragment.editTextBicycleNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextBicycleNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextBicycleNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectBicycle(0);
        }

        if(FamilyFragment.checkBoxBoat.isChecked()){
            family.setSelectBoat(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextBoatNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextBoatNo.getText().toString().trim())) {
                    family.setNoBoat(Integer.parseInt(FamilyFragment.editTextBoatNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextBoatNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextBoatNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectBoat(0);
        }

        if(FamilyFragment.checkBoxBus.isChecked()){
            family.setSelectBus(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextBusNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextBusNo.getText().toString().trim())) {
                    family.setNoBus(Integer.parseInt(FamilyFragment.editTextBusNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextBusNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextBusNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectBus(0);
        }

        if(FamilyFragment.checkBoxCar.isChecked()){
            family.setSelectCar(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextCarNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextCarNo.getText().toString().trim())) {
                    family.setNoCar(Integer.parseInt(FamilyFragment.editTextCarNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextCarNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextCarNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectCar(0);
        }

        if(FamilyFragment.checkBoxJeep.isChecked()){
            family.setSelectJeep(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextJeepNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextJeepNo.getText().toString().trim())) {
                    family.setNoJeep(Integer.parseInt(FamilyFragment.editTextJeepNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextJeepNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextJeepNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectJeep(0);
        }

        if(FamilyFragment.checkBoxMotorBoat.isChecked()){
            family.setSelectMotorboat(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextMotorBoatNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextMotorBoatNo.getText().toString().trim())) {
                    family.setNoMotorboat(Integer.parseInt(FamilyFragment.editTextMotorBoatNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextMotorBoatNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextMotorBoatNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectMotorboat(0);
        }

        if(FamilyFragment.checkBoxMotorcycle.isChecked()){
            family.setSelectMotorcycle(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextMotorcycleNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextMotorcycleNo.getText().toString().trim())) {
                    family.setNoMotorCycle(Integer.parseInt(FamilyFragment.editTextMotorcycleNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextMotorcycleNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextMotorcycleNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectMotorcycle(0);
        }

        if(FamilyFragment.checkBoxOwner.isChecked()){
            family.setSelectOwnerJeep(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextOwnerNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextOwnerNo.getText().toString().trim())) {
                    family.setNoOwnerJeep(Integer.parseInt(FamilyFragment.editTextOwnerNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextOwnerNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextOwnerNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectOwnerJeep(0);
        }

        if(FamilyFragment.checkBoxPedicab.isChecked()){
            family.setSelectPedicab(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextPedicabNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextPedicabNo.getText().toString().trim())) {
                    family.setNoPedicab(Integer.parseInt(FamilyFragment.editTextPedicabNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextPedicabNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextPedicabNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectPedicab(0);
        }

        if(FamilyFragment.checkBoxPickup.isChecked()){
            family.setSelectPickup(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextPickupNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextPickupNo.getText().toString().trim())) {
                    family.setNoPickup(Integer.parseInt(FamilyFragment.editTextPickupNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextPickupNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextPickupNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectPickup(0);
        }

        if(FamilyFragment.checkBoxPumpBoat.isChecked()){
            family.setSelectPumpBoat(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextPumpBoatNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextPumpBoatNo.getText().toString().trim())) {
                    family.setNoPumpBoat(Integer.parseInt(FamilyFragment.editTextPumpBoatNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextPumpBoatNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextPumpBoatNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectPumpBoat(0);
        }

        if(FamilyFragment.checkBoxRaft.isChecked()){
            family.setSelectRaft(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextRaftNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextRaftNo.getText().toString().trim())) {
                    family.setNoRaft(Integer.parseInt(FamilyFragment.editTextRaftNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextRaftNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextRaftNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectRaft(0);
        }

        if(FamilyFragment.checkBoxSuv.isChecked()){
            family.setSelectSuv(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextSuvNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextSuvNo.getText().toString().trim())) {
                    family.setNoSuv(Integer.parseInt(FamilyFragment.editTextSuvNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextSuvNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextSuvNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectSuv(0);
        }

        if(FamilyFragment.checkBoxTricycle.isChecked()){
            family.setSelectTricycle(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextTricycleNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextTricycleNo.getText().toString().trim())) {
                    family.setNoTricycle(Integer.parseInt(FamilyFragment.editTextTricycleNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextTricycleNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextTricycleNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectTricycle(0);
        }

        if(FamilyFragment.checkBoxTruck.isChecked()){
            family.setSelectTruck(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextTruckNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextTruckNo.getText().toString().trim())) {
                    family.setNoTruck(Integer.parseInt(FamilyFragment.editTextTruckNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextTruckNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextTruckNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectTruck(0);
        }

        if(FamilyFragment.checkBoxVan.isChecked()){
            family.setSelectVan(1);
            if(!TextUtils.isEmpty(FamilyFragment.editTextVanNo.getText().toString().trim())) {
                if (TextUtils.isDigitsOnly(FamilyFragment.editTextVanNo.getText().toString().trim())) {
                    family.setNoVan(Integer.parseInt(FamilyFragment.editTextVanNo.getText().toString().trim()));
                } else {
                    FamilyFragment.editTextVanNo.setError(errorMsgNum);
                }
            }
            else {
                FamilyFragment.editTextVanNo.setError(errorMsgReq);
            }
        }
        else{
            family.setSelectVan(0);
        }

    }
}
