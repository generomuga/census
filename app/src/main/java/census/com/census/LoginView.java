package census.com.census;



public interface LoginView {
    void setErrorUsername(String message);
    void setErrorPassword(String message);
    void onSuccess();
}
