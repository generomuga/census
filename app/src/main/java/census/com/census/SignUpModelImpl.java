package census.com.census;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class SignUpModelImpl implements SignUpModel{

    OnSignUpListener listener;
    private FirebaseAuth mAuth;


    public SignUpModelImpl(OnSignUpListener listener) {
        this.listener = listener;

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void register(String email, String password) {
        /*mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase","faield");
                }
            }
        });*/
    }
}
