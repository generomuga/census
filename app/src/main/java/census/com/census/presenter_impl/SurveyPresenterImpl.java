package census.com.census.presenter_impl;

import census.com.census.presenter.SurveyPresenter;
import census.com.census.view.SurveyView;

public class SurveyPresenterImpl implements SurveyPresenter.OnFamilyIdentification {

    SurveyView.OnFamilyIdentification surveyViewListener;

    public SurveyPresenterImpl(SurveyView.OnFamilyIdentification surveyViewListener) {
        this.surveyViewListener = surveyViewListener;
    }

    @Override
    public void checkFname(String fname) {
       if(fname.isEmpty()){
           surveyViewListener.setErrorFname("This field is required!");
           return;
       }
    }

    @Override
    public void checkMname(String mname) {
        if(mname.isEmpty()){
            surveyViewListener.setErrorMname("This field is required!");
            return;
        }
    }
}
