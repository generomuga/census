package census.com.census;

public class LoginModelImpl implements LoginModel{


    OnLoginListener listener;

    @Override
    public void login(String username, String password, OnLoginListener listener) {
        if(username.equals("gene") && password.equals("gene")){
            listener.onSuccess();
        }
        else
        {
            listener.onErrorPassword("Wrong password");
        }
    }
}
