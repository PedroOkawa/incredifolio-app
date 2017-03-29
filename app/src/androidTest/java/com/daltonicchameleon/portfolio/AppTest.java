package com.daltonicchameleon.portfolio;

import com.daltonicchameleon.portfolio.di.app.AppModule;
import com.daltonicchameleon.portfolio.di.component.DaggerAppTestComponent;
import com.daltonicchameleon.portfolio.di.module.ApiTestModule;
import com.daltonicchameleon.portfolio.di.module.UtilsTestModule;

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
                .utilsModule(new UtilsTestModule())
                .build();
        appComponent.inject(this);
    }
}