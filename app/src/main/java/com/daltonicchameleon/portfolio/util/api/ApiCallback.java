package com.daltonicchameleon.portfolio.util.api;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;

import java.io.IOException;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public abstract class ApiCallback<T> {

    private TextHelper textHelper;

    public ApiCallback(TextHelper textHelper) {
        this.textHelper = textHelper;
    }

    protected abstract void doOnComplete(T t);
    protected abstract void doOnError(String error);
    protected abstract void doOnExpired();

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
            doOnError(textHelper.convertString(R.string.error_network_general));
            return;
        }

        try {
            ApiError apiError = convertApiError(retrofitException);

            if(apiError.getCode() == Constants.API_STATUS_CODE_EXPIRED) {
                doOnExpired();
                return;
            }

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

