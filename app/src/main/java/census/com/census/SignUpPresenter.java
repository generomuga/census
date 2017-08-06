package census.com.census;

public interface SignUpPresenter {

    void checkEmail(String email,String confirmEmail);
    boolean checkFname(String fname);
    boolean checkMname(String mname);
    boolean checkLname(String lname);
}
