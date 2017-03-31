package com.daltonicchameleon.portfolio.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daltonicchameleon.portfolio.di.app.AppComponent;

/**
 * portfolio-app
 * Created in 3/20/17 by the following authors:
 * Pedro Okawa
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    private T dataBinding;
//    private Set<Disposable> disposables = new HashSet<>();

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            initializesArguments(getArguments());
        }

        initializesDataBinding(inflater);
        initializesComponents(getBaseActivity().getApp().getAppComponent());
        doOnCreated(savedInstanceState);
        return dataBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        dispose();
        dataBinding.unbind();
        super.onDestroyView();
    }

    /**
     * Returns layout to inflate from the extended fragment
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
     * Dispose fragment
     */
    protected abstract void dispose();

    /**
     * Initializes fragment's arguments
     *
     * @param bundle
     */
    protected void initializesArguments(Bundle bundle) {

    }

    /**
     * Used to initializes extended fragment components
     *
     * @param appComponent
     */
    protected void initializesComponents(AppComponent appComponent) {

    }

    /**
     * Initializes databinding instance based on extended fragment layout (layoutToInflate)
     */
    private void initializesDataBinding(LayoutInflater layoutInflater) {
        dataBinding = DataBindingUtil.inflate(layoutInflater, layoutToInflate(), null, false);
    }

    /**
     * Returns fragment data binding
     *
     * @return dataBinding
     */
    public T getDataBinding() {
        return dataBinding;
    }

    /**
     * Returns base activity that holds the fragment
     *
     * @return baseActivity
     */
    public final BaseActivity getBaseActivity() {
        return BaseActivity.class.cast(getActivity());
    }

    /**
     * Attaches a request into the fragment and cancels it when the fragment is destroyed
     *
     * @param disposable
     */
//    public void addRequestDisposable(Disposable disposable) {
//        disposables.add(disposable);
//    }
}