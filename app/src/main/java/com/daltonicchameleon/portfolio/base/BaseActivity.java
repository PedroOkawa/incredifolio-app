package com.daltonicchameleon.portfolio.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.daltonicchameleon.portfolio.App;
import com.daltonicchameleon.portfolio.di.app.AppComponent;
import com.daltonicchameleon.portfolio.util.constants.Constants;

import java.util.HashSet;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    private T dataBinding;
//    private Set<Disposable> disposables = new HashSet<>();

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().getExtras() != null) {
            initializesArguments(getIntent().getExtras());
        }

        initializesDataBinding();
        initializesComponents(getApp().getAppComponent());
        doOnCreated(savedInstanceState);
        attachFragment();
    }

    @Override
    protected void onDestroy() {
        cancelAllRequests();
        dataBinding.unbind();
        super.onDestroy();
    }

    /**
     * Returns layout to inflate from the extended activity
     *
     * @return layoutId
     */
    protected abstract @LayoutRes int layoutToInflate();

    /**
     * Called when all features are initialized and loaded (databinding and Dagger components)
     *
     * @param savedInstanceState
     */
    protected abstract void doOnCreated(@Nullable Bundle savedInstanceState);

    /**
     * Initializes activity's arguments
     *
     * @param bundle
     */
    protected void initializesArguments(Bundle bundle) {

    }

    /**
     * Used to initializes extended activity components
     *
     * @param appComponent
     */
    protected void initializesComponents(AppComponent appComponent) {

    }

    /**
     * Initializes databinding instance based on extended activity layout (layoutToInflate)
     */
    private void initializesDataBinding() {
        this.dataBinding = DataBindingUtil.setContentView(this, layoutToInflate());
    }

    /**
     * Returns Activity data binding
     *
     * @return dataBinding
     */
    public T getDataBinding() {
        return dataBinding;
    }

    /**
     * Attaches the first fragment
     */
    private void attachFragment() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0 && getIntent().hasExtra(Constants.BUNDLE_FRAGMENT)) {
            performTransaction(getInitialFragment());
        }
    }

    /**
     * Executes transaction to replace fragment
     *
     * @param fragment
     */
    public final void performTransaction(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(contentViewId(), fragment);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    /**
     * Gets initial fragment if exists
     *
     * @return fragment
     */
    @Nullable
    private Fragment getInitialFragment() {
        try {
            Fragment fragment = (Fragment) Class.forName(getIntent().getStringExtra(Constants.BUNDLE_FRAGMENT)).newInstance();

            if(getIntent().getExtras() != null) {
                fragment.setArguments(getIntent().getExtras());
            }

            return fragment;
        } catch (Exception e) {
            Log.e(BaseActivity.class.getCanonicalName(), e.getMessage());
        }

        return null;
    }

    /**
     * Returns custom App instance to retrieve AppComponent
     *
     * @return app
     */
    public final App getApp() {
        return App.class.cast(getApplication());
    }

    /**
     * Returns content layout to inflate fragments
     *
     * @return content layout
     */
    public @IdRes int contentViewId() {
        return 0;
    }

    /**
     * Attaches a request into the activity and cancels it when the fragment is destroyed
     *
     * @param disposable
     */
//    public void addRequestDisposable(Disposable disposable) {
//        disposables.add(disposable);
//    }

    /**
     * Cancels all requests
     */
    private void cancelAllRequests() {
//        for(Disposable disposable : disposables) {
//            if(!disposable.isDisposed()) {
//                disposable.dispose();
//            }
//        }
//
//        disposables.clear();
    }
}
