package com.daltonicchameleon.portfolio.presenter.main;

import com.daltonicchameleon.portfolio.ui.main.MainView;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView mainview;

    public MainPresenterImpl(MainView mainview) {
        this.mainview = mainview;
    }

    @Override
    public void initialize() {
        mainview.getDataBinding().setLoading(true);

    	/* EXECUTE STUFFS */

        mainview.getDataBinding().setLoading(false);
    }

    @Override
    public void dispose() {
        /* DESTROY ALL INSTANCES */
    }

}