package com.daltonicchameleon.portfolio.presenter.toolbar;

import com.daltonicchameleon.portfolio.ui.toolbar.ToolbarView;

public class ToolbarPresenterImpl implements ToolbarPresenter {

    private ToolbarView toolbarview;

    public ToolbarPresenterImpl(ToolbarView toolbarview) {
        this.toolbarview = toolbarview;
    }

    @Override
    public void initialize() {
//        toolbarview.getDataBinding().setLoading(true);

    	/* EXECUTE STUFFS */

//        toolbarview.getDataBinding().setLoading(false);
    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}