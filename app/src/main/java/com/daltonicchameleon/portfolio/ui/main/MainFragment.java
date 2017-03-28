package com.daltonicchameleon.portfolio.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseFragment;
import com.daltonicchameleon.portfolio.databinding.FragmentMainBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.main.MainModule;
import com.daltonicchameleon.portfolio.di.main.DaggerMainComponent;
import com.daltonicchameleon.portfolio.presenter.main.MainPresenter;

import javax.inject.Inject;

public class MainFragment extends BaseFragment<FragmentMainBinding> implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    public MainFragment getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initializesComponents(AppComponent appComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(@Nullable Bundle savedInstanceState) {
        mainPresenter.initialize();
    }

}