package census.com.census.view;

public interface SignUpView {

    void setErrorEmail(String message);

    void showProgress(boolean show);

    void onSuccess(String message);

    void setErrorFname(String message);
    void setErrorMname(String message);
    void setErrorLname(String message);
}
