package census.com.census;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements SignUpView{

    private EditText mEmail;
    private Button mRegister;
    SignUpPresenter signUpPresenterListener;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById(R.id.userEmail);
        mRegister = (Button) findViewById(R.id.buttonRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //attemptRegister();
                register();
            }
        });

        signUpPresenterListener = new SignUpPresenterImpl(this);

    }

    private void attemptRegister(){
        signUpPresenterListener.checkEmail(mEmail.getText().toString().trim());
    }

    @Override
    public void setErrorEmail(String message) {
        mEmail.setError(message);
    }

    @Override
    public void onSuccess() {

    }


    private void register(){

        mAuth.createUserWithEmailAndPassword(mEmail.getText().toString().toString(),"geasdasd").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase","faield");
                }
            }

        });
    }
}
