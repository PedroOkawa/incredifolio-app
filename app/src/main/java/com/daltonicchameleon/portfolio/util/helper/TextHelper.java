package com.daltonicchameleon.portfolio.util.helper;

import android.support.annotation.StringRes;

import com.daltonicchameleon.portfolio.App;

/**
 * portfolio-app
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class TextHelper {

    private App app;

    public TextHelper(App app) {
        this.app = app;
    }

    public String convertString(@StringRes int stringId) {
        return app.getString(stringId);
    }

}
