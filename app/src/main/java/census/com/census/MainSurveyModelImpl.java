package census.com.census;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainSurveyModelImpl implements MainSurveyModel {

    //OnFamilyIdentificationModel listener;

    DatabaseReference mDatabase;

    public MainSurveyModelImpl() {
        //this.listener = listener;
        mDatabase = FirebaseDatabase.getInstance().getReference("data");
    }

    @Override
    public void sendFirebase(String fname,String mName) {
        FamilyIdentification familyIdentification = new FamilyIdentification();
        familyIdentification.setfName(fname);
        familyIdentification.setmName(mName);

        DatabaseReference mFamilyIdentification = mDatabase.child("familyIdentification");
        mFamilyIdentification.push().setValue(familyIdentification);


        //mDatabase = FirebaseDatabase.getInstance().getReference("family_identification");
        //mDatabase.setValue(familyIdentification);

    }
}
