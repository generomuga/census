package census.com.census.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import census.com.census.R;
import census.com.census.presenter.ForgotPasswordPresenter;
import census.com.census.presenter_impl.ForgotPasswordPresenterImpl;
import census.com.census.view.ForgotPasswordView;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordView{

    private EditText mEmail;
    private Button mReset;
    private ForgotPasswordPresenter forgotPasswordPresenterListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEmail = (EditText) findViewById(R.id.editTextEmail);

        mReset = (Button) findViewById(R.id.buttonReset);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptReset();
            }
        });

        forgotPasswordPresenterListener = new ForgotPasswordPresenterImpl(this);
    }

    public void attemptReset(){
        forgotPasswordPresenterListener.checkEmail(mEmail.getText().toString().trim());
    }

    @Override
    public void setErrorEmail(String message) {
        mEmail.setError(message);
    }

    @Override
    public void onSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
