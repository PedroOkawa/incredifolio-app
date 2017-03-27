package com.daltonicchameleon.portfolio.util.account;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.daltonicchameleon.portfolio.R;

/**
 * portfolio-app
 * Created in 3/27/17 by the following authors:
 * Pedro Okawa
 */
public class AccountAuthenticator extends AbstractAccountAuthenticator {

    private static final int ERROR_CODE_ONE_ACCOUNT_ALLOWED = 1001;

    private AccountManager accountManager;
    private Context context;
    private final Handler handler = new Handler();

    public AccountAuthenticator(Context context) {
        super(context);
        this.context = context;
        this.accountManager = AccountManager.get(context);
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options)
            throws NetworkErrorException {

        if(accountManager.getAccountsByType(context.getPackageName()).length > 0) {
            final Bundle result = new Bundle();
            result.putInt(AccountManager.KEY_ERROR_CODE, ERROR_CODE_ONE_ACCOUNT_ALLOWED);
            result.putString(AccountManager.KEY_ERROR_MESSAGE, context.getString(R.string.error_account_only_one_allowed));

            handler.post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, R.string.error_account_only_one_allowed, Toast.LENGTH_SHORT).show();
                }
            });

            return result;
        }

        return null;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        // TODO Auto-generated method stub
        return null;
    }
}