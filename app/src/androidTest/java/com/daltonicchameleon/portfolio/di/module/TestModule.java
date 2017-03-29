package com.daltonicchameleon.portfolio.di.module;

import com.daltonicchameleon.portfolio.util.TestHelper;
import com.daltonicchameleon.portfolio.util.helper.ConnectionHelper;
import com.daltonicchameleon.portfolio.util.helper.FileHelper;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * portfolio-app
 * Created in 3/29/17 by the following authors:
 * Pedro Okawa
 */
@Module
public class TestModule {

    /**
     * Provides an instance of Test helper that will handle functions able to help user on test like
     * open mocked json files, execute pre-defined instrumented tests etc.
     *
     * @param connectionHelper
     * @param fileHelper
     * @param gson
     * @param retrofit
     * @return testHelper
     */
    @Singleton
    @Provides
    public TestHelper providesTestHelper(ConnectionHelper connectionHelper, FileHelper fileHelper, Gson gson, Retrofit retrofit) {
        return new TestHelper(connectionHelper, fileHelper, gson, retrofit);
    }

}
