package census.com.census.model_impl;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Date;

import census.com.census.FamilyIdentification;
import census.com.census.model.SurveyModel;

public class SurveyModelImpl implements SurveyModel.OnFamilyIdentification{

    DatabaseReference mDatabase;
    private String key;

    SurveyModel.OnFamilyIdentification.OnResult onResultListener;

    public SurveyModelImpl(OnResult onResultListener) {
        this.onResultListener = onResultListener;
        mDatabase = FirebaseDatabase.getInstance().getReference("data");
    }

    @Override
    public void sendData(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status) {

        key = mDatabase.push().getKey();

        FamilyIdentification familyIdentification = new FamilyIdentification();
        familyIdentification.setId(key);
        familyIdentification.setfName(fName);
        familyIdentification.setmName(mName);
        familyIdentification.setlName(lName);
        familyIdentification.setRegion(region);
        familyIdentification.setProvince(province);
        familyIdentification.setMunicipality(municipality);
        familyIdentification.setBarangay(barangay);
        familyIdentification.setHouseNp(houseNo);
        familyIdentification.setStreetNo(streetNo);
        familyIdentification.setResidency(residency);
        familyIdentification.setOwnership(ownership);
        familyIdentification.setFamilyStatus(status);
        familyIdentification.setUser("genen");

        DatabaseReference mFamilyIdentification = mDatabase.child("familyIdentification");
        mFamilyIdentification.child(key).setValue(familyIdentification, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null){
                    onResultListener.setErrorData(databaseError.getMessage().toString());
                }
                else{
                    onResultListener.onSuccess();
                }
            }
        });
    }
}
