package com.daltonicchameleon.portfolio.suite.unit;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.daltonicchameleon.portfolio.AppTest;
import com.daltonicchameleon.portfolio.di.component.DaggerAppTestComponent;
import com.daltonicchameleon.portfolio.util.constants.Constants;
import com.daltonicchameleon.portfolio.util.helper.FileHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

/**
 * portfolio-app
 * Created in 3/30/17 by the following authors:
 * Pedro Okawa
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class FileHelperTest {

    @Inject
    FileHelper fileHelper;

    @Before
    public void initialize() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AppTest appTest = AppTest.class.cast(instrumentation.getTargetContext().getApplicationContext());
        DaggerAppTestComponent daggerAppTestComponent = DaggerAppTestComponent.class.cast(appTest.getAppComponent());
        daggerAppTestComponent.inject(this);
    }

    @Test
    public void loadExpectedFile() {
        String json = fileHelper.loadJSONFromAsset(Constants.TESTS_FILE_VALIDATE_SUCCESS);

        assertNotNull(json);
    }

}
