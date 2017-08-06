package census.com.census;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements SignUpView{

    private EditText mEmail;
    private Button mRegister;
    private ProgressBar mProgress;
    private View mForm;

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
                attemptRegister();
            }
        });

        mForm = findViewById(R.id.signUpForm);

        mProgress = new ProgressBar(this);

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
    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mForm.setVisibility(show ? View.GONE : View.VISIBLE);
        mForm.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mForm.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgress.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

}
