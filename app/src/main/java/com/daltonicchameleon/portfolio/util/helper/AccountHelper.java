package com.daltonicchameleon.portfolio.util.helper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * portfolio-app
 *
 * Created based on ADAL's class that is a merge of codes that me and the author of the library worked on.
 * https://github.com/massivedisaster/ADAL/blob/master/adal-accounts/src/main/java/com/massivedisaster/adal/account/AccountHelper.java
 *
 * Created in 3/27/17 by the following authors:
 * Pedro Okawa
 */
public class AccountHelper {

    private AccountManager accountManager;
    private Context context;
    private int deletedAccounts;

    public AccountHelper(Context context) {
        this.accountManager = AccountManager.get(context);
        this.context = context;
    }

    /**
     * Add a new account to the account manager
     *
     * @param name     The account name.
     * @param password The account password.
     * @param token    The account token.
     */
    public void addAccount(final String name, final String password, final String token) {
        validateAccountManager();
        clearAccounts(new OnAccountListener() {
            @SuppressWarnings("MissingPermission")
            @Override
            public void onFinished() {
                Account account = new Account(name, context.getPackageName());
                Intent intent = new Intent();

                accountManager.addAccountExplicitly(account, password, null);
                accountManager.setAuthToken(account, context.getPackageName(), token);

                intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, name);
                intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, context.getPackageName());
                intent.putExtra(AccountManager.KEY_AUTHTOKEN, token);
            }
        });
    }

    @SuppressWarnings("MissingPermission")
    public synchronized void clearAccounts(final OnAccountListener onAccountListener) {
        validateAccountManager();
        deletedAccounts = 0;

        final Account[] accounts = accountManager.getAccountsByType(context.getPackageName());

        if (accounts.length == 0) {
            if (onAccountListener != null) {
                onAccountListener.onFinished();
            }
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            for (Account acc : accounts) {
                accountManager.removeAccountExplicitly(acc);
            }

            if (onAccountListener != null) {
                onAccountListener.onFinished();
            }
        } else {
            for (Account account : accounts) {
                AccountManagerCallback<Boolean> callback = new AccountManagerCallback<Boolean>() {
                    @Override
                    public void run(AccountManagerFuture<Boolean> future) {
                        if (onAccountListener != null && ++deletedAccounts == accounts.length) {
                            onAccountListener.onFinished();
                        }
                    }
                };

                accountManager.removeAccount(account, callback, null);
            }
        }
    }

    /**
     * Verify if the application has account added.
     *
     * @return True if the application has a account added.
     */
    @SuppressWarnings("MissingPermission")
    public boolean hasAccount() {
        validateAccountManager();
        return accountManager.getAccountsByType(context.getPackageName()).length > 0;
    }

    /**
     * Verify if the application  have a account and retrieve the account
     *
     * @return The current account.
     */
    @SuppressWarnings("MissingPermission")
    public Account getCurrentAccount() {
        validateAccountManager();
        return accountManager.getAccountsByType(context.getPackageName())[0];
    }

    /**
     * Verify if the application have an account and retrieve the account password
     *
     * @param account The account.
     * @return The user password.
     */
    @SuppressWarnings("MissingPermission")
    public String getAccountPassword(Account account) {
        validateAccountManager();
        return accountManager.getPassword(account);
    }

    /**
     * Verify if the application have an account and retrieve the account token
     *
     * @return The account token.
     */
    @SuppressWarnings("MissingPermission")
    public String getCurrentToken() {
        validateAccountManager();
        Account account = accountManager.getAccountsByType(context.getPackageName())[0];
        return accountManager.peekAuthToken(account, context.getPackageName().trim());
    }

    private void validateAccountManager() {
        if (accountManager == null) {
            throw new NullPointerException("It's necessary to call AccountHelper.initialize first");
        }
    }

    public interface OnAccountListener {

        void onFinished();

    }
}