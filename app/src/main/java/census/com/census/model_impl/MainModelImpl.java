package census.com.census.model_impl;


import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import census.com.census.events.DeleteErrorEnvironmentEvent;
import census.com.census.events.DeleteErrorFamilyEvent;
import census.com.census.events.DeleteErrorFamilyIdentificationEvent;
import census.com.census.events.DeleteErrorHealthEvent;
import census.com.census.events.DeleteSuccessEnvironmentEvent;
import census.com.census.events.DeleteSuccessFamilyEvent;
import census.com.census.events.DeleteSuccessFamilyIdentificationEvent;
import census.com.census.events.DeleteSuccessHealthEvent;
import census.com.census.model.MainModel;

public class MainModelImpl implements MainModel{

    private DatabaseReference mDatabase;

    public MainModelImpl() {
        mDatabase = FirebaseDatabase.getInstance().getReference("data");
        mDatabase.keepSynced(true);
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
                if(task != null) {
                    EventBus.getDefault().post(new DeleteSuccessFamilyIdentificationEvent());
                }
                else{
                    EventBus.getDefault().post(new DeleteErrorFamilyIdentificationEvent(task.getException().getMessage()));
                }
            }
        });

        mFamily.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task != null){
                    EventBus.getDefault().post(new DeleteSuccessFamilyEvent());
                }
                else{
                    EventBus.getDefault().post(new DeleteErrorFamilyEvent(task.getException().getMessage()));
                }
            }
        });

        mHealth.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task != null){
                    EventBus.getDefault().post(new DeleteSuccessHealthEvent());
                }
                else{
                    EventBus.getDefault().post(new DeleteErrorHealthEvent(task.getException().getMessage()));
                }
            }
        });

        mEnvironment.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task != null){
                    EventBus.getDefault().post(new DeleteSuccessEnvironmentEvent());
                }
                else{
                    EventBus.getDefault().post(new DeleteErrorEnvironmentEvent(task.getException().getMessage()));
                }
            }
        });
    }
}
    