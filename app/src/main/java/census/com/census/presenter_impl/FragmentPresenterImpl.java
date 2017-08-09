package census.com.census.presenter_impl;

import census.com.census.presenter.FragmentPresenter;
import census.com.census.view.FragmentView;

public class FragmentPresenterImpl implements FragmentPresenter{

    FragmentView fragmentView;

    public FragmentPresenterImpl(FragmentView fragmentView) {
        this.fragmentView = fragmentView;
    }

    @Override
    public void checkFName(String fname) {
        if(fname.isEmpty()){
            fragmentView.setErrorFname("This field is required!");
            return;
        }
    }

}
