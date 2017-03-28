package com.daltonicchameleon.portfolio.presenter.fullscreen;

import com.daltonicchameleon.portfolio.ui.fullscreen.FullScreenView;

public class FullScreenPresenterImpl implements FullScreenPresenter {

    private FullScreenView fullScreenView;

    public FullScreenPresenterImpl(FullScreenView fullScreenView) {
        this.fullScreenView = fullScreenView;
    }

    @Override
    public void initialize() {
//        fullScreenView.getDataBinding().setLoading(true);

    	/* EXECUTE STUFFS */

//        fullScreenView.getDataBinding().setLoading(false);
    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}