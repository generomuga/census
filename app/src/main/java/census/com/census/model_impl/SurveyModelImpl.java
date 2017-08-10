package census.com.census.model_impl;


import census.com.census.model.SurveyModel;

public class SurveyModelImpl implements SurveyModel.OnFamilyIdentification{

    SurveyModel.OnFamilyIdentification.OnResult onResultListener;

    public SurveyModelImpl(OnResult onResultListener) {
        this.onResultListener = onResultListener;
    }

    @Override
    public void sendData(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status) {

    }
}
