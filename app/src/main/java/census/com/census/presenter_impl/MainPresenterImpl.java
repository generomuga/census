package census.com.census.presenter_impl;

import census.com.census.model.MainModel;
import census.com.census.model_impl.MainModelImpl;
import census.com.census.presenter.MainPresenter;
import census.com.census.view.MainView;

public class MainPresenterImpl implements MainPresenter {

    MainView mainViewListener;
    MainModel mainModelListener;

    public MainPresenterImpl(MainView mainViewListener) {
        this.mainViewListener = mainViewListener;
        mainModelListener = new MainModelImpl();
    }

    @Override
    public void checkList(String id) {
        if(id.isEmpty()){
            return;
        }

        mainModelListener.deleteData(id);
    }
}
