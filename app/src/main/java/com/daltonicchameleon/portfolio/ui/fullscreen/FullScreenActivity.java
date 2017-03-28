package com.daltonicchameleon.portfolio.ui.fullscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseActivity;
import com.daltonicchameleon.portfolio.databinding.ActivityFullScreenBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.fullscreen.FullScreenModule;
import com.daltonicchameleon.portfolio.di.fullscreen.DaggerFullScreenComponent;
import com.daltonicchameleon.portfolio.presenter.fullscreen.FullScreenPresenter;

import javax.inject.Inject;

public class FullScreenActivity extends BaseActivity<ActivityFullScreenBinding> implements FullScreenView {

    @Inject
    FullScreenPresenter fullscreenPresenter;

    @Override
    public FullScreenActivity getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_full_screen;
    }

    @Override
    public int contentViewId() {
        return R.id.frmFullScreenContent;
    }

    @Override
    protected void initializesComponents(AppComponent appComponent) {
        DaggerFullScreenComponent
                .builder()
                .appComponent(appComponent)
                .fullScreenModule(new FullScreenModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(@Nullable Bundle savedInstanceState) {
        fullscreenPresenter.initialize();
    }
}