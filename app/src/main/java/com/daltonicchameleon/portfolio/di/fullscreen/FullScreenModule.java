package com.daltonicchameleon.portfolio.di.fullscreen;

import com.daltonicchameleon.portfolio.di.scope.Activity;
import com.daltonicchameleon.portfolio.presenter.fullscreen.FullScreenPresenter;
import com.daltonicchameleon.portfolio.presenter.fullscreen.FullScreenPresenterImpl;
import com.daltonicchameleon.portfolio.ui.fullscreen.FullScreenView;

import dagger.Module;
import dagger.Provides;

@Module
public class FullScreenModule {

    private FullScreenView view;

    public FullScreenModule(FullScreenView view) {
        this.view = view;
    }

    @Activity
    @Provides
    public FullScreenView providesFullScreenView() {
        return view;
    }

    @Activity
    @Provides
    public FullScreenPresenter providesFullScreenPresenter(FullScreenView fullscreenView) {
        return new FullScreenPresenterImpl(fullscreenView);
    }

}