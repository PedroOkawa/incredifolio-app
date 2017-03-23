package com.daltonicchameleon.incredifolio.util.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.daltonicchameleon.incredifolio.App;
import com.daltonicchameleon.incredifolio.base.BaseActivity;
import com.daltonicchameleon.incredifolio.base.BaseDialog;
import com.daltonicchameleon.incredifolio.base.BaseFragment;
import com.daltonicchameleon.incredifolio.util.constants.Constants;

/**
 * portfolio-app
 * Created in 3/23/17 by the following authors:
 * Pedro Okawa
 */
public class CallManager {

    private App app;

    public CallManager(App app) {
        this.app = app;
    }

    /**
     * Opens an activity with the given fragment, extras and request code (Used only if it's necessary to start an baseActivity for result)
     *
     * @param activity
     * @param activityClazz
     * @param fragmentClazz
     * @param bundle
     * @param requestCode
     * @param flagType
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Class<? extends BaseFragment> fragmentClazz, Bundle bundle, Integer requestCode, int flagType) {
        if(activity == null) {
            throw new NullPointerException();
        }

        Intent intent = new Intent(activity, activityClazz);

        if(flagType == Constants.CALL_MANAGER_FLAG_TYPE_NEW_TASK) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        if(fragmentClazz != null) {
            intent.putExtra(Constants.BUNDLE_FRAGMENT, fragmentClazz.getCanonicalName());
        }

        if(bundle != null) {
            intent.putExtras(bundle);
        }

        if(requestCode != null) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivity(intent);
        }
    }

    /**
     * Opens an activity
     *
     * @param activity
     * @param activityClazz
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz) {
        open(activity, activityClazz, null, null, null, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }

    /**
     * Opens an activity in a new task (removing previous opened activities)
     *
     * @param activity
     * @param activityClazz
     * @param flagType
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, int flagType) {
        open(activity, activityClazz, null, null, null, flagType);
    }

    /**
     * Opens an activity with the given extras
     *
     * @param activity
     * @param activityClazz
     * @param bundle
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Bundle bundle) {
        open(activity, activityClazz, null, bundle, null, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }

    /**
     * Opens an activity with the given extras
     *
     * @param activity
     * @param activityClazz
     * @param bundle
     * @param flagType
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Bundle bundle, int flagType) {
        open(activity, activityClazz, null, bundle, null, flagType);
    }

    /**
     * Opens an activity for result with the given request code
     *
     * @param activity
     * @param activityClazz
     * @param requestCode
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Integer requestCode) {
        open(activity, activityClazz, null, null, requestCode, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }


    /**
     * Opens an activity for result with the given request code and extras
     *
     * @param activity
     * @param activityClazz
     * @param bundle
     * @param requestCode
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Bundle bundle, Integer requestCode) {
        open(activity, activityClazz, null, bundle, requestCode, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }

    /**
     * Opens an activity with the given fragment without any extra
     *
     * @param activity
     * @param activityClazz
     * @param fragmentClazz
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Class<? extends BaseFragment> fragmentClazz) {
        open(activity, activityClazz, fragmentClazz, null, null, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }

    /**
     * Opens an activity with the given fragment without any extra and a flag
     *
     * @param activity
     * @param activityClazz
     * @param fragmentClazz
     * @param flagType
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Class<? extends BaseFragment> fragmentClazz, int flagType) {
        open(activity, activityClazz, fragmentClazz, null, null, flagType);
    }

    /**
     * Opens an activity with the given fragment and extras
     *
     * @param activity
     * @param activityClazz
     * @param fragmentClazz
     * @param bundle
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Class<? extends BaseFragment> fragmentClazz, Bundle bundle) {
        open(activity, activityClazz, fragmentClazz, bundle, null, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }

    /**
     * Opens an activity with the given fragment and extras
     *
     * @param activity
     * @param activityClazz
     * @param fragmentClazz
     * @param bundle
     * @param flagType
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Class<? extends BaseFragment> fragmentClazz, Bundle bundle, int flagType) {
        open(activity, activityClazz, fragmentClazz, bundle, null, flagType);
    }

    /**
     * Opens an activity for result with the given fragment
     *
     * @param activity
     * @param activityClazz
     * @param fragmentClazz
     * @param requestCode
     */
    public void open(Activity activity, Class<? extends BaseActivity> activityClazz, Class<? extends BaseFragment> fragmentClazz, Integer requestCode) {
        open(activity, activityClazz, fragmentClazz, null, requestCode, Constants.CALL_MANAGER_FLAG_TYPE_NONE);
    }

    /**
     * Adds a fragment into baseActivity's container with the given parameters
     *
     * @param baseActivity
     * @param fragmentClazz
     * @param bundle
     */
    public void add(BaseActivity baseActivity, Class<? extends BaseFragment> fragmentClazz, Bundle bundle) {
        if(baseActivity == null) {
            throw new NullPointerException();
        }

        FragmentManager fragmentManager = baseActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        try {
            BaseFragment fragment = fragmentClazz.newInstance();

            if(bundle != null) {
                fragment.setArguments(bundle);
            }

            fragmentTransaction.addToBackStack(null);
            if (fragmentManager.findFragmentById(baseActivity.contentViewId()) != null) {
                fragmentTransaction.hide(fragmentManager.findFragmentById(baseActivity.contentViewId()));
            }

            fragmentTransaction.add(baseActivity.contentViewId(), fragment);
            fragmentTransaction.commit();
        } catch (Exception e) {
            Log.e(CallManager.class.getCanonicalName(), e.getMessage());
        }
    }

    /**
     * Adds a fragment into baseActivity's container without extras
     *
     * @param baseActivity
     * @param fragmentClazz
     */
    public void add(BaseActivity baseActivity, Class<? extends BaseFragment> fragmentClazz) {
        add(baseActivity, fragmentClazz, null);
    }

    /**
     * Opens the dialog with the given arguments
     *
     * @param fragmentManager
     * @param dialogClazz
     * @param bundle
     */
    public void openDialog(FragmentManager fragmentManager, Class<? extends BaseDialog> dialogClazz, Bundle bundle) {
        try {
            BaseDialog baseDialog = dialogClazz.newInstance();
            if(bundle != null) {
                baseDialog.setArguments(bundle);
            }
            baseDialog.show(fragmentManager, baseDialog.getClass().getCanonicalName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the dialog without any arguments
     *
     * @param fragmentManager
     * @param dialogClazz
     */
    public void openDialog(FragmentManager fragmentManager, Class<? extends BaseDialog> dialogClazz) {
        openDialog(fragmentManager, dialogClazz, null);
    }

    /**
     * Clears fragments back stack
     */
    public void clearBackStack(BaseActivity baseActivity) {
        if(baseActivity == null) {
            throw new NullPointerException();
        }

        FragmentManager fragmentManager = baseActivity.getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Restarts the app
     */
//    public void restartApp() {
//        Intent intent = new Intent(app, FullScreenActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.putExtra(Constants.BUNDLE_FRAGMENT, LoginFragment.class.getCanonicalName());
//        app.startActivity(intent);
//    }

}

