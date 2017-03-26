package com.daltonicchameleon.portfolio.util.manager;

import android.util.Log;

import com.daltonicchameleon.portfolio.model.User;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.api.response.UserResponse;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 3/24/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
 */
public class ApiManager {

    private ApiService apiService;

    public ApiManager(ApiService apiService) {
        this.apiService = apiService;
    }

    /** SESSION **/

    public void validateToken(final ApiCallback<Void> apiCallback) {
        String tokenTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IlRlc3QxMjMiLCJ1c2VybmFtZSI6IlBlZHJvT2thd2EiLCJpZCI6IjU4ZDQwNzZkN2QyZTdmMWNjMjk0MGQ0NCIsImlhdCI6MTQ5MDM3MDMwNX0.OgU3TuvXjWaRsB9N3YowDOx0FbqtVHWi4kPQrnrKZXA";
        apiService
                .validate(tokenTest)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                        Log.w("TEST", "COMPLETE");
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

    /** USER **/

    /**
     * Authenticates user by username and password
     *
     * @param username
     * @param password
     * @param apiCallback
     */
    public void authenticateUser(String username, String password, final ApiCallback<User> apiCallback) {
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
                        apiCallback.onComplete(userResponse.getUser());
                    }
                });
    }

}
