package com.daltonicchameleon.portfolio.suite;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import rx.Observable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashTest {

    @Inject
    AccountHelper accountHelper;
    @Inject
    ApiService apiService;
    @Inject
    TestHelper testHelper;

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class, true, false);

    @Before
    public void initialize() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = AppTest.class.cast(instrumentation.getTargetContext().getApplicationContext());
        DaggerAppTestComponent daggerAppTestComponent = DaggerAppTestComponent.class.cast(appTest.getAppComponent());
        daggerAppTestComponent.inject(SplashTest.this);
    }

    /* SUCCESS CASES */

    /**
     * Calls validates token requests with a success answer calling main fragment afterwards
     */
    @Test
    public void validToken() {
        /* Associates to always return true when checking if there's a token stored */
        when(accountHelper.hasToken()).thenReturn(true);
        when(accountHelper.getCurrentToken()).thenReturn("token");

        Void value = null;

        /* Associates an answer to the 'validate' api request */
        when(apiService.validate("token")).thenReturn(Observable.just(value));

        /* Launch the activity */
        activityRule.launchActivity(null);

        /* Stops the application for a small delay */
        testHelper.startSmallDelay();

        /* Validates if the current layout shown is the main screen */
        testHelper.validateViewVisibility(R.id.lnrMainContent);
    }

    /* ERROR CASES */

    /**
     * Calls validates token requests with an error calling login fragment afterwards
     */
    @Test
    public void invalidToken() {
        /* Associates to always return true when checking if there's a token stored */
        when(accountHelper.hasToken()).thenReturn(true);
        when(accountHelper.getCurrentToken()).thenReturn("token");

        /* Associates an answer to the 'validate' api request */
        when(apiService.validate(anyString()))
                .then(testHelper.generatesErrorFromAssets(
                        Constants.TESTS_FILE_VALIDATE_INVALID_TOKEN,
                        Constants.HTTP_STATUS_CODE_ERROR_UNAUTHORIZED));

        /* Launch the activity */
        activityRule.launchActivity(null);

        /* Stops the application for a small delay */
        testHelper.startSmallDelay();

        /* Validates if the current layout shown is the login screen */
        testHelper.validateViewVisibility(R.id.lnrLoginContent);
    }
}
