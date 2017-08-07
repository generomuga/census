package census.com.census.views;

public interface LoginView {
    void setErrorUsername(String message);
    void setErrorPassword(String message);
    void onSuccess();
}
