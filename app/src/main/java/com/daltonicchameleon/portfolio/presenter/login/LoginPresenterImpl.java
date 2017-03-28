package com.daltonicchameleon.portfolio.presenter.login;

import com.daltonicchameleon.portfolio.ui.login.LoginView;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginview;

    public LoginPresenterImpl(LoginView loginview) {
        this.loginview = loginview;
    }

    @Override
    public void initialize() {
//        loginview.getDataBinding().setLoading(true);

    	/* EXECUTE STUFFS */

//        loginview.getDataBinding().setLoading(false);
    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}