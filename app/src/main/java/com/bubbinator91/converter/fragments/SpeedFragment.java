package com.bubbinator91.converter.fragments;

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
import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;

/**
 * mph, ft/s, m/s, kph, knot
 * Conversions comply with the conversions through Google.com
 */

public class SpeedFragment extends BaseFragment {
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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherFps.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
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
							if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherFps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherFps.s.after", "null");
			}

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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherKnot.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
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
							if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherKnot.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherKnot.s.after", "null");
			}

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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherKph.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal kph = new BigDecimal(s.toString());
							BigDecimal fps = kph.divide(new BigDecimal("1.09728")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);
							BigDecimal mph = kph.divide(new BigDecimal("1.609344")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);
							BigDecimal mps = kph.divide(new BigDecimal("3.6")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);
							BigDecimal knot = kph.divide(new BigDecimal("1.852")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);

                            editTextFps.setText(fps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                            editTextKnot.setText(knot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
							if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherKph.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherKph.s.after", "null");
			}

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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMps.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal mps = new BigDecimal(s.toString());
							BigDecimal fps = mps.divide(new BigDecimal("0.3048")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);
							BigDecimal mph = mps.divide(new BigDecimal("0.44704")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);
							BigDecimal kph = mps.multiply(new BigDecimal("3.6"));
							BigDecimal knot = mps.divide(new BigDecimal("0.514444")
															   , fieldLength
															   , BigDecimal.ROUND_HALF_UP);

							editTextFps.setText(fps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextKnot.setText(knot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextMps.setText(kph.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextMph.setText(mph.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
							if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMph.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMps.s.after", "null");
			}

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

            if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMph.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal mph = new BigDecimal(s.toString());
							BigDecimal fps = mph.multiply(new BigDecimal("5280"))
													 .divide(new BigDecimal("60")
																	, fieldLength
																	, BigDecimal.ROUND_HALF_UP)
													 .divide(new BigDecimal("60")
																	, fieldLength
																	, BigDecimal.ROUND_HALF_UP);
							BigDecimal mps = mph.multiply(new BigDecimal("0.44704"));
							BigDecimal kph = mph.multiply(new BigDecimal("1.609344"));
							BigDecimal knot = mph.divide(new BigDecimal("1.150779")
																, fieldLength
																, BigDecimal.ROUND_HALF_UP);

                            editTextFps.setText(fps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
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
                        } catch (NumberFormatException e) {
							if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
								e.printStackTrace();
							}
                        }
                    }
                }
            } else {
                editTextFps.setText("", TextView.BufferType.EDITABLE);
                editTextKnot.setText("", TextView.BufferType.EDITABLE);
                editTextKph.setText("", TextView.BufferType.EDITABLE);
                editTextMps.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMph.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMph.s.after", "null");
			}

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
		if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
			Log.d(TAG + ".onCreateView", "Entered");
		}

        View rootView = super.onCreateView(inflater, container, savedInstanceState);

		if (rootView != null) {
			editTextFps = ((EditText) rootView.findViewById(R.id.editText_speed_fps));
			editTextKnot = ((EditText) rootView.findViewById(R.id.editText_speed_knot));
			editTextKph = ((EditText) rootView.findViewById(R.id.editText_speed_kph));
			editTextMps = ((EditText) rootView.findViewById(R.id.editText_speed_mps));
			editTextMph = ((EditText) rootView.findViewById(R.id.editText_speed_mph));

			editTextFps.addTextChangedListener(textWatcherFps);
			editTextKnot.addTextChangedListener(textWatcherKnot);
			editTextKph.addTextChangedListener(textWatcherKph);
			editTextMps.addTextChangedListener(textWatcherMps);
			editTextMph.addTextChangedListener(textWatcherMph);
		}

        return rootView;
    }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_speed; }

	@Override
	protected int getScrollViewResource() { return  R.id.fragment_speed; }
}
