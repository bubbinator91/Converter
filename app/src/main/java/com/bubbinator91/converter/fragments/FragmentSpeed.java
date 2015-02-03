package com.bubbinator91.converter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.Util;

/**
 * mph, ft/s, m/s, kph, knot
 * Conversions taken from https://en.wikipedia.org/wiki/Miles_per_hour
 */

public class FragmentSpeed extends Fragment {
    private final boolean DEBUG = false;
    private final String TAG = "FragmentSpeed";

    private EditText editTextFps, editTextKnot, editTextKph, editTextMps, editTextMph;

    private TextWatcher textWatcherFps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextFps.removeTextChangedListener(textWatcherFps);
            editTextKnot.removeTextChangedListener(textWatcherKnot);
            editTextKph.removeTextChangedListener(textWatcherKph);
            editTextMps.removeTextChangedListener(textWatcherMps);
            editTextMph.removeTextChangedListener(textWatcherMph);

            if (DEBUG)
                Log.d(TAG + ".textWatcherFps.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double fps = Double.parseDouble(s.toString());
                            double mph = ((fps * 60.0 * 60.0) / 5280.0);
                            double mps = (fps * .3048);
                            double kph = (fps * 1.09728);
                            double knot = (fps * .592484);

                            editTextKnot.setText(Double.toString(knot), TextView.BufferType.EDITABLE);
                            editTextKph.setText(Double.toString(kph), TextView.BufferType.EDITABLE);
                            editTextMps.setText(Double.toString(mps), TextView.BufferType.EDITABLE);
                            editTextMph.setText(Double.toString(mph), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherFps.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherFps.s.after", "null");

            editTextFps.addTextChangedListener(textWatcherFps);
            editTextKnot.addTextChangedListener(textWatcherKnot);
            editTextKph.addTextChangedListener(textWatcherKph);
            editTextMps.addTextChangedListener(textWatcherMps);
            editTextMph.addTextChangedListener(textWatcherMph);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKnot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextFps.removeTextChangedListener(textWatcherFps);
            editTextKnot.removeTextChangedListener(textWatcherKnot);
            editTextKph.removeTextChangedListener(textWatcherKph);
            editTextMps.removeTextChangedListener(textWatcherMps);
            editTextMph.removeTextChangedListener(textWatcherMph);

            if (DEBUG)
                Log.d(TAG + ".textWatcherKnot.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double knot = Double.parseDouble(s.toString());
                            double fps = (knot / .592484);
                            double mph = (knot * 1.150779);
                            double mps = (knot * .514444);
                            double kph = (knot * 1.852);

                            editTextFps.setText(Double.toString(fps), TextView.BufferType.EDITABLE);
                            editTextKph.setText(Double.toString(kph), TextView.BufferType.EDITABLE);
                            editTextMps.setText(Double.toString(mps), TextView.BufferType.EDITABLE);
                            editTextMph.setText(Double.toString(mph), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherKnot.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherKnot.s.after", "null");

            editTextFps.addTextChangedListener(textWatcherFps);
            editTextKnot.addTextChangedListener(textWatcherKnot);
            editTextKph.addTextChangedListener(textWatcherKph);
            editTextMps.addTextChangedListener(textWatcherMps);
            editTextMph.addTextChangedListener(textWatcherMph);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKph = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextFps.removeTextChangedListener(textWatcherFps);
            editTextKnot.removeTextChangedListener(textWatcherKnot);
            editTextKph.removeTextChangedListener(textWatcherKph);
            editTextMps.removeTextChangedListener(textWatcherMps);
            editTextMph.removeTextChangedListener(textWatcherMph);

            if (DEBUG)
                Log.d(TAG + ".textWatcherKph.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double kph = Double.parseDouble(s.toString());
                            double fps = (kph / 1.09728);
                            double mph = (kph / 1.609344);
                            double mps = (kph / 3.6);
                            double knot = (kph / 1.852);

                            editTextFps.setText(Double.toString(fps), TextView.BufferType.EDITABLE);
                            editTextKnot.setText(Double.toString(knot), TextView.BufferType.EDITABLE);
                            editTextMps.setText(Double.toString(mps), TextView.BufferType.EDITABLE);
                            editTextMph.setText(Double.toString(mph), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherKph.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherKph.s.after", "null");

            editTextFps.addTextChangedListener(textWatcherFps);
            editTextKnot.addTextChangedListener(textWatcherKnot);
            editTextKph.addTextChangedListener(textWatcherKph);
            editTextMps.addTextChangedListener(textWatcherMps);
            editTextMph.addTextChangedListener(textWatcherMph);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextFps.removeTextChangedListener(textWatcherFps);
            editTextKnot.removeTextChangedListener(textWatcherKnot);
            editTextKph.removeTextChangedListener(textWatcherKph);
            editTextMps.removeTextChangedListener(textWatcherMps);
            editTextMph.removeTextChangedListener(textWatcherMph);

            if (DEBUG)
                Log.d(TAG + ".textWatcherMps.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double mps = Double.parseDouble(s.toString());
                            double fps = (mps / .3048);
                            double mph = (mps / .44704);
                            double kph = (mps * 3.6);
                            double knot = (mps / .514444);

                            editTextFps.setText(Double.toString(fps), TextView.BufferType.EDITABLE);
                            editTextKnot.setText(Double.toString(knot), TextView.BufferType.EDITABLE);
                            editTextKph.setText(Double.toString(kph), TextView.BufferType.EDITABLE);
                            editTextMph.setText(Double.toString(mph), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherMps.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherMps.s.after", "null");

            editTextFps.addTextChangedListener(textWatcherFps);
            editTextKnot.addTextChangedListener(textWatcherKnot);
            editTextKph.addTextChangedListener(textWatcherKph);
            editTextMps.addTextChangedListener(textWatcherMps);
            editTextMph.addTextChangedListener(textWatcherMph);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMph = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextFps.removeTextChangedListener(textWatcherFps);
            editTextKnot.removeTextChangedListener(textWatcherKnot);
            editTextKph.removeTextChangedListener(textWatcherKph);
            editTextMps.removeTextChangedListener(textWatcherMps);
            editTextMph.removeTextChangedListener(textWatcherMph);

            if (DEBUG)
                Log.d(TAG + ".textWatcherMph.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double mph = Double.parseDouble(s.toString());
                            double fps = (((mph * 5280.0) / 60.0) / 60.0);
                            double mps = (mph * .44704);
                            double kph = (mph * 1.609344);
                            double knot = (mph / 1.150779);

                            editTextFps.setText(Double.toString(fps), TextView.BufferType.EDITABLE);
                            editTextKnot.setText(Double.toString(knot), TextView.BufferType.EDITABLE);
                            editTextKph.setText(Double.toString(kph), TextView.BufferType.EDITABLE);
                            editTextMps.setText(Double.toString(mps), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherMph.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherMph.s.after", "null");

            editTextFps.addTextChangedListener(textWatcherFps);
            editTextKnot.addTextChangedListener(textWatcherKnot);
            editTextKph.addTextChangedListener(textWatcherKph);
            editTextMps.addTextChangedListener(textWatcherMps);
            editTextMph.addTextChangedListener(textWatcherMph);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_speed, container, false);

        editTextFps = ((EditText)rootView.findViewById(R.id.editText_speed_fps));
        editTextKnot = ((EditText)rootView.findViewById(R.id.editText_speed_knot));
        editTextKph = ((EditText)rootView.findViewById(R.id.editText_speed_kph));
        editTextMps = ((EditText)rootView.findViewById(R.id.editText_speed_mps));
        editTextMph = ((EditText)rootView.findViewById(R.id.editText_speed_mph));

        editTextFps.addTextChangedListener(textWatcherFps);
        editTextKnot.addTextChangedListener(textWatcherKnot);
        editTextKph.addTextChangedListener(textWatcherKph);
        editTextMps.addTextChangedListener(textWatcherMps);
        editTextMph.addTextChangedListener(textWatcherMph);

        return rootView;
    }
}
