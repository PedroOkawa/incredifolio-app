package com.daltonicchameleon.portfolio.presenter.splash;

import android.util.Log;

import com.daltonicchameleon.portfolio.ui.splash.SplashView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class SplashPresenterImpl implements SplashPresenter {

    private ApiManager apiManager;
    private SplashView splashview;
    private TextHelper textHelper;

    public SplashPresenterImpl(ApiManager apiManager, SplashView splashview, TextHelper textHelper) {
        this.apiManager = apiManager;
        this.splashview = splashview;
        this.textHelper = textHelper;
    }

    @Override
    public void initialize() {
        apiManager.validateToken(new ApiCallback<Void>(textHelper) {
            @Override
            protected void doOnComplete(Void aVoid) {
                splashview.callMain();
                Log.w("TEST", "COMPLETE");
            }

            @Override
            protected void doOnError(String error) {
                splashview.callLogin();
                Log.w("TEST", "ERROR: " + error);
            }

            @Override
            protected void doOnExpired() {
                splashview.callLogin();
                Log.w("TEST", "EXPIRED");
            }
        });
    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}