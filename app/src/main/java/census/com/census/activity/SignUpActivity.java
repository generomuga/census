package census.com.census.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import census.com.census.R;
import census.com.census.presenter_impl.SignUpPresenterImpl;
import census.com.census.presenter.SignUpPresenter;
import census.com.census.view.SignUpView;

public class SignUpActivity extends AppCompatActivity implements SignUpView {


    private EditText mFname;
    private EditText mMname;
    private EditText mLname;
    private EditText mEmail;
    private EditText mEmailConfirm;

    private Button mRegister;
    private ProgressDialog mProgress;
    private View mForm;

    SignUpPresenter signUpPresenterListener;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        mFname = (EditText) findViewById(R.id.editTextFname);
        mMname = (EditText) findViewById(R.id.editTextMname);
        mLname = (EditText) findViewById(R.id.editTextLname);

        mEmail = (EditText) findViewById(R.id.userEmail);
        mEmailConfirm = (EditText) findViewById(R.id.userEmailConfirm);

        mRegister = (Button) findViewById(R.id.buttonRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });

        mForm = findViewById(R.id.signUpForm);

        mProgress = new ProgressDialog(this);

        signUpPresenterListener = new SignUpPresenterImpl(this);

    }

    private void attemptRegister(){
        mProgress.setMessage("Registering account...");
        mProgress.setCancelable(false);
        mProgress.show();

        if(signUpPresenterListener.checkFname(mFname.getText().toString())) {
            if(signUpPresenterListener.checkMname(mMname.getText().toString())) {
                if(signUpPresenterListener.checkLname(mLname.getText().toString())) {
                    signUpPresenterListener.checkEmail(mEmail.getText().toString().trim(), mEmailConfirm.getText().toString());
                }
            }
        }

    }

    @Override
    public void setErrorEmail(String message) {
        mProgress.dismiss();
        mEmail.setError(message);
    }

    @Override
    public void showProgress(final boolean show) {
        //int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        /*mForm.setVisibility(show ? View.GONE : View.VISIBLE);
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
        });*/

    }

    @Override
    public void onSuccess(String message) {
        mProgress.dismiss();
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setErrorFname(String message) {
        mProgress.dismiss();
        mFname.setError(message);
    }

    @Override
    public void setErrorMname(String message) {
        mProgress.dismiss();
        mMname.setError(message);
    }

    @Override
    public void setErrorLname(String message) {
        mProgress.dismiss();
        mLname.setError(message);
    }

}
