package com.daltonicchameleon.portfolio.di.module;

import com.daltonicchameleon.portfolio.App;
import com.daltonicchameleon.portfolio.di.base.UtilsModule;
import com.daltonicchameleon.portfolio.util.helper.AccountHelper;

import static org.mockito.Mockito.mock;

/**
 * portfolio-app
 * Created in 3/29/17 by the following authors:
 * Pedro Okawa
 */
public class UtilsTestModule extends UtilsModule {

    /**
     * Provides mocked version of the AccountHelper used only for test purpose.
     * @param app
     * @return mocked accountHelper
     */
    @Override
    public AccountHelper providesAccountHelper(App app) {
        return mock(AccountHelper.class);
    }
}
