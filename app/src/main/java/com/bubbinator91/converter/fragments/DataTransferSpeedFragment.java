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

// TODO Update to use global variables
// TODO Improve performance by using a second thread
public class DataTransferSpeedFragment extends BaseFragment {
	private enum LastEditTextFocused {
		BPS,
		BYPS,
		KBPS,
		KBYPS,
		MBPS,
		MBYPS,
		GBPS,
		GBYPS,
		TBPS,
		TBYPS
	}
	private final String TAG = "FragmentDataTransferSpeed";

	private EditText editTextBps, editTextByps, editTextKbps, editTextKbyps, editTextMbps,
			editTextMbyps, editTextGbps, editTextGbyps, editTextTbps, editTextTbyps;

	private LastEditTextFocused lastEditTextFocused;

	// region TextWatchers

	private TextWatcher textWatcherBps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedBps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherByps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedByps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherKbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedKbps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherKbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedKbyps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherMbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedMbps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherMbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedMbyps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherGbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedGbps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherGbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedGbyps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherTbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedTbps(s);
            }
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherTbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
            if (s != null) {
                afterTextChangedTbyps(s);
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
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".onCreateView", "Entered");
		}

		if (getRootView() != null) {
			Typeface tf = Typeface.createFromAsset(getCurrentActivity().getAssets(), "fonts/Roboto-Regular.ttf");

			TextView textViewBps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_bps));
			TextView textViewByps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_byps));
			TextView textViewKbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_kbps));
			TextView textViewKbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_kbyps));
			TextView textViewMbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_mbps));
			TextView textViewMbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_mbyps));
			TextView textViewGbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_gbps));
			TextView textViewGbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_gbyps));
			TextView textViewTbps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_tbps));
			TextView textViewTbyps = ((TextView) getRootView().findViewById(R.id.textView_data_transfer_speed_tbyps));

			textViewBps.setTypeface(tf);
			textViewByps.setTypeface(tf);
			textViewKbps.setTypeface(tf);
			textViewKbyps.setTypeface(tf);
			textViewMbps.setTypeface(tf);
			textViewMbyps.setTypeface(tf);
			textViewGbps.setTypeface(tf);
			textViewGbyps.setTypeface(tf);
			textViewTbps.setTypeface(tf);
			textViewTbyps.setTypeface(tf);

			editTextBps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_bps));
			editTextByps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_byps));
			editTextKbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_kbps));
			editTextKbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_kbyps));
			editTextMbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_mbps));
			editTextMbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_mbyps));
			editTextGbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_gbps));
			editTextGbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_gbyps));
			editTextTbps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_tbps));
			editTextTbyps = ((EditText) getRootView().findViewById(R.id.editText_data_transfer_speed_tbyps));

			addTextChangedListeners();
		}

        return getRootView();
    }

	@Override
	public void onResume() {
		super.onResume();
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".onResume", "Entered");
		}

		if (lastEditTextFocused == LastEditTextFocused.BPS) {
			if (editTextBps.getText() != null) {
				afterTextChangedBps(editTextBps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.BYPS) {
			if (editTextByps.getText() != null) {
				afterTextChangedByps(editTextByps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KBPS) {
			if (editTextKbps.getText() != null) {
				afterTextChangedKbps(editTextKbps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KBYPS) {
			if (editTextKbyps.getText() != null) {
				afterTextChangedKbyps(editTextKbyps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.MBPS) {
			if (editTextMbps.getText() != null) {
				afterTextChangedMbps(editTextMbps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.MBYPS) {
			if (editTextMbyps.getText() != null) {
				afterTextChangedMbyps(editTextMbyps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.GBPS) {
			if (editTextGbps.getText() != null) {
				afterTextChangedGbps(editTextGbps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.GBYPS) {
			if (editTextGbyps.getText() != null) {
				afterTextChangedGbyps(editTextGbyps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.TBPS) {
			if (editTextTbps.getText() != null) {
				afterTextChangedTbps(editTextTbps.getText());
			}
		} else if (lastEditTextFocused == LastEditTextFocused.TBYPS) {
			if (editTextTbyps.getText() != null) {
				afterTextChangedTbyps(editTextTbyps.getText());
			}
		}
	}

	// endregion

	// region Helper methods

	private void addTextChangedListeners() {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".addTextChangedListeners", "Entered");
        }

		editTextBps.addTextChangedListener(textWatcherBps);
		editTextByps.addTextChangedListener(textWatcherByps);
		editTextKbps.addTextChangedListener(textWatcherKbps);
		editTextKbyps.addTextChangedListener(textWatcherKbyps);
		editTextMbps.addTextChangedListener(textWatcherMbps);
		editTextMbyps.addTextChangedListener(textWatcherMbyps);
		editTextGbps.addTextChangedListener(textWatcherGbps);
		editTextGbyps.addTextChangedListener(textWatcherGbyps);
		editTextTbps.addTextChangedListener(textWatcherTbps);
		editTextTbyps.addTextChangedListener(textWatcherTbyps);
	}

	private void removeTextChangedListeners() {
        if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
            Log.d(TAG + ".removeTextChangedListeners", "Entered");
        }

		editTextBps.removeTextChangedListener(textWatcherBps);
		editTextByps.removeTextChangedListener(textWatcherByps);
		editTextKbps.removeTextChangedListener(textWatcherKbps);
		editTextKbyps.removeTextChangedListener(textWatcherKbyps);
		editTextMbps.removeTextChangedListener(textWatcherMbps);
		editTextMbyps.removeTextChangedListener(textWatcherMbyps);
		editTextGbps.removeTextChangedListener(textWatcherGbps);
		editTextGbyps.removeTextChangedListener(textWatcherGbyps);
		editTextTbps.removeTextChangedListener(textWatcherTbps);
		editTextTbyps.removeTextChangedListener(textWatcherTbyps);
	}

	protected void afterTextChangedBps(Editable editableBps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedBps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.BPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedBps.before", editableBps.toString());
		}

		if (editableBps.length() != 0) {
			editableBps = Utils.sanitizeEditable(editableBps);
			if (editableBps != null) {
				if (Utils.isNumeric(editableBps.toString())) {
					try {
						BigDecimal bps = new BigDecimal(editableBps.toString());
						BigDecimal byps = bps.multiply(new BigDecimal("0.125"));
						BigDecimal kbps = bps.multiply(new BigDecimal("0.001"));
						BigDecimal kbyps = bps.multiply(new BigDecimal("0.000125"));
						BigDecimal mbps = bps.multiply(new BigDecimal("0.000001"));
						BigDecimal mbyps = bps.multiply(new BigDecimal("0.000000125"));
						BigDecimal gbps = bps.multiply(new BigDecimal("0.000000001"));
						BigDecimal gbyps = bps.multiply(new BigDecimal("0.000000000125"));
						BigDecimal tbps = bps.multiply(new BigDecimal("0.000000000001"));
						BigDecimal tbyps = bps.multiply(new BigDecimal("0.000000000000125"));

						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextKbyps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableBps != null)) {
			Log.d(TAG + ".afterTextChangedBps.after", editableBps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableBps == null)) {
			Log.d(TAG + ".afterTextChangedBps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedByps(Editable editableByps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedByps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.BYPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedByps.before", editableByps.toString());
		}

		if (editableByps.length() != 0) {
			editableByps = Utils.sanitizeEditable(editableByps);
			if (editableByps != null) {
				if (Utils.isNumeric(editableByps.toString())) {
					try {
						BigDecimal byps = new BigDecimal(editableByps.toString());
						BigDecimal bps = byps.multiply(new BigDecimal("8"));
						BigDecimal kbps = byps.multiply(new BigDecimal("0.008"));
						BigDecimal kbyps = byps.multiply(new BigDecimal("0.001"));
						BigDecimal mbps = byps.multiply(new BigDecimal("0.000008"));
						BigDecimal mbyps = byps.multiply(new BigDecimal("0.000001"));
						BigDecimal gbps = byps.multiply(new BigDecimal("0.000000008"));
						BigDecimal gbyps = byps.multiply(new BigDecimal("0.000000001"));
						BigDecimal tbps = byps.multiply(new BigDecimal("0.000000000008"));
						BigDecimal tbyps = byps.multiply(new BigDecimal("0.000000000001"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextKbyps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableByps != null)) {
			Log.d(TAG + ".afterTextChangedByps.after", editableByps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableByps == null)) {
			Log.d(TAG + ".afterTextChangedByps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedKbps(Editable editableKbps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKbps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.KBPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKbps.before", editableKbps.toString());
		}

		if (editableKbps.length() != 0) {
			editableKbps = Utils.sanitizeEditable(editableKbps);
			if (editableKbps != null) {
				if (Utils.isNumeric(editableKbps.toString())) {
					try {
						BigDecimal kbps = new BigDecimal(editableKbps.toString());
						BigDecimal bps = kbps.multiply(new BigDecimal("1000"));
						BigDecimal byps = kbps.multiply(new BigDecimal("125"));
						BigDecimal kbyps = kbps.multiply(new BigDecimal("0.125"));
						BigDecimal mbps = kbps.multiply(new BigDecimal("0.001"));
						BigDecimal mbyps = kbps.multiply(new BigDecimal("0.000125"));
						BigDecimal gbps = kbps.multiply(new BigDecimal("0.000001"));
						BigDecimal gbyps = kbps.multiply(new BigDecimal("0.000000125"));
						BigDecimal tbps = kbps.multiply(new BigDecimal("0.000000001"));
						BigDecimal tbyps = kbps.multiply(new BigDecimal("0.000000000125"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbyps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKbps != null)) {
			Log.d(TAG + ".afterTextChangedKbps.after", editableKbps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKbps == null)) {
			Log.d(TAG + ".afterTextChangedKbps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedKbyps(Editable editableKbyps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKbyps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.KBYPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedKbyps.before", editableKbyps.toString());
		}

		if (editableKbyps.length() != 0) {
			editableKbyps = Utils.sanitizeEditable(editableKbyps);
			if (editableKbyps != null) {
				if (Utils.isNumeric(editableKbyps.toString())) {
					try {
						BigDecimal kbyps = new BigDecimal(editableKbyps.toString());
						BigDecimal bps = kbyps.multiply(new BigDecimal("8000"));
						BigDecimal byps = kbyps.multiply(new BigDecimal("1000"));
						BigDecimal kbps = kbyps.multiply(new BigDecimal("8"));
						BigDecimal mbps = kbyps.multiply(new BigDecimal("0.008"));
						BigDecimal mbyps = kbyps.multiply(new BigDecimal("0.001"));
						BigDecimal gbps = kbyps.multiply(new BigDecimal("0.000008"));
						BigDecimal gbyps = kbyps.multiply(new BigDecimal("0.000001"));
						BigDecimal tbps = kbyps.multiply(new BigDecimal("0.000000008"));
						BigDecimal tbyps = kbyps.multiply(new BigDecimal("0.000000001"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKbyps != null)) {
			Log.d(TAG + ".afterTextChangedKbyps.after", editableKbyps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableKbyps == null)) {
			Log.d(TAG + ".afterTextChangedKbyps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedMbps(Editable editableMbps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMbps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.MBPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMbps.before", editableMbps.toString());
		}

		if (editableMbps.length() != 0) {
			editableMbps = Utils.sanitizeEditable(editableMbps);
			if (editableMbps != null) {
				if (Utils.isNumeric(editableMbps.toString())) {
					try {
						BigDecimal mbps = new BigDecimal(editableMbps.toString());
						BigDecimal bps = mbps.multiply(new BigDecimal("1000000"));
						BigDecimal byps = mbps.multiply(new BigDecimal("125000"));
						BigDecimal kbps = mbps.multiply(new BigDecimal("1000"));
						BigDecimal kbyps = mbps.multiply(new BigDecimal("125"));
						BigDecimal mbyps = mbps.multiply(new BigDecimal("0.125"));
						BigDecimal gbps = mbps.multiply(new BigDecimal("0.001"));
						BigDecimal gbyps = mbps.multiply(new BigDecimal("0.000125"));
						BigDecimal tbps = mbps.multiply(new BigDecimal("0.000001"));
						BigDecimal tbyps = mbps.multiply(new BigDecimal("0.000000125"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMbps != null)) {
			Log.d(TAG + ".afterTextChangedMbps.after", editableMbps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMbps == null)) {
			Log.d(TAG + ".afterTextChangedMbps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedMbyps(Editable editableMbyps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMbyps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.MBYPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedMbyps.before", editableMbyps.toString());
		}

		if (editableMbyps.length() != 0) {
			editableMbyps = Utils.sanitizeEditable(editableMbyps);
			if (editableMbyps != null) {
				if (Utils.isNumeric(editableMbyps.toString())) {
					try {
						BigDecimal mbyps = new BigDecimal(editableMbyps.toString());
						BigDecimal bps = mbyps.multiply(new BigDecimal("8000000"));
						BigDecimal byps = mbyps.multiply(new BigDecimal("1000000"));
						BigDecimal kbps = mbyps.multiply(new BigDecimal("8000"));
						BigDecimal kbyps = mbyps.multiply(new BigDecimal("1000"));
						BigDecimal mbps = mbyps.multiply(new BigDecimal("8"));
						BigDecimal gbps = mbyps.multiply(new BigDecimal("0.008"));
						BigDecimal gbyps = mbyps.multiply(new BigDecimal("0.001"));
						BigDecimal tbps = mbyps.multiply(new BigDecimal("0.000008"));
						BigDecimal tbyps = mbyps.multiply(new BigDecimal("0.000001"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMbyps != null)) {
			Log.d(TAG + ".afterTextChangedMbyps.after", editableMbyps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableMbyps == null)) {
			Log.d(TAG + ".afterTextChangedMbyps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedGbps(Editable editableGbps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedGbps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.GBPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedGbps.before", editableGbps.toString());
		}

		if (editableGbps.length() != 0) {
			editableGbps = Utils.sanitizeEditable(editableGbps);
			if (editableGbps != null) {
				if (Utils.isNumeric(editableGbps.toString())) {
					try {
						BigDecimal gbps = new BigDecimal(editableGbps.toString());
						BigDecimal bps = gbps.multiply(new BigDecimal("1000000000"));
						BigDecimal byps = gbps.multiply(new BigDecimal("125000000"));
						BigDecimal kbps = gbps.multiply(new BigDecimal("1000000"));
						BigDecimal kbyps = gbps.multiply(new BigDecimal("125000"));
						BigDecimal mbps = gbps.multiply(new BigDecimal("1000"));
						BigDecimal mbyps = gbps.multiply(new BigDecimal("125"));
						BigDecimal gbyps = gbps.multiply(new BigDecimal("0.125"));
						BigDecimal tbps = gbps.multiply(new BigDecimal("0.001"));
						BigDecimal tbyps = gbps.multiply(new BigDecimal("0.000125"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableGbps != null)) {
			Log.d(TAG + ".afterTextChangedGbps.after", editableGbps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableGbps == null)) {
			Log.d(TAG + ".afterTextChangedGbps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedGbyps(Editable editableGbyps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedGbyps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.GBYPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedGbyps.before", editableGbyps.toString());
		}

		if (editableGbyps.length() != 0) {
			editableGbyps = Utils.sanitizeEditable(editableGbyps);
			if (editableGbyps != null) {
				if (Utils.isNumeric(editableGbyps.toString())) {
					try {
						BigDecimal gbyps = new BigDecimal(editableGbyps.toString());
						BigDecimal bps = gbyps.multiply(new BigDecimal("8000000000"));
						BigDecimal byps = gbyps.multiply(new BigDecimal("1000000000"));
						BigDecimal kbps = gbyps.multiply(new BigDecimal("8000000"));
						BigDecimal kbyps = gbyps.multiply(new BigDecimal("1000000"));
						BigDecimal mbps = gbyps.multiply(new BigDecimal("8000"));
						BigDecimal mbyps = gbyps.multiply(new BigDecimal("1000"));
						BigDecimal gbps = gbyps.multiply(new BigDecimal("8"));
						BigDecimal tbps = gbyps.multiply(new BigDecimal("0.008"));
						BigDecimal tbyps = gbyps.multiply(new BigDecimal("0.001"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableGbyps != null)) {
			Log.d(TAG + ".afterTextChangedGbyps.after", editableGbyps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableGbyps == null)) {
			Log.d(TAG + ".afterTextChangedGbyps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedTbps(Editable editableTbps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedTbps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.TBPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedTbps.before", editableTbps.toString());
		}

		if (editableTbps.length() != 0) {
			editableTbps = Utils.sanitizeEditable(editableTbps);
			if (editableTbps != null) {
				if (Utils.isNumeric(editableTbps.toString())) {
					try {
						BigDecimal tbps = new BigDecimal(editableTbps.toString());
						BigDecimal bps = tbps.multiply(new BigDecimal("1000000000000"));
						BigDecimal byps = tbps.multiply(new BigDecimal("125000000000"));
						BigDecimal kbps = tbps.multiply(new BigDecimal("1000000000"));
						BigDecimal kbyps = tbps.multiply(new BigDecimal("125000000"));
						BigDecimal mbps = tbps.multiply(new BigDecimal("1000000"));
						BigDecimal mbyps = tbps.multiply(new BigDecimal("125000"));
						BigDecimal gbps = tbps.multiply(new BigDecimal("1000"));
						BigDecimal gbyps = tbps.multiply(new BigDecimal("125"));
						BigDecimal tbyps = tbps.multiply(new BigDecimal("0.125"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbyps.setText(tbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableTbps != null)) {
			Log.d(TAG + ".afterTextChangedTbps.after", editableTbps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableTbps == null)) {
			Log.d(TAG + ".afterTextChangedTbps.after", "null");
		}

		addTextChangedListeners();
	}

	private void afterTextChangedTbyps(Editable editableTbyps) {
		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedTbyps", "Entered");
		}

		removeTextChangedListeners();

		lastEditTextFocused = LastEditTextFocused.TBYPS;

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
			Log.d(TAG + ".afterTextChangedTbyps.before", editableTbyps.toString());
		}

		if (editableTbyps.length() != 0) {
			editableTbyps = Utils.sanitizeEditable(editableTbyps);
			if (editableTbyps != null) {
				if (Utils.isNumeric(editableTbyps.toString())) {
					try {
						BigDecimal tbyps = new BigDecimal(editableTbyps.toString());
						BigDecimal bps = tbyps.multiply(new BigDecimal("8000000000000"));
						BigDecimal byps = tbyps.multiply(new BigDecimal("1000000000000"));
						BigDecimal kbps = tbyps.multiply(new BigDecimal("8000000000"));
						BigDecimal kbyps = tbyps.multiply(new BigDecimal("1000000000"));
						BigDecimal mbps = tbyps.multiply(new BigDecimal("8000000"));
						BigDecimal mbyps = tbyps.multiply(new BigDecimal("1000000"));
						BigDecimal gbps = tbyps.multiply(new BigDecimal("8000"));
						BigDecimal gbyps = tbyps.multiply(new BigDecimal("1000"));
						BigDecimal tbps = tbyps.multiply(new BigDecimal("8"));

						editTextBps.setText(bps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													.stripTrailingZeros()
													.toPlainString()
												   , TextView.BufferType.EDITABLE);
						editTextByps.setText(byps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbps.setText(kbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextKbyps.setText(kbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextMbps.setText(mbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextMbyps.setText(mbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextGbps.setText(gbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													 .stripTrailingZeros()
													 .toPlainString()
													, TextView.BufferType.EDITABLE);
						editTextGbyps.setText(gbyps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
													  .stripTrailingZeros()
													  .toPlainString()
													 , TextView.BufferType.EDITABLE);
						editTextTbps.setText(tbps.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
			editTextBps.setText("", TextView.BufferType.EDITABLE);
			editTextByps.setText("", TextView.BufferType.EDITABLE);
			editTextKbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbps.setText("", TextView.BufferType.EDITABLE);
			editTextMbyps.setText("", TextView.BufferType.EDITABLE);
			editTextGbps.setText("", TextView.BufferType.EDITABLE);
			editTextGbyps.setText("", TextView.BufferType.EDITABLE);
			editTextTbps.setText("", TextView.BufferType.EDITABLE);
			editTextTbyps.setText("", TextView.BufferType.EDITABLE);
		}

		if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableTbyps != null)) {
			Log.d(TAG + ".afterTextChangedTbyps.after", editableTbyps.toString());
		} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (editableTbyps == null)) {
			Log.d(TAG + ".afterTextChangedTbyps.after", "null");
		}

		addTextChangedListeners();
	}

	// endregion

	// region Overridden BaseFragment methods

	@Override
	protected String getChildTag() { return TAG; }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_data_transfer_speed; }

	@Override
	protected int getScrollViewResource() { return  R.id.fragment_dts; }

	// endregion
}
