package census.com.census.view;

public interface SurveyView {
    interface OnFamilyIdentification{
        void setErrorFname(String message);
        void setErrorMname(String message);
    }
}
