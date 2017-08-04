package census.com.census;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainSurveyModelImpl implements MainSurveyModel {

    OnFamilyIdentificationModel listener;

    DatabaseReference mDatabase;

    public MainSurveyModelImpl(OnFamilyIdentificationModel listener) {
        this.listener = listener;
    }

    @Override
    public void sendFirebase(String fname) {
        mDatabase = FirebaseDatabase.getInstance().getReference("first");
        mDatabase.setValue(fname);
    }
}
