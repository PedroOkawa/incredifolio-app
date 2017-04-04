package com.daltonicchameleon.portfolio.util.manager;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * portfolio-context
 * Created in 3/23/17 by the following authors:
 * Pedro Okawa
 */
public class FeedbackManager {

    private Context context;

    public FeedbackManager(Context context) {
        this.context = context;
    }

    /**
     * Shows a toast with the following message (String)
     *
     * @param message
     */
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a toast with the following message (StringRes)
     *
     * @param message
     */
    public void showToast(@StringRes int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a snackbar with the following message (String)
     *
     * @param view
     * @param message
     */
    public void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Shows a snackbar with the following message (StringRes)
     *
     * @param view
     * @param message
     */
    public void showSnackBar(View view, @StringRes int message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

}
