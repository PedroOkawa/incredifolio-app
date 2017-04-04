package com.daltonicchameleon.portfolio.presenter.login;

import android.support.annotation.IdRes;
import android.view.View;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.model.User;
import com.daltonicchameleon.portfolio.ui.login.LoginView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.helper.FormHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;
import com.daltonicchameleon.portfolio.util.manager.FeedbackManager;

public class LoginPresenterImpl implements LoginPresenter {

    private ApiManager apiManager;
    private FeedbackManager feedbackManager;
    private FormHelper formHelper;
    private LoginView loginView;

    public LoginPresenterImpl(ApiManager apiManager, FeedbackManager feedbackManager, FormHelper formHelper, LoginView loginView) {
        this.apiManager = apiManager;
        this.feedbackManager = feedbackManager;
        this.formHelper = formHelper;
        this.loginView = loginView;
    }

    @Override
    public void initialize() {
        OnLoginClickListener onLoginClickListener = new OnLoginClickListener();
        loginView.getDataBinding().setOnClickListener(onLoginClickListener);
    }

    @Override
    public void dispose() {

    }

    /**
     * Handles login click listener behavior
     */
    private void onLoginClick() {
        /* Validates if the form is filled */
        @IdRes int viewId = formHelper.checkIfValuesAreFilled(
                loginView.getDataBinding().edtLoginUsername,
                loginView.getDataBinding().edtLoginPassword
        );

        switch(viewId) {
            case R.id.edtLoginUsername:
                feedbackManager.showSnackBar(
                        loginView.getDataBinding().getRoot(),
                        R.string.error_login_register_provide_username
                );
                return;
            case R.id.edtLoginPassword:
                feedbackManager.showSnackBar(
                        loginView.getDataBinding().getRoot(),
                        R.string.error_login_register_provide_password
                );
                return;
            default:
                /* Form is valid and requests login */
                sendLogin();
        }
    }

    /**
     * Sends the login request after all form validations
     */
    private void sendLogin() {
        loginView.getDataBinding().btnLoginSend.setEnabled(false);
        loginView.getDataBinding().setLoading(true);
        apiManager.authenticateUser(
                loginView.getDataBinding().edtLoginUsername.getText().toString(),
                loginView.getDataBinding().edtLoginPassword.getText().toString(),
                new LoginCallback()
        );
    }

    private class LoginCallback extends ApiCallback<User> {

        @Override
        protected void doOnComplete(User user) {
            loginView.callMain();
            loginView.getDataBinding().setLoading(false);
        }

        @Override
        protected void doOnError(String error) {
            loginView.getDataBinding().btnLoginSend.setEnabled(true);
            loginView.getDataBinding().setLoading(false);
            feedbackManager.showSnackBar(loginView.getDataBinding().getRoot(), error);
        }
    }

    private class OnLoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnLoginSend) {
                onLoginClick();
            } else if(view.getId() == R.id.txtLoginRegister) {
                loginView.callRegister();
            }
        }
    }

}