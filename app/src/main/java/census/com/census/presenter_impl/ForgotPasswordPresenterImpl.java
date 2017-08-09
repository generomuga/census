package census.com.census.presenter_impl;

import census.com.census.presenter.ForgotPasswordPresenter;
import census.com.census.view.ForgotPasswordView;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter{
    ForgotPasswordView forgotPasswordView;

    public ForgotPasswordPresenterImpl(ForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
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
    }

    private boolean isEmail(String email){
        return email.contains("@");
    }

}
