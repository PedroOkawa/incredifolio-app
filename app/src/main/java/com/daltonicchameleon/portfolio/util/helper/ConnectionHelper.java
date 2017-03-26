package com.daltonicchameleon.portfolio.util.helper;

import android.support.annotation.NonNull;

import com.daltonicchameleon.portfolio.util.api.RetrofitException;

import java.io.IOException;

import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class ConnectionHelper {

    public Exception asRetrofitException(@NonNull Throwable throwable, @NonNull Retrofit retrofit) {
        if(throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            Response response = httpException.response();
            return RetrofitException.httpError(response.raw().request().url().toString(), response, retrofit);
        }

        if(throwable instanceof IOException) {
            return RetrofitException.networkError((IOException) throwable);
        }

        if(throwable instanceof RuntimeException) {
            return RetrofitException.runtimeError((RuntimeException) throwable);
        }

        return RetrofitException.unexpectedError(throwable);
    }
}
