package census.com.census;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import census.com.census.presenter.LoginPresenter;
import census.com.census.views.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenterListener;
    private EditText mEmail;
    private EditText mPassword;
    private Button btnSignIn;
    private Button mSignup;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.userEmail);
        mPassword = (EditText) findViewById(R.id.userPassword);

        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });

        mSignup = (Button) findViewById(R.id.buttonRegister);
        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });


        mProgress = new ProgressDialog(this);

        loginPresenterListener = new LoginPresenterImpl(this);
    }

    private void attemptLogin(){
        mProgress.setMessage("Logging in...");
        mProgress.setCancelable(false);
        mProgress.show();
        loginPresenterListener.checkCredentials(mEmail.getText().toString().trim(),mPassword.getText().toString().trim());
    }

    @Override
    public void setErrorUsername(String message) {
        mProgress.dismiss();
        mEmail.setError(message);
    }

    @Override
    public void setErrorPassword(String message) {
        mProgress.dismiss();
        mPassword.setError(message);
    }

    @Override
    public void onSuccess() {
        mProgress.dismiss();
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        //Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
    }
}
