package census.com.census;

import android.text.TextUtils;

public class LoginPresenterImpl implements LoginPresenter {

    LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void checkCredentials(String username, String password) {
        if(TextUtils.isEmpty(username)){
            loginView.setErrorUsername("This is required!");
            return;
        }

        if(TextUtils.isEmpty(password)){
            loginView.setErrorPassword("This is required");
            return;
        }

        loginView.onSuccess();
    }
}
