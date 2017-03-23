package com.daltonicchameleon.incredifolio.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.daltonicchameleon.incredifolio.di.app.AppComponent;
import com.daltonicchameleon.incredifolio.util.constants.Constants;

/**
 * portfolio-app
 * Created in 3/23/17 by the following authors:
 * Pedro Okawa
 */
public abstract class BaseDialog<T extends ViewDataBinding> extends DialogFragment {

    private T dataBinding;

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null) {
            initializesArguments(getArguments());
        }

        this.dataBinding = DataBindingUtil.inflate(inflater, layoutToInflate(), null, false);

        initializesComponents(getBaseActivity().getApp().getAppComponent());

        doOnCreated(savedInstanceState);
        return dataBinding.getRoot();
    }

    @NonNull
    @Override
    public final Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public final void onResume() {
        super.onResume();

        Display display = getDialog().getWindow().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getDialog().getWindow().getAttributes());
        layoutParams.width = (int) (size.x * Constants.BASE_DIALOG_WIDTH_PERCENTAGE);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(layoutParams);
    }

    @Override
    public final void onDestroyView() {
        dataBinding.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    /**
     * Returns layout to inflate from the extended dialogt
     *
     * @return layoutId
     */
    protected abstract @LayoutRes int layoutToInflate();

    /**
     * Initializes dialog's arguments
     *
     * @param bundle
     */
    protected abstract void initializesArguments(Bundle bundle);

    /**
     * Used to initializes extended dialog components
     *
     * @param appComponent
     */
    protected abstract void initializesComponents(AppComponent appComponent);

    /**
     * Called when all features are initialized and loaded (databinding and Dagger components)
     *
     * @param savedInstanceState
     */
    protected abstract void doOnCreated(@Nullable Bundle savedInstanceState);

    /**
     * Returns Dialog data binding
     *
     * @return dataBinding
     */
    public T getDataBinding() {
        return dataBinding;
    }

    /**
     * Returns base activity that holds the dialog
     *
     * @return baseActivity
     */
    public BaseActivity getBaseActivity() {
        return BaseActivity.class.cast(getActivity());
    }
}

