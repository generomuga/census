package census.com.census.presenter_impl;

import census.com.census.model.SurveyModel;
import census.com.census.model_impl.SurveyModelImpl;
import census.com.census.presenter.SurveyPresenter;
import census.com.census.view.SurveyView;

public class SurveyPresenterImpl implements SurveyPresenter.OnFamilyIdentification,SurveyModel.OnFamilyIdentification.OnResult {

    SurveyView.OnFamilyIdentification surveyViewListener;
    SurveyModel.OnFamilyIdentification surveyModel;

    public SurveyPresenterImpl(SurveyView.OnFamilyIdentification surveyViewListener) {
        this.surveyViewListener = surveyViewListener;
        surveyModel = new SurveyModelImpl(this);
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
    public void sendValue(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status) {

    }
    @Override
    public void setErrorData(String message) {

    }

    @Override
    public void onSuccess() {

    }

}
