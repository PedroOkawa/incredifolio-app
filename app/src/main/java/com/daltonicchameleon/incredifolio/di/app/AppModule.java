package com.daltonicchameleon.incredifolio.di.app;


import com.daltonicchameleon.incredifolio.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public App providesApp() {
        return app;
    }

}