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

}
