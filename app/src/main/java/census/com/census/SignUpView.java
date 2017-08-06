package census.com.census;

public interface SignUpView {

    void setErrorEmail(String message);

    void showProgress(boolean show);

    void onSuccess(String message);
}
