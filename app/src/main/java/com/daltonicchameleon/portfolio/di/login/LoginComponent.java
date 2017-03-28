package com.daltonicchameleon.portfolio.di.login;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.login.LoginPresenter;
import com.daltonicchameleon.portfolio.ui.login.LoginFragment;
import com.daltonicchameleon.portfolio.ui.login.LoginView;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginFragment fragment);

    LoginView providesLoginView();

    LoginPresenter providesLoginPresenter();

}