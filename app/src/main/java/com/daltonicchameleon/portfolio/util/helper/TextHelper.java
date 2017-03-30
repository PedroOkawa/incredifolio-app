package com.daltonicchameleon.portfolio.util.helper;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * portfolio-context
 * Created in 3/24/17 by the following authors:
 * Pedro Okawa
 */
public class TextHelper {

    private Context context;

    public TextHelper(Context context) {
        this.context = context;
    }

    public String convertString(@StringRes int stringId) {
        return context.getString(stringId);
    }

}
