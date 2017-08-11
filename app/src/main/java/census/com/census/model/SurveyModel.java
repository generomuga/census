package census.com.census.model;

public interface SurveyModel {
    interface OnFamilyIdentification{
        interface OnResult{
            void setErrorData(String message);
            void onSuccess();
        }
        void sendData(String fName,String mName,String lName,String region,String province,
                      String municipality,String barangay,String houseNo,String streetNo, int residency, int ownership, int status, String user);
    }

}
