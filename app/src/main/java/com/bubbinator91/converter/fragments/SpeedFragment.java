package com.bubbinator91.converter.fragments;

import android.graphics.Typeface;
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

    private TextWatcher textWatcherFps = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
			if (s != null) {
				afterTextChangedFps(s);
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
			if (s != null) {
				afterTextChangedKnot(s);
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
			if (s != null) {
				afterTextChangedKph(s);
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
			if (s != null) {
				afterTextChangedMps(s);
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
			if (s != null) {
				afterTextChangedMph(s);
			}
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".onCreateView", "Entered");
		}

		super.onCreateView(inflater, container, savedInstanceState);

		setShouldHideToolbarOnScroll(false);

		if (getRootView() != null) {
			Typeface tf = Typeface.createFromAsset(getCurrentActivity().getAssets(), "fonts/Roboto-Regular.ttf");

			TextView textViewFps = ((TextView) getRootView().findViewById(R.id.textView_speed_fps));
			TextView textViewKnot = ((TextView) getRootView().findViewById(R.id.textView_speed_knot));
			TextView textViewKph = ((TextView) getRootView().findViewById(R.id.textView_speed_kph));
			TextView textViewMps = ((TextView) getRootView().findViewById(R.id.textView_speed_mps));
			TextView textViewMph = ((TextView) getRootView().findViewById(R.id.textView_speed_mph));

			textViewFps.setTypeface(tf);
			textViewKnot.setTypeface(tf);
			textViewKph.setTypeface(tf);
			textViewMps.setTypeface(tf);
			textViewMph.setTypeface(tf);

			editTextFps = ((EditText) getRootView().findViewById(R.id.editText_speed_fps));
			editTextKnot = ((EditText) getRootView().findViewById(R.id.editText_speed_knot));
			editTextKph = ((EditText) getRootView().findViewById(R.id.editText_speed_kph));
			editTextMps = ((EditText) getRootView().findViewById(R.id.editText_speed_mps));
			editTextMph = ((EditText) getRootView().findViewById(R.id.editText_speed_mph));

			addTextChangedListeners();
		}

        return getRootView();
    }

	@Override
	public void onResume() {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".onResume", "Entered");
		}
		super.onResume();

		if (lastEditTextFocused == LastEditTextFocused.FPS) {
			if (editTextFps.getText() != null) {
				afterTextChangedFps(editTextFps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KNOT) {
			if (editTextKnot.getText() != null) {
				afterTextChangedKnot(editTextKnot.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KPH) {
			if (editTextKph.getText() != null) {
				afterTextChangedKph(editTextKph.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.MPS) {
			if (editTextMps.getText() != null) {
				afterTextChangedMps(editTextMps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.MPH) {
			if (editTextMph.getText() != null) {
				afterTextChangedMph(editTextMph.getText());
			}
		}
	}

	private void addTextChangedListeners() {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".addTextChangedListeners", "Entered");
		}

		editTextFps.addTextChangedListener(textWatcherFps);
		editTextKnot.addTextChangedListener(textWatcherKnot);
		editTextKph.addTextChangedListener(textWatcherKph);
		editTextMps.addTextChangedListener(textWatcherMps);
		editTextMph.addTextChangedListener(textWatcherMph);
	}

	private void removeTextChangedListeners() {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".removeTextChangedListeners", "Entered");
		}

		editTextFps.removeTextChangedListener(textWatcherFps);
		editTextKnot.removeTextChangedListener(textWatcherKnot);
		editTextKph.removeTextChangedListener(textWatcherKph);
		editTextMps.removeTextChangedListener(textWatcherMps);
		editTextMph.removeTextChangedListener(textWatcherMph);
	}

	private void afterTextChangedFps(Editable editableFps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedFps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.FPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedFps.before", editableFps.toString());
		}

		if (editableFps.length() != 0) {
			editableFps = Utils.sanitizeEditable(editableFps);
			if (editableFps != null) {
				if (Utils.isNumeric(editableFps.toString())) {
					try {
						BigDecimal fps = new BigDecimal(editableFps.toString());
						BigDecimal mph = fps.multiply(new BigDecimal(60*60))
												 .divide(new BigDecimal("5280")
																, getFieldLength()
																, BigDecimal.ROUND_HALF_UP);
						BigDecimal mps = fps.multiply(new BigDecimal("0.3048"));
						BigDecimal kph = fps.multiply(new BigDecimal("1.09728"));
						BigDecimal knot = fps.multiply(new BigDecimal(".592484"));

						editTextKnot.setText(knot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKph.setText(kph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMps.setText(mps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMph.setText(mph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
					} catch (NumberFormatException e) {
						if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
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

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableFps != null)) {
			Log.d(TAG + ".afterTextChangedFps.after", editableFps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableFps == null)) {
			Log.d(TAG + ".afterTextChangedFps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedKnot(Editable editableKnot) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKnot", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.KNOT;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKnot.before", editableKnot.toString());
		}

		if (editableKnot.length() != 0) {
			editableKnot = Utils.sanitizeEditable(editableKnot);
			if (editableKnot != null) {
				if (Utils.isNumeric(editableKnot.toString())) {
					try {
						BigDecimal knot = new BigDecimal(editableKnot.toString());
						BigDecimal fps = knot.divide(new BigDecimal(".592484")
															, getFieldLength()
															, BigDecimal.ROUND_HALF_UP);
						BigDecimal mph = knot.multiply(new BigDecimal("1.150779"));
						BigDecimal mps = knot.multiply(new BigDecimal(".514444"));
						BigDecimal kph = knot.multiply(new BigDecimal("1.852"));

						editTextFps.setText(fps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextKph.setText(kph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMps.setText(mps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMph.setText(mph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
					} catch (NumberFormatException e) {
						if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
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

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKnot != null)) {
			Log.d(TAG + ".afterTextChangedKnot.after", editableKnot.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKnot == null)) {
			Log.d(TAG + ".afterTextChangedKnot.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedKph(Editable editableKph) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKph", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.KPH;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKph.before", editableKph.toString());
		}

		if (editableKph.length() != 0) {
			editableKph = Utils.sanitizeEditable(editableKph);
			if (editableKph != null) {
				if (Utils.isNumeric(editableKph.toString())) {
					try {
						BigDecimal kph = new BigDecimal(editableKph.toString());
						BigDecimal fps = kph.divide(new BigDecimal("1.09728")
														   , getFieldLength()
														   , BigDecimal.ROUND_HALF_UP);
						BigDecimal mph = kph.divide(new BigDecimal("1.609344")
														   , getFieldLength()
														   , BigDecimal.ROUND_HALF_UP);
						BigDecimal mps = kph.divide(new BigDecimal("3.6")
														   , getFieldLength()
														   , BigDecimal.ROUND_HALF_UP);
						BigDecimal knot = kph.divide(new BigDecimal("1.852")
															, getFieldLength()
															, BigDecimal.ROUND_HALF_UP);

						editTextFps.setText(fps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextKnot.setText(knot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMps.setText(mps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMph.setText(mph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
					} catch (NumberFormatException e) {
						if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
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

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKph != null)) {
			Log.d(TAG + ".afterTextChangedKph.after", editableKph.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKph == null)) {
			Log.d(TAG + ".afterTextChangedKph.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedMps(Editable editableMps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.MPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMps.before", editableMps.toString());
		}

		if (editableMps.length() != 0) {
			editableMps = Utils.sanitizeEditable(editableMps);
			if (editableMps != null) {
				if (Utils.isNumeric(editableMps.toString())) {
					try {
						BigDecimal mps = new BigDecimal(editableMps.toString());
						BigDecimal fps = mps.divide(new BigDecimal("0.3048")
														   , getFieldLength()
														   , BigDecimal.ROUND_HALF_UP);
						BigDecimal mph = mps.divide(new BigDecimal("0.44704")
														   , getFieldLength()
														   , BigDecimal.ROUND_HALF_UP);
						BigDecimal kph = mps.multiply(new BigDecimal("3.6"));
						BigDecimal knot = mps.divide(new BigDecimal("0.514444")
															, getFieldLength()
															, BigDecimal.ROUND_HALF_UP);

						editTextFps.setText(fps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextKnot.setText(knot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKph.setText(kph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMph.setText(mph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
					} catch (NumberFormatException e) {
						if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
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

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMps != null)) {
			Log.d(TAG + ".afterTextChangedMps.after", editableMps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMps == null)) {
			Log.d(TAG + ".afterTextChangedMps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedMph(Editable editableMph) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMph", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.MPH;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMph.before", editableMph.toString());
		}

		if (editableMph.length() != 0) {
			editableMph = Utils.sanitizeEditable(editableMph);
			if (editableMph != null) {
				if (Utils.isNumeric(editableMph.toString())) {
					try {
						BigDecimal mph = new BigDecimal(editableMph.toString());
						BigDecimal fps = mph.multiply(new BigDecimal("5280"))
												 .divide(new BigDecimal("60")
																, getFieldLength()
																, BigDecimal.ROUND_HALF_UP)
												 .divide(new BigDecimal("60")
																, getFieldLength()
																, BigDecimal.ROUND_HALF_UP);
						BigDecimal mps = mph.multiply(new BigDecimal("0.44704"));
						BigDecimal kph = mph.multiply(new BigDecimal("1.609344"));
						BigDecimal knot = mph.divide(new BigDecimal("1.150779")
															, getFieldLength()
															, BigDecimal.ROUND_HALF_UP);

						editTextFps.setText(fps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextKnot.setText(knot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKph.setText(kph.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextMps.setText(mps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
					} catch (NumberFormatException e) {
						if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
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

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMph != null)) {
			Log.d(TAG + ".afterTextChangedMph.after", editableMph.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMph == null)) {
			Log.d(TAG + ".afterTextChangedMph.after", "null");
		}

		addTextChangedListeners();
	}

	@Override
	protected String getChildTag() { return TAG; }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_speed; }

	@Override
	protected int getScrollViewResource() { return  R.id.fragment_speed; }
}
