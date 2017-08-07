package census.com.census;

import census.com.census.model.SignUpModel;
import census.com.census.presenter.SignUpPresenter;
import census.com.census.views.SignUpView;

public class SignUpPresenterImpl implements SignUpPresenter, SignUpModel.OnSignUpListener {

    SignUpView signUpView;
    SignUpModel signUpModelListener;

    public SignUpPresenterImpl(SignUpView signUpView) {
        this.signUpView = signUpView;
        signUpModelListener = new SignUpModelImpl(this);
    }

    @Override
    public void checkEmail(String email,String confirmEmail) {

        if(email.isEmpty()){
            signUpView.setErrorEmail("This field is required!");
            return;
        }
        if(!validateEmail(email)){
            signUpView.setErrorEmail("Invalid email!");
            return;
        }

        if(!email.equals(confirmEmail)){
            signUpView.setErrorEmail("Email is not the same!");
            return;
        }

        //signUpView.showProgress(true);

        signUpModelListener.register(email,"gene123123123");
        //signUpModelListener = new SignUpModelImpl(this);
    }

    @Override
    public boolean checkFname(String fname) {
        if(fname.isEmpty()){
            signUpView.setErrorFname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkMname(String mname) {
        if(mname.isEmpty()){
            signUpView.setErrorMname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean checkLname(String lname) {
        if(lname.isEmpty()){
            signUpView.setErrorLname("This field is required!");
            return false;
        }
        else{
            return true;
        }
    }

    private boolean validateEmail(String email){
        return email.contains("@");
    }

    @Override
    public void onErrorRegister(String message) {
        //signUpView.showProgress(false);
        signUpView.setErrorEmail(message);
    }

    @Override
    public void onSuccess(String message) {
        //signUpView.showProgress(false);
        signUpView.onSuccess(message);
    }
}
