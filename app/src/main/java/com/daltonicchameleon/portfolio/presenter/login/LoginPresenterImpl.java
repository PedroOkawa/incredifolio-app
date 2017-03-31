package com.daltonicchameleon.portfolio.presenter.login;

import android.view.View;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.model.User;
import com.daltonicchameleon.portfolio.ui.login.LoginView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

public class LoginPresenterImpl implements LoginPresenter {

    private ApiManager apiManager;
    private LoginView loginView;
    private OnLoginClickListener onLoginClickListener;
    private TextHelper textHelper;

    public LoginPresenterImpl(ApiManager apiManager, LoginView loginView, TextHelper textHelper) {
        this.apiManager = apiManager;
        this.loginView = loginView;
        this.textHelper = textHelper;
    }

    @Override
    public void initialize() {
        onLoginClickListener = new OnLoginClickListener();
        loginView.getDataBinding().btnLoginSend.setOnClickListener(onLoginClickListener);
        loginView.getDataBinding().txtLoginRegister.setOnClickListener(onLoginClickListener);
    }

    @Override
    public void dispose() {

    }

    private class LoginCallback extends ApiCallback<User> {

        public LoginCallback() {
            super(textHelper);
        }

        @Override
        protected void doOnComplete(User user) {
            loginView.callMain();
            loginView.getDataBinding().setLoading(false);
        }

        @Override
        protected void doOnError(String error) {
            loginView.getDataBinding().btnLoginSend.setEnabled(true);
            loginView.getDataBinding().setLoading(false);
        }

        @Override
        protected void doOnExpired() {
            loginView.getDataBinding().btnLoginSend.setEnabled(true);
            loginView.getDataBinding().setLoading(false);
        }
    }

    private class OnLoginClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnLoginSend) {
                loginView.getDataBinding().btnLoginSend.setEnabled(false);
                loginView.getDataBinding().setLoading(true);
                apiManager.authenticateUser(
                        loginView.getDataBinding().edtLoginUsername.getText().toString(),
                        loginView.getDataBinding().edtLoginPassword.getText().toString(),
                        new LoginCallback()
                );
            } else if(view.getId() == R.id.txtLoginRegister) {
                loginView.callRegister();
            }
        }
    }

}