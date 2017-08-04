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
    public void checkInput(String fname) {
        if(TextUtils.isEmpty(fname)){
            mainSurveyView.onError();
            return;
        }
        mainSurveyModel.sendFirebase(fname);
    }

    @Override
    public void onSuccess() {

    }

}
