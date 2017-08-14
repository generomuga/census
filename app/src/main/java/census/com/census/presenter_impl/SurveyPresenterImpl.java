package census.com.census.presenter_impl;

import census.com.census.model.SurveyModel;
import census.com.census.model_impl.SurveyModelImpl;
import census.com.census.presenter.SurveyPresenter;
import census.com.census.view.SurveyView;

public class SurveyPresenterImpl implements SurveyPresenter.OnFamilyIdentification,SurveyPresenter.OnFamily,SurveyModel.OnFamilyIdentification.OnResult, SurveyModel.OnFamily.OnResult{

    SurveyView.OnFamilyIdentification surveyViewListener;
    SurveyView.OnFamily surveyFamilyListener;

    SurveyModel.OnFamilyIdentification surveyModel;
    SurveyModel.OnFamily surveyModelFamily;

    public SurveyPresenterImpl(SurveyView.OnFamilyIdentification surveyViewListener,SurveyView.OnFamily surveyFamilyListener) {
        this.surveyViewListener = surveyViewListener;
        this.surveyFamilyListener = surveyFamilyListener;
        surveyModel = (SurveyModel.OnFamilyIdentification) (surveyModelFamily = new SurveyModelImpl(this,this));
    }

    @Override
    public boolean checkFname(String fname){
       if(fname.isEmpty()){
           surveyViewListener.setErrorFname("This field is required!");
           return false;
       }
       else{
           return true;
       }
    }

    @Override
    public boolean checkMname(String mname) {
        if(mname.isEmpty()){
            surveyViewListener.setErrorMname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkLname(String lname) {
        if(lname.isEmpty()){
            surveyViewListener.setErrorLname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkHouseNo(String houseNo) {
        if(houseNo.isEmpty()){
            surveyViewListener.setErrorHouseNo("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkStreetNo(String streetNo) {
        if(streetNo.isEmpty()){
            surveyViewListener.setErrorStreetNo("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public void sendValue(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status, String user) {
        surveyModel.sendData(fName,mName,lName,region,province,municipality,barangay,houseNo,streetNo,residency,ownership,status,user);
    }
    @Override
    public void setErrorData(String message) {

    }

    @Override
    public void onSuccess() {
        surveyViewListener.onSuccess("Successfully sent!");
    }

    @Override
    public void sendValue(int familyNo, int yearReside, String region, String province, String municipality, String barangay, String isp, int bicycle, int qBicycle, int boat, int qBoat, int bus, int qBus, int car, int qCar, int jeep, int qJeep, int motorboat, int qMotorboat, int motorcycle, int qMotorcyle, int owner, int qOwner, int pedicab, int qPedicab, int pickup, int qPickup, int pumpboat, int qPumpboat, int raft, int qRaft, int suv, int qSuv, int tric, int qTric, int truck, int qTruck, int van, int qVan) {
        if(familyNo == 0){
            surveyFamilyListener.setErrorNumFam("Please provide number of family members!");
            return;
        }
    }

    @Override
    public void setErrorFamilyData(String message) {

    }

    @Override
    public void onSuccessFamily() {

    }
}
