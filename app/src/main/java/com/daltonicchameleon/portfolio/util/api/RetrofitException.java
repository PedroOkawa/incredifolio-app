package com.daltonicchameleon.portfolio.util.api;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class RetrofitException extends Exception {

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, null, url, response, Type.HTTP, retrofit);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), exception, null, null, Type.NETWORK, null);
    }

    public static RetrofitException runtimeError(RuntimeException exception) {
        return new RetrofitException(exception.getMessage(), exception, null, null, Type.RUNTIME, null);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), exception, null, null, Type.UNEXPECTED, null);
    }

    private String url;
    private Response response;
    private Type type;
    private Retrofit retrofit;

    public enum Type {
        NETWORK,
        RUNTIME,
        HTTP,
        UNEXPECTED
    }

    public RetrofitException(String message, Throwable throwable, String url, Response response, Type type, Retrofit retrofit) {
        super(message, throwable);
        this.url = url;
        this.response = response;
        this.type = type;
        this.retrofit = retrofit;
    }

    public String getUrl() {
        return url;
    }

    public Response getResponse() {
        return response;
    }

    public Type getType() {
        return type;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public <T> T getErrorBodyAs(Class<T> type) throws IOException {
        if(response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        return converter.convert(response.errorBody());
    }
}