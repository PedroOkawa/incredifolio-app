package com.daltonicchameleon.incredifolio.base;

import android.databinding.ViewDataBinding;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public interface BaseView<T, K extends ViewDataBinding> {

    T getTarget();
    K getDataBinding();


}
