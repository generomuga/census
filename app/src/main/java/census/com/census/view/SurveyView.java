package census.com.census.view;

public interface SurveyView {
    interface OnFamilyIdentification{
        void setErrorFname(String message);
        void setErrorMname(String message);
        void setErrorLname(String message);
        void setErrorHouseNo(String message);
        void setErrorStreetNo(String message);
        void onSuccessFamilyIdentification(String message);
        void onErrorFamilyIdentification(String message);
    }
    interface OnFamily{
        void setErrorNumFam(String message);
        //void setErrorQuantity(String message);
        void onSuccessFamily(String message);
        void onErrorFamily(String message);
    }

    //test
    void onSuccess(String message);
    void onErrorData(String message);

}
