package com.daltonicchameleon.portfolio.di.register;

import com.daltonicchameleon.portfolio.di.scope.Fragment;
import com.daltonicchameleon.portfolio.presenter.register.RegisterPresenter;
import com.daltonicchameleon.portfolio.presenter.register.RegisterPresenterImpl;
import com.daltonicchameleon.portfolio.ui.register.RegisterView;
import com.daltonicchameleon.portfolio.util.helper.FormHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;
import com.daltonicchameleon.portfolio.util.manager.FeedbackManager;

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
    public RegisterPresenter providesRegisterPresenter(ApiManager apiManager, FeedbackManager feedbackManager, FormHelper formHelper, RegisterView registerView) {
        return new RegisterPresenterImpl(apiManager, feedbackManager, formHelper, registerView);
    }

}