package census.com.census;

public interface LoginModel {

    interface OnLoginListener{
        void onSuccess();
        void onErrorPassword(String message);
    }

    void login(String username,String password,OnLoginListener listener);
}
