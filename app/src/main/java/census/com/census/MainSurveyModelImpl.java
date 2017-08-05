package census.com.census;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainSurveyModelImpl implements MainSurveyModel {

    String key;

    DatabaseReference mDatabase;

    public MainSurveyModelImpl() {
        mDatabase = FirebaseDatabase.getInstance().getReference("data");
    }

    @Override
    public void sendFamilyIdentification(String fname,String mName, String lName, String region, String province, String municipality, String barangay) {

        key = mDatabase.push().getKey();

        FamilyIdentification familyIdentification = new FamilyIdentification();
        familyIdentification.setId(key);
        familyIdentification.setfName(fname);
        familyIdentification.setmName(mName);
        familyIdentification.setlName(lName);
        familyIdentification.setRegion(region);
        familyIdentification.setProvince(province);
        familyIdentification.setMunicipality(municipality);
        familyIdentification.setBarangay(barangay);

        DatabaseReference mFamilyIdentification = mDatabase.child("familyIdentification");
        mFamilyIdentification.child(key).setValue(familyIdentification);
    }

    @Override
    public void sendFamily(String isp) {
        Family family = new Family();
        family.setIsp(isp);

        DatabaseReference mFamily = mDatabase.child("family");
        //key = mDatabase.push().getKey();
        mFamily.child(key).setValue(family);
    }
}
