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

    }

    @Override
    public void onErrorRegister(String message) {

    }

    @Override
    public void onSuccess() {

    }
}
