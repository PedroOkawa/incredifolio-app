package com.daltonicchameleon.portfolio.presenter.register;

import android.view.View;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.model.User;
import com.daltonicchameleon.portfolio.ui.register.RegisterView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.helper.TextHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;

public class RegisterPresenterImpl implements RegisterPresenter {

    private ApiManager apiManager;
    private OnRegisterClickListener onRegisterClickListener;
    private RegisterView registerview;
    private TextHelper textHelper;

    public RegisterPresenterImpl(ApiManager apiManager, RegisterView registerview, TextHelper textHelper) {
        this.apiManager = apiManager;
        this.registerview = registerview;
        this.textHelper = textHelper;
    }

    @Override
    public void initialize() {
        onRegisterClickListener = new OnRegisterClickListener();
        registerview.getDataBinding().btnRegisterSend.setOnClickListener(onRegisterClickListener);
    }

    @Override
    public void dispose() {

    }

    private class RegisterCallback extends ApiCallback<User> {

        public RegisterCallback() {
            super(textHelper);
        }

        @Override
        protected void doOnComplete(User user) {
            registerview.callMain();
            registerview.getDataBinding().setLoading(false);
        }

        @Override
        protected void doOnError(String error) {
            registerview.getDataBinding().btnRegisterSend.setEnabled(true);
            registerview.getDataBinding().setLoading(false);
        }

        @Override
        protected void doOnExpired() {
            registerview.getDataBinding().btnRegisterSend.setEnabled(true);
            registerview.getDataBinding().setLoading(false);
        }
    }

    private class OnRegisterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnRegisterSend) {
                registerview.getDataBinding().btnRegisterSend.setEnabled(false);
                registerview.getDataBinding().setLoading(true);
                apiManager.registerUser(
                        registerview.getDataBinding().edtRegisterUsername.getText().toString(),
                        registerview.getDataBinding().edtRegisterPassword.getText().toString(),
                        new RegisterCallback()
                );
            }
        }
    }

}