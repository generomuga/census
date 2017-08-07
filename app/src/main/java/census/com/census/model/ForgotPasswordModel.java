package census.com.census.model;

public interface ForgotPasswordModel {

    interface OnForgotPasswordListener{
        void onErrorEmail(String message);
        void onSuccess();
    }

    void sendReset(String email);
}
