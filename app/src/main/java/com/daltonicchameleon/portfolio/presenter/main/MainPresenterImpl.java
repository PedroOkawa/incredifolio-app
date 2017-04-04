package com.daltonicchameleon.portfolio.presenter.main;

import android.support.v7.widget.LinearLayoutManager;

import com.daltonicchameleon.portfolio.ui.main.MainView;
import com.daltonicchameleon.portfolio.util.adapter.PortfolioAdapter;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

public class MainPresenterImpl implements MainPresenter {

    private ApiManager apiManager;
    private MainView mainView;
    private PortfolioAdapter portfolioAdapter;

    public MainPresenterImpl(ApiManager apiManager, MainView mainView) {
        this.apiManager = apiManager;
        this.mainView = mainView;
    }

    @Override
    public void initialize() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainView.getTarget().getContext());
        portfolioAdapter = new PortfolioAdapter(mainView.getTarget().getContext());

        mainView.getDataBinding().rclMainContent.setLayoutManager(linearLayoutManager);
        mainView.getDataBinding().rclMainContent.setAdapter(portfolioAdapter);
    }

    @Override
    public void dispose() {

    }

}