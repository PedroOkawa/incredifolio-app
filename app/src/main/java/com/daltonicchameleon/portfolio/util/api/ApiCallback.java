package com.daltonicchameleon.portfolio.util.api;

import java.io.IOException;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public abstract class ApiCallback<T> {

    protected abstract void doOnComplete(T t);
    protected abstract void doOnError(String error);

    public void onComplete(T t) {
        doOnComplete(t);
    }

    public void onError(Throwable throwable) {
        RetrofitException retrofitException = (RetrofitException) throwable;

        if(retrofitException.getType() == RetrofitException.Type.UNEXPECTED) {
            doOnError(retrofitException.getMessage());
            return;
        }

        if(retrofitException.getType() != RetrofitException.Type.HTTP) {
            doOnError(throwable.getMessage());
            return;
        }

        try {
            ApiError apiError = convertApiError(retrofitException);
            doOnError(apiError.getMessage());
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        doOnError(throwable.getMessage());
    }

    /**
     * Converts throwable to an Api Error instance
     *
     * @param retrofitException
     * @return ApiError
     */
    private ApiError convertApiError(RetrofitException retrofitException) throws IOException {
        return retrofitException.getErrorBodyAs(ApiError.class);
    }
}

