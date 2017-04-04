package com.daltonicchameleon.portfolio.di.app;

import com.daltonicchameleon.portfolio.App;
import com.daltonicchameleon.portfolio.di.base.ApiModule;
import com.daltonicchameleon.portfolio.di.base.UtilsModule;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.factory.RxErrorCallAdapterFactory;
import com.daltonicchameleon.portfolio.util.helper.AccountHelper;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.daltonicchameleon.portfolio.util.helper.FileHelper;
import com.daltonicchameleon.portfolio.util.helper.FormHelper;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;
import com.daltonicchameleon.portfolio.util.manager.CallManager;
import com.daltonicchameleon.portfolio.util.manager.FeedbackManager;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
@Singleton
@Component(modules = {
        ApiModule.class,
        AppModule.class,
        UtilsModule.class
})
public interface AppComponent {

    void inject(App app);

    /* API */
    RxErrorCallAdapterFactory providesRxErrorCallAdapterFactory();
    HttpLoggingInterceptor providesHttpLoggingInterceptor();
    OkHttpClient providesOkHttpClient();
    Gson providesGson();
    Retrofit providesRetrofit();
    ApiService providesApiService();

    /* APP */
    App providesApp();

    /* UTILS */
    AccountHelper providesAccountHelper();
    ApiManager providesApiManager();
    CallManager providesCallManager();
    ConnectionHelper providesConnectionHelper();
    FeedbackManager providesFeedbackManager();
    FileHelper providesFileHelper();
    FormHelper providesFormHelper();
    TextHelper providesTextHelper();

}
