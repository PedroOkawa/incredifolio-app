package com.daltonicchameleon.portfolio.presenter.main;

import android.support.v7.widget.LinearLayoutManager;

import com.daltonicchameleon.portfolio.model.Portfolio;
import com.daltonicchameleon.portfolio.ui.main.MainView;
import com.daltonicchameleon.portfolio.util.EndlessScrollListener;
import com.daltonicchameleon.portfolio.util.adapter.PortfolioAdapter;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

import java.util.Date;
import java.util.List;

public class MainPresenterImpl implements MainPresenter {

    private long portfolioListDate;

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
        PortfolioScrollListener portfolioScrollListener = new PortfolioScrollListener(linearLayoutManager);
        portfolioAdapter = new PortfolioAdapter(mainView.getTarget().getContext());

        mainView.getDataBinding().rclMainContent.setLayoutManager(linearLayoutManager);
        mainView.getDataBinding().rclMainContent.setAdapter(portfolioAdapter);
        mainView.getDataBinding().rclMainContent.addOnScrollListener(portfolioScrollListener);

        portfolioListDate = new Date().getTime();

        apiManager.listPortfolios(new PortfolioListCallback(), portfolioListDate);
    }

    @Override
    public void dispose() {

    }

    private class PortfolioScrollListener extends EndlessScrollListener {

        public PortfolioScrollListener(LinearLayoutManager linearLayoutManager) {
            super(linearLayoutManager);
        }

        @Override
        public void onVisibleThreshold() {
            apiManager.listPortfolios(new PortfolioListCallback(), portfolioListDate);
        }
    }

    private class PortfolioListCallback extends ApiCallback<List<Portfolio>> {

        @Override
        protected void doOnComplete(List<Portfolio> portfolios) {
            if(portfolios.isEmpty()) {
                return;
            }

            portfolioAdapter.addAll(portfolios);
            portfolioListDate = portfolios.get(portfolios.size() - 1).getCreatedAt().getTime() - 1000;
        }

        @Override
        protected void doOnError(String error) {

        }
    }

}