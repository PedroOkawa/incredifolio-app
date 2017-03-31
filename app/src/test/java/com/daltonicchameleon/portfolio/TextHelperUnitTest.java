package com.daltonicchameleon.portfolio;

import android.content.Context;

import com.daltonicchameleon.portfolio.util.helper.TextHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * portfolio-app by
 * Created in 3/30/17 by the following authors:
 * Pedro Okawa
 */
@RunWith(MockitoJUnitRunner.class)
public class TextHelperUnitTest {

    private static final String textHelperSuccess = "Success";
    private static final String textHelperFailRight = "Fail";
    private static final String textHelperFailWrong = "Fail#";

    private Context context;
    private TextHelper textHelper;

    @Before
    public void initialize() {
        context = Mockito.mock(Context.class);
        textHelper = new TextHelper(context);
    }

    @Test
    public void generateSameString() {
        when(context.getString(R.string.text_helper_success)).thenReturn(textHelperSuccess);
        assertThat(textHelper.convertString(R.string.text_helper_success), is(textHelperSuccess));
    }

    @Test
    public void generateDifferentString() {
        when(context.getString(R.string.text_helper_fail)).thenReturn(textHelperFailRight);
        assertThat(textHelper.convertString(R.string.text_helper_fail), is(not((textHelperFailWrong))));
    }
}