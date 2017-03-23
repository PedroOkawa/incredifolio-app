package com.daltonicchameleon.incredifolio.di.main;

import com.daltonicchameleon.incredifolio.di.app.AppComponent;
import com.daltonicchameleon.incredifolio.di.scope.Activity;
import com.daltonicchameleon.incredifolio.presenter.main.MainPresenter;
import com.daltonicchameleon.incredifolio.ui.main.MainActivity;
import com.daltonicchameleon.incredifolio.ui.main.MainView;

import dagger.Component;

/**
 * incredifolio-app
 * Created in 20/03/17 by the following authors:
 * Pedro Okawa
 */
@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity fragment);

    MainView providesMainView();

    MainPresenter providesMainPresenter();

}