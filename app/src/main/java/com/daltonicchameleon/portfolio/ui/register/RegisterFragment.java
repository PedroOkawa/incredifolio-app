package com.daltonicchameleon.portfolio.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseFragment;
import com.daltonicchameleon.portfolio.databinding.FragmentRegisterBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.register.DaggerRegisterComponent;
import com.daltonicchameleon.portfolio.di.register.RegisterModule;
import com.daltonicchameleon.portfolio.presenter.register.RegisterPresenter;
import com.daltonicchameleon.portfolio.ui.login.LoginFragment;
import com.daltonicchameleon.portfolio.ui.main.MainFragment;
import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarActivity;
import com.daltonicchameleon.portfolio.util.manager.CallManager;

import javax.inject.Inject;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> implements RegisterView {

    @Inject
    CallManager callManager;
    @Inject
    RegisterPresenter registerPresenter;

    @Override
    public RegisterFragment getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initializesComponents(AppComponent appComponent) {
        DaggerRegisterComponent
                .builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(@Nullable Bundle savedInstanceState) {
        registerPresenter.initialize();
    }

    @Override
    protected void dispose() {
        registerPresenter.dispose();
    }

    @Override
    public void callMain() {
        callManager.open(getActivity(), ToolbarActivity.class, MainFragment.class, Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    public void callLogin() {
        callManager.add(getBaseActivity(), LoginFragment.class);
        callManager.clearBackStack(getBaseActivity());
    }
}