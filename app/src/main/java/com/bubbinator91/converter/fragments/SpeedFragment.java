package com.bubbinator91.converter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.conversion.speed.Fps;
import com.bubbinator91.converter.conversion.speed.Knot;
import com.bubbinator91.converter.conversion.speed.Kph;
import com.bubbinator91.converter.conversion.speed.Mph;
import com.bubbinator91.converter.conversion.speed.Mps;
import com.bubbinator91.converter.util.Utils;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * mph, ft/s, m/s, kph, knot
 * Conversions comply with the conversions through Google.com
 */

public class SpeedFragment extends BaseFragment {
    private enum LastEditTextFocused {
        FPS,
        KNOT,
        KPH,
        MPS,
        MPH
    }

    private final String TAG = "FragmentSpeed";

    private EditText editTextFps, editTextKnot, editTextKph, editTextMps, editTextMph;

    private LastEditTextFocused lastEditTextFocused;

    // region TextWatchers

    private TextWatcher textWatcherFps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.FPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherFps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherFps");
                new Thread(new ConversionFromFpsRunnable(s, "textWatcherFps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKnot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.KNOT;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherKnot");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherKnot");
                new Thread(new ConversionFromKnotRunnable(s, "textWatcherKnot")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKph = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.KPH;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherKph");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherKph");
                new Thread(new ConversionFromKphRunnable(s, "textWatcherKph")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.MPS;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherMps");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMps");
                new Thread(new ConversionFromMpsRunnable(s, "textWatcherMps")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMph = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            lastEditTextFocused = LastEditTextFocused.MPH;

            if ((getHandler() != null) && (s != null)) {
                removeTextChangedListeners("textWatcherMph");
                Utils.sanitizeEditable(s);
                addTextChangedListeners("textWatcherMph");
                new Thread(new ConversionFromMphRunnable(s, "textWatcherMph")).start();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    // endregion

    // region Lifecycle methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Timber.tag(TAG + ".onCreateView").i("Entered");

        setShouldHideToolbarOnScroll(false);

        if (getRootView() != null) {
            TextView textViewFps =
                    ((TextView) getRootView().findViewById(R.id.textView_speed_fps));
            TextView textViewKnot =
                    ((TextView) getRootView().findViewById(R.id.textView_speed_knot));
            TextView textViewKph =
                    ((TextView) getRootView().findViewById(R.id.textView_speed_kph));
            TextView textViewMps =
                    ((TextView) getRootView().findViewById(R.id.textView_speed_mps));
            TextView textViewMph =
                    ((TextView) getRootView().findViewById(R.id.textView_speed_mph));

            if (getTypeFace() != null) {
                textViewFps.setTypeface(getTypeFace());
                textViewKnot.setTypeface(getTypeFace());
                textViewKph.setTypeface(getTypeFace());
                textViewMps.setTypeface(getTypeFace());
                textViewMph.setTypeface(getTypeFace());
            }

            editTextFps =
                    ((EditText) getRootView().findViewById(R.id.editText_speed_fps));
            editTextKnot =
                    ((EditText) getRootView().findViewById(R.id.editText_speed_knot));
            editTextKph =
                    ((EditText) getRootView().findViewById(R.id.editText_speed_kph));
            editTextMps =
                    ((EditText) getRootView().findViewById(R.id.editText_speed_mps));
            editTextMph =
                    ((EditText) getRootView().findViewById(R.id.editText_speed_mph));

            addTextChangedListeners(null);
        }

        return getRootView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.tag(TAG + ".onResume").i("Entered");

        if (lastEditTextFocused == LastEditTextFocused.FPS) {
            if ((getHandler() != null) && (editTextFps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextFps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromFpsRunnable(editTextFps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KNOT) {
            if ((getHandler() != null) && (editTextKnot.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKnot.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKnotRunnable(editTextKnot.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.KPH) {
            if ((getHandler() != null) && (editTextKph.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextKph.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromKphRunnable(editTextKph.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MPS) {
            if ((getHandler() != null) && (editTextMps.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMps.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMpsRunnable(editTextMps.getText(),
                        "onResume")).start();
            }
        } else if (lastEditTextFocused == LastEditTextFocused.MPH) {
            if ((getHandler() != null) && (editTextMph.getText() != null)) {
                removeTextChangedListeners("onResume");
                Utils.sanitizeEditable(editTextMph.getText());
                addTextChangedListeners("onResume");
                new Thread(new ConversionFromMphRunnable(editTextMph.getText(),
                        "onResume")).start();
            }
        }
    }

    // endregion

    // region Helper methods

    private void addTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".addTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".addTextChangedListeners").i("Entered");
        }

        editTextFps.addTextChangedListener(textWatcherFps);
        editTextKnot.addTextChangedListener(textWatcherKnot);
        editTextKph.addTextChangedListener(textWatcherKph);
        editTextMps.addTextChangedListener(textWatcherMps);
        editTextMph.addTextChangedListener(textWatcherMph);
    }

    private void removeTextChangedListeners(String callingClassName) {
        if (callingClassName != null) {
            Timber.tag(TAG + "." + callingClassName + ".removeTextChangedListeners").i("Entered");
        } else {
            Timber.tag(TAG + ".removeTextChangedListeners").i("Entered");
        }

        editTextFps.removeTextChangedListener(textWatcherFps);
        editTextKnot.removeTextChangedListener(textWatcherKnot);
        editTextKph.removeTextChangedListener(textWatcherKph);
        editTextMps.removeTextChangedListener(textWatcherMps);
        editTextMph.removeTextChangedListener(textWatcherMph);
    }

    // endregion

    // region Overridden BaseFragment methods

    @Override
    protected String getChildTag() { return TAG; }

    @Override
    protected int getLayoutResource() { return R.layout.fragment_speed; }

    @Override
    protected int getScrollViewResource() { return  R.id.fragment_speed; }

    // endregion

    // region Private classes

    private class ConversionFromFpsRunnable implements Runnable {
        private final String TAG = "ConversionFromFpsRunnable";

        private Editable mEditableFps;
        private String mCallingClassName;

        public ConversionFromFpsRunnable(@NonNull Editable editableFps,
                                         @NonNull String callingClassName) {
            mEditableFps = editableFps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableFps != null) {
                final ArrayList<String> results =
                        Fps.toAll(mEditableFps.toString(), getFieldLength());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextKnot.setText(results.get(0), TextView.BufferType.EDITABLE);
                        editTextKph.setText(results.get(1), TextView.BufferType.EDITABLE);
                        editTextMps.setText(results.get(2), TextView.BufferType.EDITABLE);
                        editTextMph.setText(results.get(3), TextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromKnotRunnable implements Runnable {
        private final String TAG = "ConversionFromKnotRunnable";

        private Editable mEditableKnot;
        private String mCallingClassName;

        public ConversionFromKnotRunnable(@NonNull Editable editableKnot,
                                          @NonNull String callingClassName) {
            mEditableKnot = editableKnot;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKnot != null) {
                final ArrayList<String> results =
                        Knot.toAll(mEditableKnot.toString(), getFieldLength());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextFps.setText(results.get(0), TextView.BufferType.EDITABLE);
                        editTextKph.setText(results.get(1), TextView.BufferType.EDITABLE);
                        editTextMps.setText(results.get(2), TextView.BufferType.EDITABLE);
                        editTextMph.setText(results.get(3), TextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromKphRunnable implements Runnable {
        private final String TAG = "ConversionFromKphRunnable";

        private Editable mEditableKph;
        private String mCallingClassName;

        public ConversionFromKphRunnable(@NonNull Editable editableKph,
                                         @NonNull String callingClassName) {
            mEditableKph = editableKph;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableKph != null) {
                final ArrayList<String> results =
                        Kph.toAll(mEditableKph.toString(), getFieldLength());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextFps.setText(results.get(0), TextView.BufferType.EDITABLE);
                        editTextKnot.setText(results.get(1), TextView.BufferType.EDITABLE);
                        editTextMps.setText(results.get(2), TextView.BufferType.EDITABLE);
                        editTextMph.setText(results.get(3), TextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMpsRunnable implements Runnable {
        private final String TAG = "ConversionFromMpsRunnable";

        private Editable mEditableMps;
        private String mCallingClassName;

        public ConversionFromMpsRunnable(@NonNull Editable editableMps,
                                         @NonNull String callingClassName) {
            mEditableMps = editableMps;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMps != null) {
                final ArrayList<String> results =
                        Mps.toAll(mEditableMps.toString(), getFieldLength());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextFps.setText(results.get(0), TextView.BufferType.EDITABLE);
                        editTextKnot.setText(results.get(1), TextView.BufferType.EDITABLE);
                        editTextKph.setText(results.get(2), TextView.BufferType.EDITABLE);
                        editTextMph.setText(results.get(3), TextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    private class ConversionFromMphRunnable implements Runnable {
        private final String TAG = "ConversionFromMphRunnable";

        private Editable mEditableMph;
        private String mCallingClassName;

        public ConversionFromMphRunnable(@NonNull Editable editableMph,
                                         @NonNull String callingClassName) {
            mEditableMph = editableMph;
            mCallingClassName = callingClassName;
        }

        @Override
        public void run() {
            Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");

            if (mEditableMph != null) {
                final ArrayList<String> results =
                        Mph.toAll(mEditableMph.toString(), getFieldLength());

                getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        removeTextChangedListeners(TAG + "." + mCallingClassName);
                        editTextFps.setText(results.get(0), TextView.BufferType.EDITABLE);
                        editTextKnot.setText(results.get(1), TextView.BufferType.EDITABLE);
                        editTextKph.setText(results.get(2), TextView.BufferType.EDITABLE);
                        editTextMps.setText(results.get(3), TextView.BufferType.EDITABLE);
                        addTextChangedListeners(TAG + "." + mCallingClassName);
                    }
                });
            }
        }
    }

    // endregion
}