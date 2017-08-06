package census.com.census;

public class SignUpPresenterImpl implements SignUpPresenter, SignUpModel.OnSignUpListener {

    SignUpView signUpView;
    SignUpModel signUpModelListener;

    public SignUpPresenterImpl(SignUpView signUpView) {
        this.signUpView = signUpView;
        signUpModelListener = new SignUpModelImpl(this);
    }

    @Override
    public void checkEmail(String email) {

        if(email.isEmpty()){
            signUpView.setErrorEmail("This field is required!");
            return;
        }
        if(!validateEmail(email)){
            signUpView.setErrorEmail("Invalid email!");
            return;
        }

        //signUpView.showProgress(true);

        signUpModelListener.register(email,"gene123123123");
        signUpModelListener = new SignUpModelImpl(this);
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
