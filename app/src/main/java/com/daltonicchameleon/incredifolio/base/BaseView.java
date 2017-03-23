package com.daltonicchameleon.incredifolio.base;

import android.databinding.ViewDataBinding;

/**
 * incredifolio-app
 * Created in 20/03/17 by the following authors:
 * Pedro Okawa
 */
public interface BaseView<T, K extends ViewDataBinding> {

    T getTarget();
    K getDataBinding();


}
