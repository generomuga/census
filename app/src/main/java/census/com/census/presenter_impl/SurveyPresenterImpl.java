package census.com.census.presenter_impl;

import census.com.census.model.SurveyModel;
import census.com.census.model_impl.SurveyModelImpl;
import census.com.census.presenter.SurveyPresenter;
import census.com.census.view.SurveyView;

public class SurveyPresenterImpl implements SurveyPresenter.OnFamilyIdentification,SurveyModel.OnFamilyIdentification.OnResult,SurveyPresenter.OnFamily,SurveyModel.OnFamily.OnResult{

    SurveyView.OnFamilyIdentification surveyViewFamilyIdentificationListener;
    SurveyView.OnFamily surveyViewFamilyListener;

    SurveyModel.OnFamilyIdentification surveyFamilyIdentificationModel;
    SurveyModel.OnFamily surveyFamilyModel;

    public SurveyPresenterImpl(SurveyView.OnFamilyIdentification surveyViewFamilyIdentificationListener,SurveyView.OnFamily surveyViewFamilyListener) {
        this.surveyViewFamilyIdentificationListener = surveyViewFamilyIdentificationListener;
        this.surveyViewFamilyListener = surveyViewFamilyListener;
        surveyFamilyIdentificationModel = (SurveyModel.OnFamilyIdentification) (surveyFamilyModel = new SurveyModelImpl(this,this));
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

    /*@Override
    public void sendValue(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status, String user) {
        surveyModel.sendData(fName,mName,lName,region,province,municipality,barangay,houseNo,streetNo,residency,ownership,status,user);
    }*/

}
