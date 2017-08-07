package census.com.census;

import android.text.TextUtils;

import census.com.census.model.LoginModel;
import census.com.census.model_impl.LoginModelImpl;
import census.com.census.presenter.LoginPresenter;
import census.com.census.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter,LoginModel.OnLoginListener{

    LoginView loginView;
    LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl(this);
    }

    @Override
    public void checkCredentials(String username, String password) {
        if(TextUtils.isEmpty(username)){
            loginView.setErrorUsername("This is required!");
            return;
        }

        if(!isEmail(username)){
            loginView.setErrorUsername("Invalid email");
            return;
        }

        if(TextUtils.isEmpty(password)){
            loginView.setErrorPassword("This is required");
            return;
        }

        loginModel.login(username,password,this);
    }

    private boolean isEmail(String username){
        return username.contains("@");
    }

    @Override
    public void onSuccess() {
        loginView.onSuccess();
    }

    @Override
    public void onErrorPassword(String message) {
        loginView.setErrorPassword(message);
    }
}
