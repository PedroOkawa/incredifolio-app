package com.daltonicchameleon.portfolio.ui.register;

import com.daltonicchameleon.portfolio.base.BaseView;
import com.daltonicchameleon.portfolio.databinding.FragmentRegisterBinding;

public interface RegisterView extends BaseView<RegisterFragment, FragmentRegisterBinding> {

    void callMain();
    void callLogin();

}