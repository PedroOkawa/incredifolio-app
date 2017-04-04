package com.daltonicchameleon.portfolio.util.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.databinding.AdapterPortfolioBinding;
import com.daltonicchameleon.portfolio.model.Portfolio;

/**
 * portfolio-app
 * Created in 4/4/17 by the following authors:
 * Pedro Okawa
 */
public class PortfolioAdapter extends BindingAdapter<Portfolio, AdapterPortfolioBinding> {

    public PortfolioAdapter(Context context) {
        super(context);
    }

    @Override
    protected int layoutToInflate(int viewType) {
        return R.layout.adapter_portfolio;
    }

    @Override
    protected void doOnBindViewHolder(ViewHolder viewHolder, Portfolio portfolio, int position) {
        Glide.with(getContext())
                .load(portfolio.getImage())
                .into(viewHolder.getDataBinding().imgAdapterPortfolioImage);
    }
}
