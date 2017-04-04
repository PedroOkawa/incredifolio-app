package com.daltonicchameleon.portfolio.presenter.register;

import android.support.annotation.IdRes;
import android.view.View;

import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.model.User;
import com.daltonicchameleon.portfolio.ui.register.RegisterView;
import com.daltonicchameleon.portfolio.util.api.ApiCallback;
import com.daltonicchameleon.portfolio.util.helper.FormHelper;
import com.daltonicchameleon.portfolio.util.manager.ApiManager;
import com.daltonicchameleon.portfolio.util.manager.FeedbackManager;

public class RegisterPresenterImpl implements RegisterPresenter {

    private ApiManager apiManager;
    private FeedbackManager feedbackManager;
    private FormHelper formHelper;
    private RegisterView registerview;

    public RegisterPresenterImpl(ApiManager apiManager, FeedbackManager feedbackManager, FormHelper formHelper, RegisterView registerview) {
        this.apiManager = apiManager;
        this.feedbackManager = feedbackManager;
        this.formHelper = formHelper;
        this.registerview = registerview;
    }

    @Override
    public void initialize() {
        OnRegisterClickListener onRegisterClickListener = new OnRegisterClickListener();
        registerview.getDataBinding().setOnClickListener(onRegisterClickListener);
    }

    @Override
    public void dispose() {

    }

    /**
     * Handles register click listener behavior
     */
    private void onRegisterClick() {
        /* Validates if the form is filled */
        @IdRes int viewId = formHelper.checkIfValuesAreFilled(
                registerview.getDataBinding().edtRegisterUsername,
                registerview.getDataBinding().edtRegisterPassword
        );

        switch(viewId) {
            case R.id.edtRegisterUsername:
                feedbackManager.showSnackBar(
                        registerview.getDataBinding().getRoot(),
                        R.string.error_login_register_provide_username
                );
                return;
            case R.id.edtRegisterPassword:
                feedbackManager.showSnackBar(
                        registerview.getDataBinding().getRoot(),
                        R.string.error_login_register_provide_password
                );
                return;
            default:
                /* Validates if the given passwords match */
                boolean passwordMatches = formHelper.checkIfValuesMatches(
                        registerview.getDataBinding().edtRegisterPassword,
                        registerview.getDataBinding().edtRegisterRetypePassword
                );

                if(!passwordMatches) {
                    feedbackManager.showSnackBar(
                            registerview.getDataBinding().getRoot(),
                            R.string.error_login_register_match_password
                    );
                    return;
                }

                /* Form is valid and requests register */
                sendRegister();
        }
    }

    /**
     * Sends the register request after all form validations
     */
    private void sendRegister() {
        registerview.getDataBinding().btnRegisterSend.setEnabled(false);
        registerview.getDataBinding().setLoading(true);
        apiManager.registerUser(
                registerview.getDataBinding().edtRegisterUsername.getText().toString(),
                registerview.getDataBinding().edtRegisterPassword.getText().toString(),
                new RegisterCallback()
        );
    }

    private class RegisterCallback extends ApiCallback<User> {

        @Override
        protected void doOnComplete(User user) {
            registerview.callMain();
            registerview.getDataBinding().setLoading(false);
        }

        @Override
        protected void doOnError(String error) {
            registerview.getDataBinding().btnRegisterSend.setEnabled(true);
            registerview.getDataBinding().setLoading(false);
            feedbackManager.showSnackBar(registerview.getDataBinding().getRoot(), error);
        }
    }

    private class OnRegisterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnRegisterSend) {
                onRegisterClick();
            } else if(view.getId() == R.id.txtRegisterLogin) {
                registerview.callLogin();
            }
        }
    }

}