package census.com.census.presenter;


public interface SurveyPresenter {
    interface OnFamilyIdentification{
        void checkFname(String fname);
        void checkMname(String mname);
    }
}
