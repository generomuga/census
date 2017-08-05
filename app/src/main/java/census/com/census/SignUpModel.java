package census.com.census;

public interface SignUpModel {

    interface OnSignUpListener{
        void onErrorRegister(String message);
        void onSuccess();
    }

    void register(String email,String password);

}
