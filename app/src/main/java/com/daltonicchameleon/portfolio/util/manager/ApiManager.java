package com.daltonicchameleon.portfolio.util.manager;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.model.Portfolio;
import com.daltonicchameleon.portfolio.model.User;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.api.RetrofitException;
import com.daltonicchameleon.portfolio.util.api.response.UserResponse;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.AccountHelper;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class ApiManager {

    private AccountHelper accountHelper;
    private ApiService apiService;
    private TextHelper textHelper;

    public ApiManager(AccountHelper accountHelper, ApiService apiService, TextHelper textHelper) {
        this.accountHelper = accountHelper;
        this.apiService = apiService;
        this.textHelper = textHelper;
    }

    /** PORTFOLIO **/

    public void listPortfolios(final ApiCallback<List<Portfolio>> apiCallback, long startDate) {
        if(hasTokenStored(apiCallback)) {
            Map<String, String> options = new HashMap<>();

            options.put(Constants.API_SERVICE_QUERY_START_DATE, String.valueOf(startDate));
            options.put(Constants.API_SERVICE_QUERY_PER_PAGE, Constants.API_MANAGER_PORTFOLIOS_PER_PAGE);

            apiService
                    .listPortfolios(accountHelper.getCurrentToken(), options)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Portfolio>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {
                            apiCallback.onError(throwable);
                        }

                        @Override
                        public void onNext(List<Portfolio> portfolios) {
                            apiCallback.onComplete(portfolios);
                        }

                    });
        }
    }

    /** SESSION **/

    /**
     * Validates if the user's token is valid
     *
     * @param apiCallback
     */
    public void validateToken(final ApiCallback<Void> apiCallback) {
        if(hasTokenStored(apiCallback)) {
            apiService
                    .validate(accountHelper.getCurrentToken())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Void>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable throwable) {
                            apiCallback.onError(throwable);
                        }

                        @Override
                        public void onNext(Void aVoid) {
                            apiCallback.onComplete(aVoid);
                        }
                    });
        }
    }

    /** USER **/

    /**
     * Authenticates user by username and password
     *
     * @param username
     * @param password
     * @param apiCallback
     */
    public void authenticateUser(final String username, final String password, final ApiCallback<User> apiCallback) {
        apiService
                .authenticateUser(username, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        apiCallback.onError(throwable);
                    }

                    @Override
                    public void onNext(UserResponse userResponse) {
                        accountHelper.addAccount(username, password, userResponse.getToken());
                        apiCallback.onComplete(userResponse.getUser());
                    }
                });
    }

    public void registerUser(final String username, final String password, final ApiCallback<User> apiCallback) {
        apiService
                .registerUser(username, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        apiCallback.onError(throwable);
                    }

                    @Override
                    public void onNext(UserResponse userResponse) {
                        accountHelper.addAccount(username, password, userResponse.getToken());
                        apiCallback.onComplete(userResponse.getUser());
                    }
                });
    }

    /**
     * Validates if the account and token are valid
     *
     * @param apiCallback
     * @return true if there's a token associated and false otherwise
     */
    private boolean hasTokenStored(ApiCallback apiCallback) {
        if(!accountHelper.hasToken()) {
            RetrofitException retrofitException = RetrofitException
                    .unexpectedError(
                            new NullPointerException(textHelper.convertString(R.string.error_account_not_added)));
            apiCallback.onError(retrofitException);
            return false;
        }
        return true;
    }

}
