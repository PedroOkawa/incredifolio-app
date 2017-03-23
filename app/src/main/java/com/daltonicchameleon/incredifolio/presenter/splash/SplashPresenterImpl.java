package com.daltonicchameleon.incredifolio.presenter.splash;

import com.daltonicchameleon.incredifolio.ui.splash.SplashView;

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView splashview;

    public SplashPresenterImpl(SplashView splashview) {
        this.splashview = splashview;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}