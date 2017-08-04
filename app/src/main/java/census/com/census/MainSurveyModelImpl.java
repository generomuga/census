package census.com.census;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainSurveyModelImpl implements MainSurveyModel {


    private String key;
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
        mFamilyIdentification.push().setValue(familyIdentification);
        key = mFamilyIdentification.getKey();

        //mDatabase = FirebaseDatabase.getInstance().getReference("family_identification");
        //mDatabase.setValue(familyIdentification);

    }

    @Override
    public void sendFamily(String contactNo) {
        Family family = new Family();
        family.setContactNo(contactNo);

        DatabaseReference mFamily = mDatabase.child("family");
        mFamily.setValue(family);
    }
}
