package com.daltonicchameleon.portfolio.di.base;

import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.factory.RxErrorCallAdapterFactory;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
@Module
public class ApiModule {

    /**
     * Returns an instance of RxErrorCallAdapterFactory to enable use observables instead of callbacks
     *
     * @param connectionHelper
     * @return RxErrorCallAdapterFactory
     */
    @Singleton
    @Provides
    public RxErrorCallAdapterFactory providesRxErrorCallAdapterFactory(ConnectionHelper connectionHelper) {
        return new RxErrorCallAdapterFactory(connectionHelper);
    }

    /**
     * Return http log interceptor
     *
     * @return Http Logging Interceptor
     */
    @Singleton
    @Provides
    public HttpLoggingInterceptor providesHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    /**
     * Generates custom http client with timeout configuration
     *
     * @param httpLoggingInterceptor
     * @return OkHttpClient
     */
    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .connectTimeout(Constants.API_MODULE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.API_MODULE_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.API_MODULE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .followRedirects(false)
                .build();
    }

    /**
     * Generates custom Gson object with Date converter (yyyy-MM-dd'T'HH:mm:ssZ) implemented
     *
     * @return Gson
     */
    @Singleton
    @Provides
    public Gson providesGson() {
        return new GsonBuilder()
                .setDateFormat(Constants.API_MODULE_DATE_FORMAT_COMPLETE)
                .create();
    }

    /**
     * Returns retrofit instance with all previous configurations
     *
     * @param okHttpClient
     * @param rxErrorCallAdapterFactory
     * @param gson
     * @return Retrofit
     */
    @Singleton
    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient, RxErrorCallAdapterFactory rxErrorCallAdapterFactory, Gson gson) {
        return new Retrofit
                .Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(rxErrorCallAdapterFactory)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.API_MODULE_URL)
                .build();
    }

    /**
     * Returns an instance of the api service to execute api calls
     *
     * @param retrofit
     * @return apiService
     */
    @Singleton
    @Provides
    public ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
