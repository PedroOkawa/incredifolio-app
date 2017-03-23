package com.daltonicchameleon.incredifolio.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.incredifolio.R;
import com.daltonicchameleon.incredifolio.base.BaseActivity;
import com.daltonicchameleon.incredifolio.databinding.ActivityMainBinding;
import com.daltonicchameleon.incredifolio.di.app.AppComponent;
import com.daltonicchameleon.incredifolio.di.main.MainModule;
import com.daltonicchameleon.incredifolio.di.main.DaggerMainComponent;
import com.daltonicchameleon.incredifolio.presenter.main.MainPresenter;

import javax.inject.Inject;

/**
 * incredifolio-app
 * Created in 20/03/17 by the following authors:
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