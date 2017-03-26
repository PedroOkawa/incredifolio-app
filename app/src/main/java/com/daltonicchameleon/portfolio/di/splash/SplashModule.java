package com.daltonicchameleon.portfolio.di.splash;

import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.splash.SplashPresenter;
import com.daltonicchameleon.portfolio.presenter.splash.SplashPresenterImpl;
import com.daltonicchameleon.portfolio.ui.splash.SplashView;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

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
    public SplashPresenter providesSplashPresenter(ApiManager apiManager, SplashView splashView, TextHelper textHelper) {
        return new SplashPresenterImpl(apiManager, splashView, textHelper);
    }

}