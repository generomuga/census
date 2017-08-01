package census.com.census;

import android.util.Log;

public class FamilyIdentificationPresenterImpl implements FamilyIdentificationPresenter{

    FamilyIdentificationView familyIdentificationView;

    public FamilyIdentificationPresenterImpl(FamilyIdentificationView familyIdentificationView) {
        this.familyIdentificationView = familyIdentificationView;
    }

    @Override
    public void setParams(String fname) {
        Log.i("sampe",fname);
    }
}
