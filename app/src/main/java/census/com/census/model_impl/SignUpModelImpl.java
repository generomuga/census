package census.com.census.model_impl;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import census.com.census.model.SignUpModel;

public class SignUpModelImpl implements SignUpModel {

    OnSignUpListener listener;
    private FirebaseAuth mAuth;


    public SignUpModelImpl(OnSignUpListener listener) {
        this.listener = listener;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void register(final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Log.e("Register error",task.getException().getMessage().toString());
                    listener.onErrorRegister(task.getException().getMessage().toString());
                }
                else{
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        Log.e("Reset error",task.getException().getMessage().toString());
                                    }
                                    else{
                                        listener.onSuccess("Please verify your account to your email!");
                                    }
                                }
                            });
                }
            }
        });

    }
}
