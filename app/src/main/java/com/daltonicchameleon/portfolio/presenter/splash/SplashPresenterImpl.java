package com.daltonicchameleon.portfolio.presenter.splash;

import android.os.Handler;

import com.daltonicchameleon.portfolio.ui.splash.SplashView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class SplashPresenterImpl implements SplashPresenter {

    private ApiManager apiManager;
    private SplashView splashview;

    public SplashPresenterImpl(ApiManager apiManager, SplashView splashview) {
        this.apiManager = apiManager;
        this.splashview = splashview;
    }

    @Override
    public void initialize() {
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                apiManager.validateToken(new AuthenticateCallback());
            }
        }, Constants.DELAY_MEDIUM);
    }

    @Override
    public void dispose() {

    }

    private class AuthenticateCallback extends ApiCallback<Void> {

        @Override
        protected void doOnComplete(Void aVoid) {
            splashview.callMain();
        }

        @Override
        protected void doOnError(String error) {
            splashview.callLogin();
        }
    }

}