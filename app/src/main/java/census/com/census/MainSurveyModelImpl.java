package census.com.census;


public class MainSurveyModelImpl implements MainSurveyModel {

    OnFamilyIdentificationModel listener;

    public MainSurveyModelImpl(OnFamilyIdentificationModel listener) {
        this.listener = listener;
    }

    @Override
    public void sendFirebase(String fname) {

    }
}
