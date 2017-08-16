package census.com.census.presenter_impl;

import census.com.census.presenter.SurveyPresenter;
import census.com.census.view.SurveyView;

public class SurveyPresenterImpl implements SurveyPresenter.OnFamilyIdentification,SurveyPresenter.OnFamily{

    SurveyView.OnFamilyIdentification surveyViewFamilyIdentificationListener;
    SurveyView.OnFamily surveyViewFamilyListener;

    public SurveyPresenterImpl(SurveyView.OnFamilyIdentification surveyViewFamilyIdentificationListener,SurveyView.OnFamily surveyViewFamilyListener) {
        this.surveyViewFamilyIdentificationListener = surveyViewFamilyIdentificationListener;
        this.surveyViewFamilyListener = surveyViewFamilyListener;
        //surveyModel = new SurveyModelImpl(this);
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
    public boolean checkNoFamily(int noFamily) {
        if(noFamily == 0){
            surveyViewFamilyListener.setErrorNumFam("Number of family members must have a value!");
            return false;        }
        else{
            return true;
        }
    }



    /*@Override
    public void sendValue(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status, String user) {
        surveyModel.sendData(fName,mName,lName,region,province,municipality,barangay,houseNo,streetNo,residency,ownership,status,user);
    }*/

}
