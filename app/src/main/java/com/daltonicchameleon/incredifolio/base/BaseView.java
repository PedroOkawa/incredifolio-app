package com.daltonicchameleon.incredifolio.base;

import android.databinding.ViewDataBinding;

public interface BaseView<T, K extends ViewDataBinding> {

    T getTarget();

    K getDataBinding();

}
