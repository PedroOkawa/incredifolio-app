package com.daltonicchameleon.portfolio.suite.unit;

import android.accounts.Account;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.daltonicchameleon.portfolio.AppTest;
import com.daltonicchameleon.portfolio.di.component.DaggerAppTestComponent;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.AccountHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * portfolio-app
 * Created in 3/30/17 by the following authors:
 * Pedro Okawa
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class AccountHelperTest {

    @Inject
    AccountHelper accountHelper;

    @Before
    public void initialize() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = AppTest.class.cast(instrumentation.getTargetContext().getApplicationContext());
        DaggerAppTestComponent daggerAppTestComponent = DaggerAppTestComponent.class.cast(appTest.getAppComponent());
        daggerAppTestComponent.inject(this);
    }

    @Test
    public void addAccount() {
        accountHelper.addAccount(Constants.TESTS_ACCOUNT_NAME, Constants.TESTS_ACCOUNT_PASSWORD, Constants.TESTS_ACCOUNT_TOKEN);
        Account account = accountHelper.getCurrentAccount();

        assertEquals(account.name, Constants.TESTS_ACCOUNT_NAME);
        assertEquals(accountHelper.getAccountPassword(account), Constants.TESTS_ACCOUNT_PASSWORD);
        assertEquals(accountHelper.getCurrentToken(), Constants.TESTS_ACCOUNT_TOKEN);
    }

    @Test
    public void clearAccounts() {
        addAccount();

        accountHelper.clearAccounts(new AccountHelper.OnAccountListener() {
            @Override
            public void onFinished() {
                assertFalse(accountHelper.hasAccount());
            }
        });
    }
}
