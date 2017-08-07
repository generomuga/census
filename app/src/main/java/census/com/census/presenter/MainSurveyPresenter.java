package census.com.census.presenter;

public interface MainSurveyPresenter {
   void checkFamilyIdentification(String fname,String mname,String lName, String region, String province,
                                  String municipality, String barangay,String houseNo,String streetNo,int residency,int ownership, int status);
   void checkFamily(String isp);
}