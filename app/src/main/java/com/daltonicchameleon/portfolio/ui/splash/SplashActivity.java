package com.daltonicchameleon.portfolio.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseActivity;
import com.daltonicchameleon.portfolio.databinding.ActivitySplashBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.splash.SplashModule;
import com.daltonicchameleon.portfolio.di.splash.DaggerSplashComponent;
import com.daltonicchameleon.portfolio.presenter.splash.SplashPresenter;

import javax.inject.Inject;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding> implements SplashView {

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

}