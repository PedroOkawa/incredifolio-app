package com.daltonicchameleon.incredifolio.di.splash;

import com.daltonicchameleon.incredifolio.di.scope.Activity;
import com.daltonicchameleon.incredifolio.presenter.splash.SplashPresenter;
import com.daltonicchameleon.incredifolio.presenter.splash.SplashPresenterImpl;
import com.daltonicchameleon.incredifolio.ui.splash.SplashView;

import dagger.Module;
import dagger.Provides;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
@Module
public class SplashModule {

    private SplashView view;

    public SplashModule(SplashView view) {
        this.view = view;
    }

    @Activity
    @Provides
    public SplashView providesSplashView() {
        return view;
    }

    @Activity
    @Provides
    public SplashPresenter providesSplashPresenter(SplashView splashView) {
        return new SplashPresenterImpl(splashView);
    }

}