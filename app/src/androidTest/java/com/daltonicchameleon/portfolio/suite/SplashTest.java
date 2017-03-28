package com.daltonicchameleon.portfolio.suite;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.daltonicchameleon.portfolio.AppTest;
import com.daltonicchameleon.portfolio.di.DaggerAppTestComponent;
import com.daltonicchameleon.portfolio.ui.splash.SplashActivity;
import com.daltonicchameleon.portfolio.util.api.ApiService;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.daltonicchameleon.portfolio.util.helper.FileHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
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
    ApiService apiService;
    @Inject
    ConnectionHelper connectionHelper;
    @Inject
    FileHelper fileHelper;
    @Inject
    Retrofit retrofit;

    @Rule
    public ActivityTestRule<SplashActivity> activityRule = new ActivityTestRule<>(SplashActivity.class, true, false);

    @Before
    public void initialize() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = AppTest.class.cast(instrumentation.getTargetContext().getApplicationContext());
        DaggerAppTestComponent daggerAppTestComponent = DaggerAppTestComponent.class.cast(appTest.getAppComponent());
        daggerAppTestComponent.inject(SplashTest.this);

        activityRule.launchActivity(null);
    }

    @Test
    public void validateToken() {
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(Constants.API_MODULE_APPLICATION_JSON),
                fileHelper.loadJSONFromAsset(Constants.TESTS_FILE_VALIDATE_INVALID_TOKEN));
        final Response response = Response.error(Constants.HTTP_STATUS_CODE_ERROR_UNAUTHORIZED, responseBody);
        Answer<Observable> answer = new Answer<Observable>() {
            @Override
            public Observable answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Observable.error(connectionHelper.asRetrofitException(new HttpException(response), retrofit));
            }
        };
        when(apiService.validate(anyString())).then(answer);
    }
}
