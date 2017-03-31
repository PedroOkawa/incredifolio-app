package com.daltonicchameleon.portfolio.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseFragment;
import com.daltonicchameleon.portfolio.databinding.FragmentLoginBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.login.DaggerLoginComponent;
import com.daltonicchameleon.portfolio.di.login.LoginModule;
import com.daltonicchameleon.portfolio.presenter.login.LoginPresenter;
import com.daltonicchameleon.portfolio.ui.main.MainFragment;
import com.daltonicchameleon.portfolio.ui.register.RegisterFragment;
import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarActivity;
import com.daltonicchameleon.portfolio.util.manager.CallManager;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements LoginView {

    @Inject
    CallManager callManager;
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

    @Override
    protected void dispose() {
        loginPresenter.dispose();
    }

    @Override
    public void callMain() {
        callManager.open(getActivity(), ToolbarActivity.class, MainFragment.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void callRegister() {
        callManager.add(getBaseActivity(), RegisterFragment.class);
    }
}