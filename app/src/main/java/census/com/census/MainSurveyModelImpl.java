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
    public void sendFamilyIdentification(String fname,String mName) {
        FamilyIdentification familyIdentification = new FamilyIdentification();
        familyIdentification.setfName(fname);
        familyIdentification.setmName(mName);

        DatabaseReference mFamilyIdentification = mDatabase.child("familyIdentification");
        key = mDatabase.push().getKey();
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
