package com.daltonicchameleon.portfolio.di.fullscreen;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.fullscreen.FullScreenPresenter;
import com.daltonicchameleon.portfolio.ui.fullscreen.FullScreenActivity;
import com.daltonicchameleon.portfolio.ui.fullscreen.FullScreenView;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = FullScreenModule.class)
public interface FullScreenComponent {

    void inject(FullScreenActivity fragment);

    FullScreenView providesFullScreenView();

    FullScreenPresenter providesFullScreenPresenter();

}