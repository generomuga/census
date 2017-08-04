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
    public void checkFamilyIdentification(String fName,String mName) {
        if(TextUtils.isEmpty(fName)){
            mainSurveyView.onError();
            return;
        }

        if(TextUtils.isEmpty(mName)){
            mainSurveyView.onError();
            return;
        }

        mainSurveyModel.sendFamilyIdentification(fName, mName);
    }

    @Override
    public void checkFamily(String isp) {
        if(TextUtils.isEmpty(isp)){
            mainSurveyView.onError();
            return;
        }
        mainSurveyModel.sendFamily(isp);
    }

}
