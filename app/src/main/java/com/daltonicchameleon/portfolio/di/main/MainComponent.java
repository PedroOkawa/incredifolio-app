package com.daltonicchameleon.portfolio.di.main;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.main.MainPresenter;
import com.daltonicchameleon.portfolio.ui.main.MainFragment;
import com.daltonicchameleon.portfolio.ui.main.MainView;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    void inject(MainFragment fragment);

    MainView providesMainView();

    MainPresenter providesMainPresenter();

}