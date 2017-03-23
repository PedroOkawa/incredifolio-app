package com.daltonicchameleon.incredifolio.di.main;

import com.daltonicchameleon.incredifolio.di.scope.Activity;
import com.daltonicchameleon.incredifolio.presenter.main.MainPresenter;
import com.daltonicchameleon.incredifolio.presenter.main.MainPresenterImpl;
import com.daltonicchameleon.incredifolio.ui.main.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Activity
    @Provides
    public MainView providesMainView() {
        return view;
    }

    @Activity
    @Provides
    public MainPresenter providesMainPresenter(MainView mainView) {
        return new MainPresenterImpl(mainView);
    }

}