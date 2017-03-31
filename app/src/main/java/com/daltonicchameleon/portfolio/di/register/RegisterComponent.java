package com.daltonicchameleon.portfolio.di.register;

import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.register.RegisterPresenter;
import com.daltonicchameleon.portfolio.ui.register.RegisterFragment;
import com.daltonicchameleon.portfolio.ui.register.RegisterView;

import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = RegisterModule.class)
public interface RegisterComponent {

    void inject(RegisterFragment fragment);

    RegisterView providesRegisterView();

    RegisterPresenter providesRegisterPresenter();

}