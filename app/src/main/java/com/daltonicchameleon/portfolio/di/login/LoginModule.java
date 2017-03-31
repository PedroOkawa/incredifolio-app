package com.daltonicchameleon.portfolio.di.login;

import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.login.LoginPresenter;
import com.daltonicchameleon.portfolio.presenter.login.LoginPresenterImpl;
import com.daltonicchameleon.portfolio.ui.login.LoginView;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Fragment
    @Provides
    public LoginView providesLoginView() {
        return view;
    }

    @Fragment
    @Provides
    public LoginPresenter providesLoginPresenter(ApiManager apiManager, LoginView loginView, TextHelper textHelper) {
        return new LoginPresenterImpl(apiManager, loginView, textHelper);
    }

}