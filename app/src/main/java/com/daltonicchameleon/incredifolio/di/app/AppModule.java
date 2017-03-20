package com.daltonicchameleon.incredifolio.di.app;


import com.daltonicchameleon.incredifolio.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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