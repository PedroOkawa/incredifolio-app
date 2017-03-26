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
                Log.d("TEST", "TOKEN IS VALID!");
            }

            @Override
            protected void doOnError(String error) {
                Log.d("TEST", "ERROR: " + error);
            }

            @Override
            protected void doOnExpired() {

            }

//            @Override
//            public void doOnSubscribe(Disposable disposable) {
//                splashview.addRequestDisposable(disposable);
//            }
        });

//        apiManager.authenticateUser("PedroOkawa", "Test123", new ApiCallback<User>(textHelper) {
//            @Override
//            protected void doOnComplete(User user) {
//
//            }
//
//            @Override
//            protected void doOnError(String error) {
//
//            }
//
//            @Override
//            protected void doOnExpired() {
//
//            }
//
//            @Override
//            public void doOnSubscribe(Disposable disposable) {
//
//            }
//        });
    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}