package com.daltonicchameleon.portfolio.di.main;

import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.main.MainPresenter;
import com.daltonicchameleon.portfolio.presenter.main.MainPresenterImpl;
import com.daltonicchameleon.portfolio.ui.main.MainView;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Fragment
    @Provides
    public MainView providesMainView() {
        return view;
    }

    @Fragment
    @Provides
    public MainPresenter providesMainPresenter(ApiManager apiManager, MainView mainView) {
        return new MainPresenterImpl(apiManager, mainView);
    }

}