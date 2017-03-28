package com.daltonicchameleon.portfolio.di.splash;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.splash.SplashPresenter;
import com.daltonicchameleon.portfolio.ui.splash.SplashActivity;
import com.daltonicchameleon.portfolio.ui.splash.SplashView;

import dagger.Component;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
@Activity
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity fragment);

    SplashView providesSplashView();
    SplashPresenter providesSplashPresenter();

}