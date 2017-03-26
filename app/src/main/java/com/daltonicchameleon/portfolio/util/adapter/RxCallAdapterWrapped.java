package com.daltonicchameleon.portfolio.util.adapter;

import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class RxCallAdapterWrapped implements CallAdapter<Call<?>, Observable<?>> {

    private CallAdapter<?, ?> wrapped;
    private ConnectionHelper connectionHelper;
    private Retrofit retrofit;

    public RxCallAdapterWrapped(CallAdapter<?, ?> wrapped, ConnectionHelper connectionHelper, Retrofit retrofit) {
        this.wrapped = wrapped;
        this.connectionHelper = connectionHelper;
        this.retrofit = retrofit;
    }

    @Override
    public Type responseType() {
        return wrapped.responseType();
    }

    @Override
    public Observable<?> adapt(Call call) {
        return ((Observable) wrapped.adapt(call)).onErrorResumeNext(new Func1<Throwable, Observable>() {
            @Override
            public Observable call(Throwable throwable) {
                return Observable.error(connectionHelper.asRetrofitException(throwable, retrofit));
            }
        });
    }
}

