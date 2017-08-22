package census.com.census.presenter_impl;



import census.com.census.presenter.MainPresenter;
import census.com.census.view.MainView;

public class MainPresenterImpl implements MainPresenter {

    MainView mainViewListener;

    public MainPresenterImpl(MainView mainViewListener) {
        this.mainViewListener = mainViewListener;
    }

    @Override
    public void checkList(String id) {
        if(id.isEmpty()){
            return;
        }

        

    }
}
