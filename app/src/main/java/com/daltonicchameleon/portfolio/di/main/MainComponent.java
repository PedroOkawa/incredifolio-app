package com.daltonicchameleon.portfolio.di.main;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.main.MainPresenter;
import com.daltonicchameleon.portfolio.ui.main.MainActivity;
import com.daltonicchameleon.portfolio.ui.main.MainView;

import dagger.Component;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity fragment);

    MainView providesMainView();

    MainPresenter providesMainPresenter();

}