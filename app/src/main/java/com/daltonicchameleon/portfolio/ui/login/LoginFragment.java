package com.daltonicchameleon.portfolio.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseFragment;
import com.daltonicchameleon.portfolio.databinding.FragmentLoginBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.login.LoginModule;
import com.daltonicchameleon.portfolio.di.login.DaggerLoginComponent;
import com.daltonicchameleon.portfolio.presenter.login.LoginPresenter;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements LoginView {

    @Inject
    LoginPresenter loginPresenter;

    @Override
    public LoginFragment getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initializesComponents(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(@Nullable Bundle savedInstanceState) {
        loginPresenter.initialize();
    }

}