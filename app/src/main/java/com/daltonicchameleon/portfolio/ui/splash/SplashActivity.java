package com.daltonicchameleon.portfolio.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseActivity;
import com.daltonicchameleon.portfolio.databinding.ActivitySplashBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.splash.DaggerSplashComponent;
import com.daltonicchameleon.portfolio.di.splash.SplashModule;
import com.daltonicchameleon.portfolio.presenter.splash.SplashPresenter;
import com.daltonicchameleon.portfolio.ui.fullscreen.FullScreenActivity;
import com.daltonicchameleon.portfolio.ui.login.LoginFragment;
import com.daltonicchameleon.portfolio.ui.main.MainFragment;
import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarActivity;
import com.daltonicchameleon.portfolio.util.manager.CallManager;

import javax.inject.Inject;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> implements SplashView {

    @Inject
    CallManager callManager;
    @Inject
    SplashPresenter splashPresenter;

    @Override
    public SplashActivity getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initializesComponents(AppComponent appComponent) {
        DaggerSplashComponent
                .builder()
                .appComponent(appComponent)
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(@Nullable Bundle savedInstanceState) {
        splashPresenter.initialize();
    }

    @Override
    public void callLogin() {
        callManager.open(this, FullScreenActivity.class, LoginFragment.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void callMain() {
        callManager.open(this, ToolbarActivity.class, MainFragment.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}