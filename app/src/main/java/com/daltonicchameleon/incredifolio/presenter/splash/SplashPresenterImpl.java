package com.daltonicchameleon.incredifolio.presenter.splash;

import com.daltonicchameleon.incredifolio.ui.splash.SplashView;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
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