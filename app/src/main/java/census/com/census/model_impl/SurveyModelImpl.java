package census.com.census.model_impl;

import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

import census.com.census.Family;
import census.com.census.FamilyIdentification;
import census.com.census.model.SurveyModel;

public class SurveyModelImpl implements SurveyModel.OnFamilyIdentification,SurveyModel.OnFamily{

    DatabaseReference mDatabase;
    private String key;

    SurveyModel.OnFamilyIdentification.OnResult onResultListener;

    SurveyModel.OnFamily.OnResult onResultListenerFamily;

    public SurveyModelImpl(SurveyModel.OnFamilyIdentification.OnResult onResultListener, SurveyModel.OnFamily.OnResult onResultListenerFamily) {
        this.onResultListener = onResultListener;
        this.onResultListenerFamily = onResultListenerFamily;
        mDatabase = FirebaseDatabase.getInstance().getReference("data");
    }

    @Override
    public void sendData(String fName, String mName, String lName, String region, String province, String municipality, String barangay, String houseNo, String streetNo, int residency, int ownership, int status, String user) {

        key = mDatabase.push().getKey();

        String time = DateFormat.getDateTimeInstance().format(new Date());
        Log.i("time",time);

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
        familyIdentification.setUser(user);
        familyIdentification.setTimestamp(time);

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

    @Override
    public void sendData(int familyNo, int yearReside, String region, String province, String municipality, String barangay, String isp, int bicycle, int qBicycle, int boat, int qBoat, int bus, int qBus, int car, int qCar, int jeep, int qJeep, int motorboat, int qMotorboat, int motorcycle, int qMotorcyle, int owner, int qOwner, int pedicab, int qPedicab, int pickup, int qPickup, int pumpboat, int qPumpboat, int raft, int qRaft, int suv, int qSuv, int tric, int qTric, int truck, int qTruck, int van, int qVan) {

        String time = DateFormat.getDateTimeInstance().format(new Date());
        Log.i("time",time);

        Family family = new Family();
        family.setId(key);
        /*familyIdentification.setfName(fName);
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
        familyIdentification.setUser(user);
        familyIdentification.setTimestamp(time);*/

        DatabaseReference mFamily = mDatabase.child("family");
        mFamily.child(key).setValue(family, new DatabaseReference.CompletionListener() {
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
