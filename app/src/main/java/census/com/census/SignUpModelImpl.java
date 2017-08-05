package census.com.census;


public class SignUpModelImpl implements SignUpModel{

    OnSignUpListener listener;

    public SignUpModelImpl(OnSignUpListener listener) {
        this.listener = listener;
    }

    @Override
    public void register(String email) {

    }
}
