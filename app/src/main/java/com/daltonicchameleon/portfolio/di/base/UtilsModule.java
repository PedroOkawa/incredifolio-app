package com.daltonicchameleon.portfolio.di.base;

import com.daltonicchameleon.portfolio.App;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;
import com.daltonicchameleon.portfolio.util.manager.FeedbackManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 3/24/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
 */
@Module
public class UtilsModule {

    /**
     * Generates an instance of the ApiManager, class that will manage api calls
     *
     * @param apiService
     * @return ApiManager
     */
    @Singleton
    @Provides
    public ApiManager providesApiManager(ApiService apiService) {
        return new ApiManager(apiService);
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
