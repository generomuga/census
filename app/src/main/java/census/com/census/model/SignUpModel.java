package census.com.census.model;

public interface SignUpModel {

    interface OnSignUpListener{
        void onErrorRegister(String message);
        void onSuccess(String message);
    }

    void register(String email,String password);

}
