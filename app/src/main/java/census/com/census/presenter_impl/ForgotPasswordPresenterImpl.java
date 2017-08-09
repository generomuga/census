package census.com.census.presenter_impl;

import android.widget.Toast;

import census.com.census.model.ForgotPasswordModel;
import census.com.census.model_impl.ForgotPasswordModelImpl;
import census.com.census.presenter.ForgotPasswordPresenter;
import census.com.census.view.ForgotPasswordView;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter, ForgotPasswordModel.OnForgotPasswordListener{
    ForgotPasswordView forgotPasswordView;
    ForgotPasswordModel forgotPasswordModelListener;

    public ForgotPasswordPresenterImpl(ForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
        forgotPasswordModelListener = new ForgotPasswordModelImpl(this);
    }

    @Override
    public void checkEmail(String email) {
        if(email.isEmpty()){
            forgotPasswordView.setErrorEmail("This field is required!");
            return;
        }

        if(!isEmail(email)){
            forgotPasswordView.setErrorEmail("Invalid email!");
            return;
        }

        forgotPasswordModelListener.sendReset(email);
    }

    private boolean isEmail(String email){
        return email.contains("@");
    }


    @Override
    public void onErrorEmail(String message) {
        forgotPasswordView.setErrorEmail(message);
    }

    @Override
    public void onSuccess() {
        forgotPasswordView.onSuccess("Please check your email!");
    }
}
