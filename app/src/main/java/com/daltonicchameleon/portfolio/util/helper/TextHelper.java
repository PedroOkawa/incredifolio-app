package com.daltonicchameleon.portfolio.util.helper;

import android.support.annotation.StringRes;

import com.daltonicchameleon.portfolio.App;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 3/24/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
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
