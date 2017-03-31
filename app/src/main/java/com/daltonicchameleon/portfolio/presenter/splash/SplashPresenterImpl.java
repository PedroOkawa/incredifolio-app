package com.daltonicchameleon.portfolio.presenter.splash;

import android.os.Handler;

import com.daltonicchameleon.portfolio.ui.splash.SplashView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.constants.Constants;
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

        public AuthenticateCallback() {
            super(textHelper);
        }

        @Override
        protected void doOnComplete(Void aVoid) {
            splashview.callMain();
        }

        @Override
        protected void doOnError(String error) {
            splashview.callLogin();
        }

        @Override
        protected void doOnExpired() {
            splashview.callLogin();
        }
    }

}