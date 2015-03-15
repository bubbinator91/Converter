package com.bubbinator91.converter.fragments;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import java.math.BigDecimal;

/**
 * mph, ft/s, m/s, kph, knot
 * Conversions taken from https://en.wikipedia.org/wiki/Miles_per_hour
 */

public class FragmentSpeed extends Fragment {
    private boolean DEBUG = false;
    private final String TAG = "FragmentSpeed";

	private SharedPreferences mPrefs;

	private int fieldLength = -1;

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
							BigDecimal fps = new BigDecimal(s.toString());
							BigDecimal mph = fps.multiply(new BigDecimal(60*60))
													 .divide(new BigDecimal("5280")
																	, fieldLength
																	, BigDecimal.ROUND_HALF_UP);
							BigDecimal mps = fps.multiply(new BigDecimal("0.3048"));
							BigDecimal kph = fps.multiply(new BigDecimal("1.09728"));
							BigDecimal knot = fps.multiply(new BigDecimal(".592484"));

                            editTextKnot.setText(knot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextKph.setText(kph.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                            editTextMps.setText(mps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                            editTextMph.setText(mph.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
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
							BigDecimal knot = new BigDecimal(s.toString());
							BigDecimal fps = knot.divide(new BigDecimal(".592484")
																, fieldLength
																, BigDecimal.ROUND_HALF_UP);
							BigDecimal mph = knot.multiply(new BigDecimal("1.150779"));
							BigDecimal mps = knot.multiply(new BigDecimal(".514444"));
							BigDecimal kph = knot.multiply(new BigDecimal("1.852"));

                            editTextFps.setText(fps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                            editTextKph.setText(kph.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                            editTextMps.setText(mps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                            editTextMph.setText(mph.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
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
		if (DEBUG)
			Log.d(TAG, "Entered onCreateView()");

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		if (mPrefs != null) {
			fieldLength = mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1);
			if (mPrefs.getInt(Util.PREFERENCE_DEBUG, -1) == 1)
				DEBUG = true;
			else
				DEBUG = false;
		}
		if (fieldLength == -1)
			fieldLength = 8;

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
