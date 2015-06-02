package com.bubbinator91.converter.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
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
import com.bubbinator91.converter.util.Globals;

import timber.log.Timber;

/**
 * The base fragment that all fragments should inherit from.
 * The purpose of this fragment to help reduce the amount of
 * duplicated code.
 */
public abstract class BaseFragment
        extends Fragment
        implements ViewTreeObserver.OnScrollChangedListener {
    private final String TAG = "BaseFragment";

    private Activity mActivity = null;
    private Handler mHandler = null;
    private SharedPreferences mPrefs = null;
    private int numOfDecimalPlaces = -1, lastY = 0;

    private View rootView = null;
    private ScrollView mScrollView = null;
    private int mToolbarHeight = 0;
    private int mToolbarOffset = 0;

    // region Lifecycle methods

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Timber.tag(TAG + "." + getChildTag() + ".onAttach").i("Entered");
        mActivity = activity;
    }

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

        mHandler = getCurrentActivity().getWindow().getDecorView().getHandler();

        mPrefs = PreferenceManager.getDefaultSharedPreferences(mActivity.getApplicationContext());

        TypedArray actionBarAttrs = getActivity()
                .obtainStyledAttributes(new int[] {
                        android.R.attr.actionBarSize
                });
        mToolbarHeight = ((int) actionBarAttrs.getDimension(0, 0) + 10);
        actionBarAttrs.recycle();

        mScrollView = ((ScrollView) rootView.findViewById(getScrollViewResource()));
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + "." + getChildTag() + ".onResume").i("Entered");

        if (mPrefs == null) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        }
        if (mPrefs != null) {
            numOfDecimalPlaces =
                    Integer.parseInt(mPrefs.getString(Globals.PREFERENCE_DECIMAL_PLACES, "-1"));
        }
        if (numOfDecimalPlaces == -1) {
            numOfDecimalPlaces = 8;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Timber.tag(TAG + "." + getChildTag() + ".onPause").i("Entered");

        Toolbar toolbar = ((Toolbar) mActivity.findViewById(R.id.toolbar));
        if (toolbar != null) {
            // Animate toolbar down if hidden
            while (mToolbarOffset > 0) {
                toolbar.animate().y(-mToolbarOffset);
                --mToolbarOffset;
            }
        }
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
            int dy = (mScrollView.getScrollY() - lastY);

            // Make sure that the toolbar isn't in some weird position
            if (mToolbarOffset > mToolbarHeight) {
                mToolbarOffset = mToolbarHeight;
            } else if (mToolbarOffset < 0) {
                mToolbarOffset = 0;
            }

            Toolbar toolbar = ((Toolbar) mActivity.findViewById(R.id.toolbar));
            try {
                // If the user is dragging the ScrollView up (they are heading towards the bottom of
                // the ScrollView) and if the current scrolling position of the ScrollView is
                // greater than the height of the toolbar minus 20, animate the toolbar off the screen. This
                // prevents the toolbar from being animated off the screen until the top of the
                // ScrollView is almost touching the status bar. The minus 20 makes sure that, for
                // fragments that have 3 conversion options or more, when the third conversion
                // textbox is selected and the keyboard appears, the toolbar will hide so that the
                // first 2 conversions remain on screen.
                if ((dy > 0) && (mScrollView.getScrollY() > (mToolbarHeight - 20))) {
                    if (toolbar != null) {
                        while (mToolbarOffset < mToolbarHeight) {
                            toolbar.animate().y(-mToolbarOffset);
                            ++mToolbarOffset;
                        }
                    }
                }
                // If the user is dragging the ScrollView down (they are heading towards the top of
                // the ScrollView) and if the current scrolling position of the ScrollView is
                // greater than the height of the toolbar, check the speed of the user's scrolling.
                // This allows the user to scroll slow enough to keep the toolbar off the screen if
                // they want to.
                else if ((dy < 0) && (mScrollView.getScrollY() > mToolbarHeight)) {
                    // If the user has scrolled more than 5 units, animate the toolbar on to the
                    // screen
                    if ((dy < -5) && (toolbar != null)) {
                        while (mToolbarOffset > 0) {
                            toolbar.animate().y(-mToolbarOffset);
                            --mToolbarOffset;
                        }
                    }
                }
                // If the user is dragging the ScrollView down (they are heading towards the top of
                // the ScrollView) and if the current scrolling position of the ScrollView is
                // less than the height of the toolbar, animate the toolbar on to the screen. This
                // forcibly animates the toolbar back onto the screen when the top of the ScrollView
                // is just below the bottom of the status bar.
                else if ((dy < 0) && (mScrollView.getScrollY() < mToolbarHeight)) {
                    if (toolbar != null) {
                        while (mToolbarOffset > 0) {
                            toolbar.animate().y(-mToolbarOffset);
                            --mToolbarOffset;
                        }
                    }
                }
            } catch (NullPointerException e) {
                Timber.tag(TAG + "." + getChildTag() + ".onScrollChanged")
                        .e("Toolbar is null\n" + e.toString());
            }

            // Store the last position of the ScrollView for later use.
            lastY = mScrollView.getScrollY();
        }
    }

    /**
     * Gets the instance of the parent {@link Activity}.
     *
     * @return  The instance of the parent {@link Activity}.
     */
    protected Activity getCurrentActivity() { return mActivity; }

    /**
     * Gets the number of decimal places to round to.
     *
     * @return  The number of decimal places to round to.
     */
    protected int getNumOfDecimalPlaces() { return numOfDecimalPlaces; }

    /**
     * Gets the {@link Handler} obtained from the parent Activity.
     *
     * @return  A {@link Handler} object obtained from the parent Activity.
     */
    protected Handler getHandler() { return mHandler; }

    /**
     * Gets the root view that was inflated in {@link #onCreate(Bundle)}.
     *
     * @return  The root view that was inflated in {@link #onCreate(Bundle)}.
     */
    protected View getRootView() { return rootView; }

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

    // endregion
}
