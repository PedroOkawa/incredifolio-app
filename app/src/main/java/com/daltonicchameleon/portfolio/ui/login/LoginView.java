package com.daltonicchameleon.portfolio.ui.login;

import com.daltonicchameleon.portfolio.base.BaseView;
import com.daltonicchameleon.portfolio.databinding.FragmentLoginBinding;

public interface LoginView extends BaseView<LoginFragment, FragmentLoginBinding> {

    void callMain();
    void callRegister();

}