package census.com.census.model_impl;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import census.com.census.model.MainModel;

public class MainModelImpl implements MainModel{

    private DatabaseReference mDatabase;

    public MainModelImpl() {

        mDatabase = FirebaseDatabase.getInstance().getReference("data");

        /*DatabaseReference drFamilyIdentification = FirebaseDatabase.getInstance().getReference("data").child("familyIdentification").child(dataId);
        DatabaseReference drFamily = FirebaseDatabase.getInstance().getReference("data").child("family").child(dataId);
        DatabaseReference drHealth = FirebaseDatabase.getInstance().getReference("data").child("health").child(dataId);
        DatabaseReference drEnvironment = FirebaseDatabase.getInstance().getReference("data").child("environment").child(dataId);

        drFamilyIdentification.removeValue();
        drFamily.removeValue();
        drHealth.removeValue();
        drEnvironment.removeValue();
        */
    }

    @Override
    public void deleteData(String id) {
        DatabaseReference mFamilyIdentification = mDatabase.child("familyIdentification").child(id);
        DatabaseReference mFamily = mDatabase.child("family").child(id);
        DatabaseReference mHealth = mDatabase.child("health").child(id);
        DatabaseReference mEnvironment = mDatabase.child("environment").child(id);

        mFamilyIdentification.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        mFamily.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        mHealth.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        mEnvironment.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
}
