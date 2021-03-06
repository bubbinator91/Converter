package com.bubbinator91.converter.ui.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import com.bubbinator91.converter.BuildConfig;
import com.bubbinator91.converter.R;
import com.bubbinator91.converter.interfaces.base.IConverterPresenter;
import com.bubbinator91.converter.util.Globals;
import com.bubbinator91.converter.util.PresenterCache;

import timber.log.Timber;

/**
 * The base fragment that all fragments should inherit from.
 * The purpose of this fragment to help reduce the amount of
 * duplicated code.
 */
public abstract class BaseFragment<T extends IConverterPresenter>
        extends Fragment
        implements ViewTreeObserver.OnScrollChangedListener {
    private final String TAG = BaseFragment.class.getSimpleName();

    private static SharedPreferences sharedPreferences = null;

    private View rootView = null;
    private ScrollView requiredScrollView = null;
    private int toolbarHeight = 0, toolbarOffset = 0, lastY = 0, numOfDecimalPlaces = -1;
    private int lastEditTextFocused;
    private boolean wasCreated = true;

    // region Lifecycle methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Timber.tag(TAG + "." + getChildTag() + ".onCreateView").i("Entered");
        rootView = inflater.inflate(getLayoutResource(), container, false);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        TypedArray actionBarAttrs = getActivity()
                .obtainStyledAttributes(new int[] {
                        android.R.attr.actionBarSize
                });
        toolbarHeight = ((int) actionBarAttrs.getDimension(0, 0) + 10);
        actionBarAttrs.recycle();

        requiredScrollView = ((ScrollView) rootView.findViewById(getScrollViewResource()));
        requiredScrollView.getViewTreeObserver().addOnScrollChangedListener(this);

        wasCreated = true;

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + "." + getChildTag() + ".onResume").i("Entered");

        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        }
        if (sharedPreferences != null) {
            numOfDecimalPlaces =
                    Integer.parseInt(sharedPreferences.getString(Globals.PREFERENCE_DECIMAL_PLACES, "-1"));
        }
        if (numOfDecimalPlaces == -1) {
            numOfDecimalPlaces = 8;
        }

        registerViewWithPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG + "." + getChildTag() + ".onPause").i("Entered");

        Toolbar toolbar = ((Toolbar) getActivity().findViewById(R.id.toolbar));
        if (toolbar != null) {
            // Animate toolbar down if hidden
            while (toolbarOffset > 0) {
                toolbar.animate().y(-toolbarOffset);
                --toolbarOffset;
            }
        }

        wasCreated = false;

        unregisterViewWithPresenter();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        PresenterCache.getInstance().savePresenter(getPresenter(), outState);
    }

    // endregion

    // region Helper methods

    /**
     * Allows the observing of the ScrollView so that the toolbar can be hidden or shown.
     */
    @Override
    public void onScrollChanged() {
        // Checks to make sure that the fragment is added to an activity
        if (isAdded()) {
            // Get the difference in scroll positions between the current call to onScrollChanged()
            // and the last call to onScrollChanged()
            int dy = (requiredScrollView.getScrollY() - lastY);

            // Make sure that the toolbar isn't in some weird position
            if (toolbarOffset > toolbarHeight) {
                toolbarOffset = toolbarHeight;
            } else if (toolbarOffset < 0) {
                toolbarOffset = 0;
            }

            // TODO Good idea to implement dagger here
            Toolbar toolbar = ((Toolbar) getActivity().findViewById(R.id.toolbar));
            try {
                // If the user is dragging the ScrollView up (they are heading towards the bottom of
                // the ScrollView) and if the current scrolling position of the ScrollView is
                // greater than the height of the toolbar minus 20, animate the toolbar off the screen. This
                // prevents the toolbar from being animated off the screen until the top of the
                // ScrollView is almost touching the status bar. The minus 20 makes sure that, for
                // fragments that have 3 conversion options or more, when the third conversion
                // textbox is selected and the keyboard appears, the toolbar will hide so that the
                // first 2 conversions remain on screen.
                if ((dy > 0) && (requiredScrollView.getScrollY() > (toolbarHeight - 20))) {
                    if (toolbar != null) {
                        while (toolbarOffset < toolbarHeight) {
                            toolbar.animate().y(-toolbarOffset);
                            ++toolbarOffset;
                        }
                    }
                }
                // If the user is dragging the ScrollView down (they are heading towards the top of
                // the ScrollView) and if the current scrolling position of the ScrollView is
                // greater than the height of the toolbar, check the speed of the user's scrolling.
                // This allows the user to scroll slow enough to keep the toolbar off the screen if
                // they want to.
                else if ((dy < 0) && (requiredScrollView.getScrollY() > toolbarHeight)) {
                    // If the user has scrolled more than 5 units, animate the toolbar on to the
                    // screen
                    if ((dy < -5) && (toolbar != null)) {
                        while (toolbarOffset > 0) {
                            toolbar.animate().y(-toolbarOffset);
                            --toolbarOffset;
                        }
                    }
                }
                // If the user is dragging the ScrollView down (they are heading towards the top of
                // the ScrollView) and if the current scrolling position of the ScrollView is
                // less than the height of the toolbar, animate the toolbar on to the screen. This
                // forcibly animates the toolbar back onto the screen when the top of the ScrollView
                // is just below the bottom of the status bar.
                else if ((dy < 0) && (requiredScrollView.getScrollY() < toolbarHeight)) {
                    if (toolbar != null) {
                        while (toolbarOffset > 0) {
                            toolbar.animate().y(-toolbarOffset);
                            --toolbarOffset;
                        }
                    }
                }
            } catch (NullPointerException e) {
                Timber.tag(TAG + "." + getChildTag() + ".onScrollChanged")
                        .e("Toolbar is null\n" + e.toString());
            }

            // Store the last position of the ScrollView for later use.
            lastY = requiredScrollView.getScrollY();
        }
    }

    /**
     * Gets the number of decimal places to round to.
     *
     * @return  The number of decimal places to round to.
     */
    protected int getNumOfDecimalPlaces() {
        return numOfDecimalPlaces;
    }

    /**
     * Gets the integer value of the last EditText focused.
     *
     * @return  The integer value of the last EditText focused.
     */
    protected int getLastEditTextFocused() {
        return lastEditTextFocused;
    }

    /**
     * Gets the root view that was inflated in {@link #onCreate(Bundle)}.
     *
     * @return  The root view that was inflated in {@link #onCreate(Bundle)}.
     */
    protected View getRootView() { return rootView; }

    /**
     * Gets the {@link SharedPreferences} object for use in any child class.
     *
     * @return  A {@link SharedPreferences} object.
     */
    protected SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager
                    .getDefaultSharedPreferences(getActivity().getApplicationContext());
        }
        
        return sharedPreferences;
    }

    /**
     * Sets the internal value of the last focused EditText.
     */
    protected void setLastEditTextFocused(int lastEditTextFocused) {
        this.lastEditTextFocused = lastEditTextFocused;
    }

    /**
     * Tells the presenter that the view needs to be unregistered.
     */
    protected void unregisterViewWithPresenter() {
        getPresenter().unregisterView();
    }

    /**
     * Gets a value indicating whether or not the fragment was resumed or fully created.
     *
     * @return  True if fragment was just resumed; false if it was fully created.
     */
    protected boolean wasResumed() {
        return !wasCreated;
    }

    // endregion

    // region Abstract methods

    /**
     * An abstract method that needs to be overridden for proper logging. This method is used in any
     * logging that comes from {@link BaseFragment} itself.
     *
     * @return  The overridden method should return the logging tag of the class that extends from
     *          {@link BaseFragment}.
     */
    protected abstract String getChildTag();

    /**
     * An abstract method that needs to be overridden for proper layout inflation. This method is
     * used in {@link #onCreate(Bundle)} of {@link BaseFragment} to inflate the layout of the
     * {@link Fragment} that extends from {@link BaseFragment}.
     *
     * @return  The overridden method should return the resource id of the layout to be inflated.
     */
    protected abstract int getLayoutResource();

    /**
     * An abstract method that needs to be overridden for proper manipulation of the toolbar. This
     * method is used in {@link #onCreate(Bundle)} of {@link BaseFragment} to retrieve the
     * {@link ScrollView}. This {@link ScrollView} is then used in {@link #onScrollChanged()} to
     * figure out if the toolbar needs to be hidden or not.
     *
     * @return  The overridden method should return the resource id of the {@link ScrollView} object
     *          that should be the root of the layout for the {@link Fragment} that extends from
     *          {@link BaseFragment}.
     */
    protected abstract int getScrollViewResource();

    /**
     * An abstract method that needs to be overridden for proper initialization of a Presenter in
     * the child fragment.
     *
     * @return  An injected Presenter object.
     */
    protected abstract T getPresenter();

    /**
     * An abstract method that needs to be overridden so that the Presenter in the child class has
     * a link to the View (the activity) so that it can notify when it needs to update.
     */
    protected abstract void registerViewWithPresenter();

    //protected abstract void unregisterViewWithPresenter();

    // endregion
}
