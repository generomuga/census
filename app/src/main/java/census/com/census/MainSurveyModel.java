package census.com.census;

public interface MainSurveyModel {

    interface OnFamilyIdentificationModel{
        void onSuccess();
    }

    void sendFirebase(String fname);

}
