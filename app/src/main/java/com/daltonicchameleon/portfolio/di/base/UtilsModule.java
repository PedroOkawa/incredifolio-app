package com.daltonicchameleon.portfolio.di.base;

import com.daltonicchameleon.portfolio.App;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.helper.AccountHelper;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.daltonicchameleon.portfolio.util.helper.FileHelper;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;
import com.daltonicchameleon.portfolio.util.manager.CallManager;
import com.daltonicchameleon.portfolio.util.manager.FeedbackManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
@Module
public class UtilsModule {

    /**
     * Generates an account helper that handles and manages application accounts
     *
     * @param app
     * @return accountHelper
     */
    @Singleton
    @Provides
    public AccountHelper providesAccountHelper(App app) {
        return new AccountHelper(app);
    }

    /**
     * Generates an instance of the ApiManager, class that will manage api calls
     *
     * @param accountHelper
     * @param apiService
     * @param textHelper
     * @return ApiManager
     */
    @Singleton
    @Provides
    public ApiManager providesApiManager(AccountHelper accountHelper, ApiService apiService, TextHelper textHelper) {
        return new ApiManager(accountHelper, apiService, textHelper);
    }

    /**
     * Provides manager that will handle all activities, dialogs and fragments calls
     *
     * @param app
     * @return CallManager
     */
    @Singleton
    @Provides
    public CallManager providesCallManager(App app) {
        return new CallManager(app);
    }

    /**
     * Provides an instance of a ConnectionHelper that checks the internet connection
     *
     * @return connectionHelper
     */
    @Singleton
    @Provides
    ConnectionHelper providesConnectionHelper() {
        return new ConnectionHelper();
    }

    /**
     * Provides an instance of a FeedbackManager that allows user to call dialogs, toast and
     * snackbars from any part of the application
     *
     * @param app
     * @return feedbackManager
     */
    @Singleton
    @Provides
    public FeedbackManager providesFeedbackManager(App app) {
        return new FeedbackManager(app);
    }

    /**
     * Provides an instance of file helper that allows to load files from assets
     *
     * @param app
     * @return fileHelper
     */
    @Singleton
    @Provides
    public FileHelper providesFileHelper(App app) {
        return new FileHelper(app);
    }

    /**
     * Provides an instance of a TextHelper that can convert any string id into string text
     *
     * @param app
     * @return textHelper
     */
    @Singleton
    @Provides
    public TextHelper providesTextHelper(App app) {
        return new TextHelper(app);
    }

}
