package com.daltonicchameleon.portfolio.di.toolbar;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.toolbar.ToolbarPresenter;
import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarActivity;
import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarView;

import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = ToolbarModule.class)
public interface ToolbarComponent {

    void inject(ToolbarActivity fragment);

    ToolbarView providesToolbarView();

    ToolbarPresenter providesToolbarPresenter();

}