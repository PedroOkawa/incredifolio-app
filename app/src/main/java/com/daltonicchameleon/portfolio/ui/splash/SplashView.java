package com.daltonicchameleon.portfolio.ui.splash;

import com.daltonicchameleon.portfolio.base.BaseView;
import com.daltonicchameleon.portfolio.databinding.ActivitySplashBinding;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public interface SplashView extends BaseView<SplashActivity, ActivitySplashBinding> {

    void callLogin();
    void callMain();

}