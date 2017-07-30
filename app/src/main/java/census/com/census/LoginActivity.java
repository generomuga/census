package census.com.census;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LoginPresenter loginPresenterListener;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextEmail = (EditText) findViewById(R.id.userEmail);
        editTextPassword = (EditText) findViewById(R.id.userPassword);

        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        loginPresenterListener = new LoginPresenterImpl(this);

    }

    private void attemptLogin(){
        loginPresenterListener.checkCredentials(editTextEmail.getText().toString().trim(),editTextPassword.getText().toString().trim());
    }

    @Override
    public void setErrorUsername(String message) {
        editTextEmail.setError(message);
    }

    @Override
    public void setErrorPassword(String message) {
        editTextPassword.setError(message);
    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }
}
