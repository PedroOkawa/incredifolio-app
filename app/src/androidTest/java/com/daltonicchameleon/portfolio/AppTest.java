package com.daltonicchameleon.portfolio;

import com.daltonicchameleon.portfolio.di.DaggerAppTestComponent;
import com.daltonicchameleon.portfolio.di.app.AppModule;
import com.daltonicchameleon.portfolio.di.module.ApiTestModule;

/**
 * portfolio-app
 * Created in 3/28/17 by the following authors:
 * Pedro Okawa
 */
public class AppTest extends App {

    @Override
    protected void initializeComponent() {
        appComponent = DaggerAppTestComponent
                .builder()
                .apiModule(new ApiTestModule())
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}