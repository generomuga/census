package census.com.census;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements SignUpView{

    private EditText mEmail;
    private Button mRegister;
    SignUpPresenter signUpPresenterListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmail = (EditText) findViewById(R.id.userEmail);
        mRegister = (Button) findViewById(R.id.buttonRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
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
}
