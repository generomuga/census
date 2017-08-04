package census.com.census;

import android.text.TextUtils;

public class MainSurveyPresenterImpl implements MainSurveyPresenter, MainSurveyModel.OnFamilyIdentificationModel{

    MainSurveyView mainSurveyView;
    MainSurveyModel mainSurveyModel;

    public MainSurveyPresenterImpl(MainSurveyView mainSurveyView) {
        this.mainSurveyView = mainSurveyView;
        mainSurveyModel = new MainSurveyModelImpl(this);
    }

    @Override
    public void checkInput(String fName,String mName) {
        if(TextUtils.isEmpty(fName)){
            mainSurveyView.onError();
            return;
        }

        if(TextUtils.isEmpty(mName)){
            mainSurveyView.onError();
            return;
        }

        mainSurveyModel.sendFirebase(fName, mName);
    }

    @Override
    public void onSuccess() {

    }

}
