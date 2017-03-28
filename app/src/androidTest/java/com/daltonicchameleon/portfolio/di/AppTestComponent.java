package com.daltonicchameleon.portfolio.di;

import com.daltonicchameleon.portfolio.suite.SplashTest;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.app.AppModule;
import com.daltonicchameleon.portfolio.di.base.ApiModule;
import com.daltonicchameleon.portfolio.di.base.UtilsModule;

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
        UtilsModule.class
})
public interface AppTestComponent extends AppComponent {

    void inject(SplashTest splashTest);

}