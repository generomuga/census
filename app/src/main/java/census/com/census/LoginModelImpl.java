package census.com.census;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import census.com.census.model.LoginModel;

public class LoginModelImpl implements LoginModel {


    OnLoginListener listener;

    private FirebaseAuth mAuth;


    public LoginModelImpl(OnLoginListener listener) {
        this.listener = listener;

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String username, String password, final OnLoginListener listener) {
        /*if(username.equals("gene@a.com") && password.equals("gene")){
            listener.onSuccess();
        }
        else
        {
            listener.onErrorPassword("Wrong password");
        }*/

        mAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            listener.onErrorPassword(task.getException().getMessage().toString());
                        }
                        else{
                            listener.onSuccess();
                        }
                    }
                });

    }
}
