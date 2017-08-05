package census.com.census;

public interface MainSurveyModel {

    void sendFamilyIdentification(String fName,String mName,String lName,String region,String province,String municipality, String barangay);
    void sendFamily(String isp);
}
