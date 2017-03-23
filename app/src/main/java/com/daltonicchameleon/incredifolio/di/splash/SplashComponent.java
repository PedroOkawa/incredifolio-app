package com.daltonicchameleon.incredifolio.di.splash;

import com.daltonicchameleon.incredifolio.di.app.AppComponent;
import com.daltonicchameleon.incredifolio.di.scope.Activity;
import com.daltonicchameleon.incredifolio.presenter.splash.SplashPresenter;
import com.daltonicchameleon.incredifolio.ui.splash.SplashActivity;
import com.daltonicchameleon.incredifolio.ui.splash.SplashView;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity fragment);

    SplashView providesSplashView();

    SplashPresenter providesSplashPresenter();

}