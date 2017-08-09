package census.com.census.model_impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import census.com.census.model.ForgotPasswordModel;

public class ForgotPasswordModelImpl implements ForgotPasswordModel{

    FirebaseAuth mAuth;

    OnForgotPasswordListener listener;

    public ForgotPasswordModelImpl(OnForgotPasswordListener listener) {
        this.listener = listener;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void sendReset(String email) {
       mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               if(!task.isSuccessful()){
                    listener.onErrorEmail(task.getException().getMessage().toString());
               }
               else{
                   listener.onSuccess();
               }
           }
       });
    }
}
