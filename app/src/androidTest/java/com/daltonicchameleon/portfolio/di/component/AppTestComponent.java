package com.daltonicchameleon.portfolio.di.component;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.app.AppModule;
import com.daltonicchameleon.portfolio.di.base.ApiModule;
import com.daltonicchameleon.portfolio.di.base.UtilsModule;
import com.daltonicchameleon.portfolio.di.module.TestModule;
import com.daltonicchameleon.portfolio.suite.SplashTest;
import com.daltonicchameleon.portfolio.util.TestHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * portfolio-app
 * Created in 3/28/17 by the following authors:
 * Pedro Okawa
 */
@Singleton
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        TestModule.class,
        UtilsModule.class
})
public interface AppTestComponent extends AppComponent {

    void inject(SplashTest splashTest);

    /* UTILS */
    TestHelper providesTestHelper();

}