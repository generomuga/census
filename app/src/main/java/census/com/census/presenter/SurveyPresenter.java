package census.com.census.presenter;


public interface SurveyPresenter {
    interface OnFamilyIdentification{
        boolean checkFname(String fname);
        boolean checkMname(String mname);
        boolean checkLname(String lname);
        boolean checkHouseNo(String houseNo);
        boolean checkStreetNo(String streetNo);
    }
}
