package com.daltonicchameleon.portfolio.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseActivity;
import com.daltonicchameleon.portfolio.databinding.ActivityMainBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.main.MainModule;
import com.daltonicchameleon.portfolio.di.main.DaggerMainComponent;
import com.daltonicchameleon.portfolio.presenter.main.MainPresenter;

import javax.inject.Inject;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainView {

    @Inject
    MainPresenter mainPresenter;

    @Override
    public MainActivity getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_main;
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