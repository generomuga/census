package census.com.census;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainSurveyActivity extends AppCompatActivity {

    private Toolbar toolBarSurvey;
    String tag;
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

        String errorMsg = "This field is required!";

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextFName.getText().toString().trim())){
            familyIdentification.setfName(FamilyIdentificationFragment.editTextFName.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextFName.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextMName.getText().toString().trim())) {
            familyIdentification.setmName(FamilyIdentificationFragment.editTextMName.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextMName.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextLName.getText().toString().trim())) {
            familyIdentification.setlName(FamilyIdentificationFragment.editTextLName.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextLName.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim())){
            familyIdentification.setHouseNp(FamilyIdentificationFragment.editTextHouseNo.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextHouseNo.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim())){
            familyIdentification.setStreetNo(FamilyIdentificationFragment.editTextStreetNo.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextHouseNo.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextBarangay.getText().toString().trim())){
            familyIdentification.setBarangay(FamilyIdentificationFragment.editTextBarangay.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextBarangay.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextMunicipality.getText().toString().trim())) {
            familyIdentification.setMunicipality(FamilyIdentificationFragment.editTextMunicipality.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextMunicipality.setError(errorMsg);
        }

        if(!TextUtils.isEmpty(FamilyIdentificationFragment.editTextProvince.getText().toString().trim())) {
            familyIdentification.setProvince(FamilyIdentificationFragment.editTextProvince.getText().toString().trim());
        }
        else{
            FamilyIdentificationFragment.editTextProvince.setError(errorMsg);
        }

        int selectedId = FamilyIdentificationFragment.radioGroupResidency.getCheckedRadioButtonId();
        Toast.makeText(this, Integer.toString(selectedId),Toast.LENGTH_SHORT).show();

        //familyIdentification.setResidency();
        //familyIdentification.setOwnership();
        //familyIdentification.setFamilyStatus();
    }
}
