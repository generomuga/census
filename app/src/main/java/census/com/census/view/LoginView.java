package census.com.census.view;

public interface LoginView {
    void setErrorUsername(String message);
    void setErrorPassword(String message);
    void onSuccess();
}
