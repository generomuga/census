package census.com.census;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpModelImpl implements SignUpModel{

    OnSignUpListener listener;
    private FirebaseAuth mAuth;


    public SignUpModelImpl(OnSignUpListener listener) {
        this.listener = listener;

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void register(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                //
            }
        });
    }
}
