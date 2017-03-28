package com.daltonicchameleon.portfolio;

import android.app.Application;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.app.AppModule;
import com.daltonicchameleon.portfolio.di.app.DaggerAppComponent;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class App extends Application {

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        initializeConnectivityMonitor();
    }

    /**
     * Builds Constants component to use in each screen
     */
    protected void initializeComponent() {
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