package com.daltonicchameleon.portfolio.di.register;

import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.register.RegisterPresenter;
import com.daltonicchameleon.portfolio.presenter.register.RegisterPresenterImpl;
import com.daltonicchameleon.portfolio.ui.register.RegisterView;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

import dagger.Module;
import dagger.Provides;

@Module
public class RegisterModule {

    private RegisterView view;

    public RegisterModule(RegisterView view) {
        this.view = view;
    }

    @Fragment
    @Provides
    public RegisterView providesRegisterView() {
        return view;
    }

    @Fragment
    @Provides
    public RegisterPresenter providesRegisterPresenter(ApiManager apiManager, RegisterView registerView, TextHelper textHelper) {
        return new RegisterPresenterImpl(apiManager, registerView, textHelper);
    }

}