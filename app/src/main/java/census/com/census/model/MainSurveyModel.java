package census.com.census.model;

public interface MainSurveyModel {

    void sendFamilyIdentification(String fName,String mName,String lName,String region,String province,
                                  String municipality,String barangay,String houseNo,String streetNo, int residency, int ownership, int status);
    void sendFamily(String isp);
}
