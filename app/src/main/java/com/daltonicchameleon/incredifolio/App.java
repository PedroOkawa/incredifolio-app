package com.daltonicchameleon.incredifolio;

import android.app.Application;

import com.daltonicchameleon.incredifolio.di.app.AppComponent;
import com.daltonicchameleon.incredifolio.di.app.AppModule;
import com.daltonicchameleon.incredifolio.di.app.DaggerAppComponent;

import javax.inject.Inject;

/**
 * incredifolio-app
 * Created in 20/03/17 by the following authors:
 * Pedro Okawa
 */
public class App extends Application {

    @Inject
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        initializeConnectivityMonitor();
    }

    /**
     * Builds Constants component to use in each screen
     */
    private void initializeComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    /**
     * Returns the Constants Component built on App creation
     *
     * @return appComponent
     */
    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * Registers connectivity monitor for API 21+
     */
    protected void initializeConnectivityMonitor() {
        //connectionHelper.registerConnectivityMonitor();
    }

}