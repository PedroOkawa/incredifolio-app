package com.daltonicchameleon.portfolio.util.factory;

import com.daltonicchameleon.portfolio.util.adapter.RxCallAdapterWrapped;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class RxErrorCallAdapterFactory extends CallAdapter.Factory {

    private ConnectionHelper connectionHelper;
    private RxJavaCallAdapterFactory rxJavaCallAdapterFactory;

    public RxErrorCallAdapterFactory(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
        this.rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapped(rxJavaCallAdapterFactory.get(returnType, annotations, retrofit), connectionHelper, retrofit);
    }
}

