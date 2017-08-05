package census.com.census;

import android.text.TextUtils;

public class MainSurveyPresenterImpl implements MainSurveyPresenter{

    MainSurveyView mainSurveyView;
    MainSurveyModel mainSurveyModel;

    public MainSurveyPresenterImpl(MainSurveyView mainSurveyView) {
        this.mainSurveyView = mainSurveyView;
        mainSurveyModel = new MainSurveyModelImpl();
    }

    @Override
    public void checkFamilyIdentification(String fName,String mName,String lName,String region,String province,String municipality,String barangay) {
        if(TextUtils.isEmpty(fName)){
            mainSurveyView.onError("This field is required!");
            return;
        }

        if(TextUtils.isEmpty(mName)){
            mainSurveyView.onError("This field is required!");
            return;
        }

        if(TextUtils.isEmpty(lName)){
            mainSurveyView.onError("This field is required!");
            return;
        }

        if(region.isEmpty()){
            return;
        }

        if(municipality.isEmpty()){
            return;
        }

        if(barangay.isEmpty()){
            return;
        }

        mainSurveyModel.sendFamilyIdentification(fName, mName, lName, region, province, municipality, barangay);
    }

    @Override
    public void checkFamily(String isp) {
        if(TextUtils.isEmpty(isp)){
            mainSurveyView.onError("This field is required!");
            return;
        }
        mainSurveyModel.sendFamily(isp);
    }

}
