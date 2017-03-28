package com.daltonicchameleon.portfolio.ui.toolbar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.base.BaseActivity;
import com.daltonicchameleon.portfolio.databinding.ActivityToolbarBinding;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.toolbar.DaggerToolbarComponent;
import com.daltonicchameleon.portfolio.di.toolbar.ToolbarModule;
import com.daltonicchameleon.portfolio.presenter.toolbar.ToolbarPresenter;

import javax.inject.Inject;

public class ToolbarActivity extends BaseActivity<ActivityToolbarBinding> implements ToolbarView {

    @Inject
    ToolbarPresenter toolbarPresenter;

    @Override
    public ToolbarActivity getTarget() {
        return this;
    }

    @Override
    protected int layoutToInflate() {
        return R.layout.activity_toolbar;
    }

    @Override
    public int contentViewId() {
        return R.id.frmToolbarContent;
    }

    @Override
    protected void initializesComponents(AppComponent appComponent) {
        DaggerToolbarComponent
                .builder()
                .appComponent(appComponent)
                .toolbarModule(new ToolbarModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void doOnCreated(@Nullable Bundle savedInstanceState) {
        toolbarPresenter.initialize();
    }

}