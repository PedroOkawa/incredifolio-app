package com.daltonicchameleon.portfolio.util.helper;

import android.support.annotation.IdRes;
import android.widget.EditText;
import android.widget.TextView;

import com.daltonicchameleon.portfolio.util.constants.Constants;

/**
 * portfolio-app
 * Created in 4/4/17 by the following authors:
 * Pedro Okawa
 */
public class FormHelper {

    /**
     * Checks if all provided text views are not empty
     *
     * @param editTexts
     * @return the view id
     */
    public @IdRes int checkIfValuesAreFilled(EditText ... editTexts) {
        for(TextView textView : editTexts) {
            if(textView.getText().toString().isEmpty()) {
                return textView.getId();
            }
        }
        return Constants.UNDEFINED_ID;
    }

    /**
     * Checks if all provided text views values matches
     *
     * @param editTexts
     * @return true in case all views values matches, otherwise returns false
     */
    public boolean checkIfValuesMatches(EditText ... editTexts) {
        for(int i = 1; i < editTexts.length; i++) {
            String previousValue = editTexts[i - 1].getText().toString();
            String nextValue = editTexts[i].getText().toString();

            if(!previousValue.equals(nextValue)) {
                return false;
            }
        }
        return true;
    }

}
