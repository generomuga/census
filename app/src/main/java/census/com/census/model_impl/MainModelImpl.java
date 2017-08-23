package census.com.census.model_impl;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import census.com.census.events.DeleteSuccessEvent;
import census.com.census.model.MainModel;

public class MainModelImpl implements MainModel{

    private DatabaseReference mDatabase;

    public MainModelImpl() {

        mDatabase = FirebaseDatabase.getInstance().getReference("data");
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
                EventBus.getDefault().post(new DeleteSuccessEvent());
            }
        });

        mFamily.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //EventBus.getDefault().post(new DeleteSuccessEvent());
            }
        });

        mHealth.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               // EventBus.getDefault().post(new DeleteSuccessEvent());
            }
        });

        mEnvironment.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }
}
