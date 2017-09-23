package census.com.census.presenter_impl;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import census.com.census.model.SurveyModel;
import census.com.census.model_impl.SurveyModelImpl;
import census.com.census.presenter.SurveyPresenter;
import census.com.census.view.SurveyView;

public class SurveyPresenterImpl implements SurveyPresenter.OnFamilyIdentification,SurveyModel.OnFamilyIdentification.OnResult,SurveyPresenter.OnFamily,SurveyModel.OnFamily.OnResult,
                                            SurveyPresenter.OnHealth,SurveyModel.OnHealth.OnResult, SurveyPresenter.OnEnvironment, SurveyModel.OnEnvironment.OnResult
    {

    SurveyView.OnFamilyIdentification surveyViewFamilyIdentificationListener;
    SurveyView.OnFamily surveyViewFamilyListener;
    SurveyView.OnHealth surveyViewHealthListener;
    SurveyView.OnEnvironment surveyViewEnvironmentListener;

    SurveyModel.OnFamilyIdentification surveyFamilyIdentificationModel;
    SurveyModel.OnFamily surveyFamilyModel;
    SurveyModel.OnHealth surveyHealthModel;
    SurveyModel.OnEnvironment surveyEnvironmentModel;

    public SurveyPresenterImpl(SurveyView.OnFamilyIdentification surveyViewFamilyIdentificationListener,SurveyView.OnFamily surveyViewFamilyListener,SurveyView.OnHealth surveyViewHealthListener,SurveyView.OnEnvironment surveyViewEnvironmentListener) {
        this.surveyViewFamilyIdentificationListener = surveyViewFamilyIdentificationListener;
        this.surveyViewFamilyListener = surveyViewFamilyListener;
        this.surveyViewHealthListener = surveyViewHealthListener;
        this.surveyViewEnvironmentListener = surveyViewEnvironmentListener;

        surveyFamilyIdentificationModel = (SurveyModel.OnFamilyIdentification) (surveyFamilyModel = (SurveyModel.OnFamily) (surveyHealthModel = (SurveyModel.OnHealth) (surveyEnvironmentModel = new SurveyModelImpl(this,this,this,this))));
    }

    @Override
    public boolean checkFname(String fname){
       if(fname.isEmpty()){
           surveyViewFamilyIdentificationListener.setErrorFname("This field is required!");
           return false;
       }
       else{
           return true;
       }
    }

    /*private boolean hasNumber(String name){
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(name);
        boolean bs = ms.matches();
        if (!bs) {
            if (ErrorMessage.contains("invalid"))
                ErrorMessage = ErrorMessage + "state,";
            else
                ErrorMessage = ErrorMessage + "invalid state,";

        }
    }*/


    @Override
    public boolean checkMname(String mname) {
        if(mname.isEmpty()){
            surveyViewFamilyIdentificationListener.setErrorMname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkLname(String lname) {
        if(lname.isEmpty()){
            surveyViewFamilyIdentificationListener.setErrorLname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkHouseNo(String houseNo) {
        if(houseNo.isEmpty()){
            surveyViewFamilyIdentificationListener.setErrorHouseNo("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkStreetNo(String streetNo) {
        if(streetNo.isEmpty()){
            surveyViewFamilyIdentificationListener.setErrorStreetNo("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkBicycleNo(boolean isChecked,String bicycleNo) {
        if(isChecked){
            return !bicycleNo.isEmpty();
        }
        else if(!isChecked){
            return bicycleNo.isEmpty();
        }
        else{
            surveyViewFamilyListener.setErrorBicycleNo("This field is required!");
            return false;
        }
    }

    @Override
    public void sendFamilyIdentificationValue(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status, String user) {
        surveyFamilyIdentificationModel.sendFamilyIdentificationData(fName,mName,lName,region,province,municipality,barangay,houseNo,streetNo,residency,ownership,status,user);;
    }

    @Override
    public void setErrorDataFamilyIdentification(String message) {
    }

    @Override
    public void onSuccessFamilyIdentification() {
        surveyViewFamilyIdentificationListener.onSuccessFamilyIdentification("Successfully submitted family identification");
    }

    @Override
    public boolean checkNoFamily(int noFamily) {
        if (noFamily > 0) {
            return true;
        }
        else{
            surveyViewFamilyListener.setErrorNumFam("Family should have a value!");
            return false;
        }
    }

    @Override
    public void sendFamilyValue(int familyNo, int yearReside, String region, String province, String municipality, String barangay, String isp, int bicycle, int qBicycle, int boat, int qBoat, int bus, int qBus, int car, int qCar, int jeep, int qJeep, int motorboat, int qMotorboat, int motorcycle, int qMotorcyle, int owner, int qOwner, int pedicab, int qPedicab, int pickup, int qPickup, int pumpboat, int qPumpboat, int raft, int qRaft, int suv, int qSuv, int tric, int qTric, int truck, int qTruck, int van, int qVan) {
        surveyFamilyModel.sendFamily(familyNo,yearReside,region,province,municipality,barangay,isp,bicycle,qBicycle,boat,qBoat,bus,qBus,car,qCar,jeep,qJeep,motorboat,qMotorboat,motorcycle,qMotorcyle,owner,qOwner,pedicab,qPedicab,pickup,qPickup,pumpboat,qPumpboat,raft,qRaft,suv,qSuv,tric,qTric,truck,qTruck,van,qVan);
    }

    @Override
    public void setErrorFamilyData(String message) {
        surveyViewFamilyListener.onErrorFamily(message);
    }

    @Override
    public void onSuccessFamily() {
        surveyViewFamilyListener.onSuccessFamily("Successfully submitted family");
    }

    @Override
    public void sendHealthValue(int eatComplete, int plantHerbal, int vegGarden, int useIodize, int familyPlan, int basal, int cervical, int lactation, int rhtythm, int standard, int sympho, int withdrawal, int condom, int depo, int iud, int tubal, int pills, int vasectomy, int others) {
        surveyHealthModel.sendHealth(eatComplete,plantHerbal,vegGarden,useIodize,familyPlan,basal,cervical,lactation,rhtythm,standard,sympho,withdrawal,condom,depo,iud,tubal,pills,vasectomy,others);
    }

    @Override
    public void setErrorHealthData(String message) {

    }

    @Override
    public void onSuccessHealth() {

    }

    @Override
    public void sendEnvironmentValue(int toilet, int water, int electricity, int lot, int house, int walls, int roof, int floor, int light, int cook, int garbage, int location, int ecological) {
        surveyEnvironmentModel.sendEnvironment(toilet,water,electricity,lot,house,walls,roof,floor,light,cook,garbage,location,ecological);
    }

    @Override
    public void setErrorEnvironmentData(String message) {

    }

    @Override
    public void onSuccessEnvironment() {

    }

}
