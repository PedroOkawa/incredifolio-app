package com.daltonicchameleon.incredifolio.presenter.main;

import com.daltonicchameleon.incredifolio.ui.main.MainView;

/**
 * incredifolio-app
 * Created in 20/03/17 by the following authors:
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