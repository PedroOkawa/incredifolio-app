package com.daltonicchameleon.portfolio.util;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.daltonicchameleon.portfolio.util.helper.FileHelper;
import com.google.gson.Gson;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

import static android.os.SystemClock.sleep;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * portfolio-app
 * Created in 3/29/17 by the following authors:
 * Pedro Okawa
 */
public class TestHelper {

    private ConnectionHelper connectionHelper;
    private FileHelper fileHelper;
    private Gson gson;
    private Retrofit retrofit;

    public TestHelper(ConnectionHelper connectionHelper, FileHelper fileHelper, Gson gson, Retrofit retrofit) {
        this.connectionHelper = connectionHelper;
        this.fileHelper = fileHelper;
        this.gson = gson;
        this.retrofit = retrofit;
    }

    /**
     * Generates api error from pre-defined mocked json answers
     *
     * @param filePath
     * @param clazz
     * @return answer
     */
    public <T> Observable<T> generatesSuccessFromAssets(String filePath, Class<T> clazz) {
        return Observable.just(gson.fromJson(fileHelper.loadJSONFromAsset(filePath), clazz));
    }

    /**
     * Generates api error from pre-defined mocked json answers
     *
     * @param filePath
     * @param statusCode
     * @return answer
     */
    public Answer generatesErrorFromAssets(String filePath, int statusCode) {
        ResponseBody responseBody = ResponseBody.create(MediaType.parse(Constants.API_MODULE_APPLICATION_JSON),
                fileHelper.loadJSONFromAsset(filePath));
        final Response response = Response.error(statusCode, responseBody);
        return new Answer<Observable>() {
            @Override
            public Observable answer(InvocationOnMock invocationOnMock) throws Throwable {
                return Observable.error(connectionHelper.asRetrofitException(new HttpException(response), retrofit));
            }
        };
    }

    /**
     * Stops the application for {@value Constants#DELAY_SMALL} milliseconds
     */
    public void startSmallDelay() {
        sleep(Constants.DELAY_SMALL);
    }

    /**
     * Stops the application for {@value Constants#DELAY_MEDIUM} milliseconds
     */
    public void startMediumDelay() {
        sleep(Constants.DELAY_MEDIUM);
    }

    /**
     * Stops the application for {@value Constants#DELAY_LARGE} milliseconds
     */
    public void startLargeDelay() {
        sleep(Constants.DELAY_LARGE);
    }

    /**
     * Validates if the view is visible based on id
     *
     * @param viewId
     */
    public void validateViewVisibility(@IdRes int viewId) {
        onView(withId(viewId)).check(matches(isDisplayed()));
    }

    /**
     * Validates if the view is visible based on id and text (String)
     *
     * @param viewId
     * @param text
     */
    public void validateViewVisibility(@IdRes int viewId, String text) {
        onView(allOf(withId(viewId), withText(text))).check(matches(isDisplayed()));
    }

    /**
     * Validates if the view is visible based on id and text (IdRes)
     *
     * @param viewId
     * @param textId
     */
    public void validateViewVisibility(@IdRes int viewId, @StringRes int textId) {
        onView(allOf(withId(viewId), withText(textId))).check(matches(isDisplayed()));
    }

}
