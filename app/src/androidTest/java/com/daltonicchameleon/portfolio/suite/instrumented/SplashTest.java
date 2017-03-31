package com.daltonicchameleon.portfolio.suite.instrumented;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.daltonicchameleon.portfolio.AppTest;
import com.daltonicchameleon.portfolio.R;
import com.daltonicchameleon.portfolio.di.component.DaggerAppTestComponent;
import com.daltonicchameleon.portfolio.ui.splash.SplashActivity;
import com.daltonicchameleon.portfolio.util.TestHelper;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.AccountHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashTest {

    @Inject
    AccountHelper accountHelper;
    @Inject
    ApiService apiService;
    @Inject
    TestHelper testHelper;

    private AccountsListener accountsListener;
    private CountingIdlingResource countingIdlingResource;

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class, true, false);

    @Before
    public void initialize() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = AppTest.class.cast(instrumentation.getTargetContext().getApplicationContext());
        DaggerAppTestComponent daggerAppTestComponent = DaggerAppTestComponent.class.cast(appTest.getAppComponent());
        daggerAppTestComponent.inject(this);
    }

    /* SUCCESS CASES */

    /**
     * Calls validates token requests with a success answer calling main fragment afterwards
     */
    @Test
    public void validToken() {
        accountHelper.addAccount(Constants.TESTS_ACCOUNT_NAME, Constants.TESTS_ACCOUNT_PASSWORD, Constants.TESTS_ACCOUNT_TOKEN);

        Void value = null;

        /* Associates an answer to the 'validate' api request */
        when(apiService.validate("token")).thenReturn(Observable.just(value));

        /* Launch the activity */
        activityRule.launchActivity(null);

        /* Stops the application for a small delay */
        testHelper.startMediumDelay();

        /* Validates if the current layout shown is the main screen */
        testHelper.validateViewVisibility(R.id.lnrMainContent);
    }

    /* ERROR CASES */

    /**
     * Calls validates token requests with an error calling login fragment afterwards
     */
    @Test
    public void invalidToken() {
        accountHelper.addAccount(Constants.TESTS_ACCOUNT_NAME, Constants.TESTS_ACCOUNT_PASSWORD, Constants.TESTS_ACCOUNT_TOKEN);

        /* Associates an answer to the 'validate' api request */
        when(apiService.validate(anyString()))
                .then(testHelper.generatesErrorFromAssets(
                        Constants.TESTS_FILE_VALIDATE_INVALID_TOKEN,
                        Constants.HTTP_STATUS_CODE_ERROR_UNAUTHORIZED));

        /* Launch the activity */
        activityRule.launchActivity(null);

        /* Stops the application for a small delay */
        testHelper.startMediumDelay();

        /* Validates if the current layout shown is the login screen */
        testHelper.validateViewVisibility(R.id.lnrLoginContent);
    }

    /**
     * Calls validates token requests with an error calling login fragment afterwards
     */
    @Test
    public void notRegistered() {
        /* Launch the activity */
        activityRule.launchActivity(null);

        /* Stops the application for a small delay */
        testHelper.startMediumDelay();

        /* Validates if the current layout shown is the login screen */
        testHelper.validateViewVisibility(R.id.lnrLoginContent);
    }

    @After
    public void dispose() {
        accountsListener = new AccountsListener();
        countingIdlingResource = new CountingIdlingResource(SplashTest.class.getCanonicalName());
        Espresso.registerIdlingResources(countingIdlingResource);

        accountHelper.clearAccounts(accountsListener);
    }

    private class AccountsListener implements AccountHelper.OnAccountListener {

        @Override
        public void onFinished() {
            countingIdlingResource.increment();
            Espresso.unregisterIdlingResources(countingIdlingResource);
        }
    }
}
