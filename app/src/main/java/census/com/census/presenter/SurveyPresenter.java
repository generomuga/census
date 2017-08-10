package census.com.census.presenter;


public interface SurveyPresenter {
    interface OnFamilyIdentification{
        boolean checkFname(String fname);
        boolean checkMname(String mname);
        boolean checkLname(String lname);
        boolean checkHouseNo(String houseNo);
        boolean checkStreetNo(String streetNo);
        void sendValue(String fName,String mName,String lName,String region,String province,
                                      String municipality,String barangay,String houseNo,String streetNo, int residency, int ownership, int status);
    }
}
