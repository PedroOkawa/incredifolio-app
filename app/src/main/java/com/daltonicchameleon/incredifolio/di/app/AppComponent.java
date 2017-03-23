package com.daltonicchameleon.incredifolio.di.app;

import com.daltonicchameleon.incredifolio.App;
import com.daltonicchameleon.incredifolio.di.app.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * incredifolio-app
 * Created in 20/03/17 by the following authors:
 * Pedro Okawa
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(App app);

    App providesApp();

}
