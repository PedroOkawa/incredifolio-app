package com.daltonicchameleon.portfolio.di.toolbar;

import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.toolbar.ToolbarPresenter;
import com.daltonicchameleon.portfolio.presenter.toolbar.ToolbarPresenterImpl;
import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarView;

import dagger.Module;
import dagger.Provides;

@Module
public class ToolbarModule {

    private ToolbarView view;

    public ToolbarModule(ToolbarView view) {
        this.view = view;
    }

    @Activity
    @Provides
    public ToolbarView providesToolbarView() {
        return view;
    }

    @Activity
    @Provides
    public ToolbarPresenter providesToolbarPresenter(ToolbarView toolbarView) {
        return new ToolbarPresenterImpl(toolbarView);
    }

}