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
import java.math.MathContext;
import java.math.RoundingMode;

public class FragmentDataTransferSpeed extends Fragment {
	private final boolean DEBUG = true;
	private final String TAG = "FragmentDataTransferSpeed";

	private SharedPreferences mPrefs;

	private int fieldLength = -1;

	private EditText editTextBps, editTextByps, editTextKbps, editTextKbyps, editTextMbps,
			editTextMbyps, editTextGbps, editTextGbyps, editTextTbps, editTextTbyps;

	private TextWatcher textWatcherBps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			if (DEBUG)
				Log.d(TAG + ".textWatcherBps.s.before", s.toString());

			if (s.length() != 0) {
				s = Util.sanitizeEditable(s);
				if (s != null) {
					if (Util.isNumeric(s.toString())) {
						try {
							BigDecimal bps = new BigDecimal(s.toString());
							BigDecimal byps = bps.multiply(new BigDecimal("0.125"));
							BigDecimal kbps = bps.multiply(new BigDecimal("0.001"));
							BigDecimal kbyps = bps.multiply(new BigDecimal("0.000125"));
							BigDecimal mbps = bps.multiply(new BigDecimal("0.000001"));
							BigDecimal mbyps = bps.multiply(new BigDecimal("0.000000125"));
							BigDecimal gbps = bps.multiply(new BigDecimal("0.000000001"));
							BigDecimal gbyps = bps.multiply(new BigDecimal("0.000000000125"));
							BigDecimal tbps = bps.multiply(new BigDecimal("0.000000000001"));
							BigDecimal tbyps = bps.multiply(new BigDecimal("0.000000000000125"));

							editTextByps.setText(byps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.stripTrailingZeros().toString(), TextView.BufferType.EDITABLE);
						} catch (NumberFormatException e) {
							if (DEBUG)
								e.printStackTrace();
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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherByps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			if (DEBUG)
				Log.d(TAG + ".textWatcherByps.s.before", s.toString());

			if (s.length() != 0) {
				s = Util.sanitizeEditable(s);
				if (s != null) {
					if (Util.isNumeric(s.toString())) {
						try {
							BigDecimal byps = new BigDecimal(s.toString());
							BigDecimal bps = byps.multiply(new BigDecimal("8"));
							BigDecimal kbps = byps.multiply(new BigDecimal("0.008"));
							BigDecimal kbyps = byps.multiply(new BigDecimal("0.001"));
							BigDecimal mbps = byps.multiply(new BigDecimal("0.000008"));
							BigDecimal mbyps = byps.multiply(new BigDecimal("0.000001"));
							BigDecimal gbps = byps.multiply(new BigDecimal("0.000000008"));
							BigDecimal gbyps = byps.multiply(new BigDecimal("0.000000001"));
							BigDecimal tbps = byps.multiply(new BigDecimal("0.000000000008"));
							BigDecimal tbyps = byps.multiply(new BigDecimal("0.000000000001"));

							editTextBps.setText(bps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
						} catch (NumberFormatException e) {
							if (DEBUG)
								e.printStackTrace();
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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherKbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			if (DEBUG)
				Log.d(TAG + ".textWatcherKbps.s.before", s.toString());

			if (s.length() != 0) {
				s = Util.sanitizeEditable(s);
				if (s != null) {
					if (Util.isNumeric(s.toString())) {
						try {
							BigDecimal kbps = new BigDecimal(s.toString());
							BigDecimal bps = kbps.multiply(new BigDecimal("1000"));
							BigDecimal byps = kbps.multiply(new BigDecimal("125"));
							BigDecimal kbyps = kbps.multiply(new BigDecimal("0.125"));
							BigDecimal mbps = kbps.multiply(new BigDecimal("0.001"));
							BigDecimal mbyps = kbps.multiply(new BigDecimal("0.000125"));
							BigDecimal gbps = kbps.multiply(new BigDecimal("0.000001"));
							BigDecimal gbyps = kbps.multiply(new BigDecimal("0.000000125"));
							BigDecimal tbps = kbps.multiply(new BigDecimal("0.000000001"));
							BigDecimal tbyps = kbps.multiply(new BigDecimal("0.000000000125"));

							editTextBps.setText(bps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
						} catch (NumberFormatException e) {
							if (DEBUG)
								e.printStackTrace();
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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherKbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			if (DEBUG)
				Log.d(TAG + ".textWatcherKbyps.s.before", s.toString());

			if (s.length() != 0) {
				s = Util.sanitizeEditable(s);
				if (s != null) {
					if (Util.isNumeric(s.toString())) {
						try {
							BigDecimal kbyps = new BigDecimal(s.toString());
							BigDecimal bps = kbyps.multiply(new BigDecimal("8000"));
							BigDecimal byps = kbyps.multiply(new BigDecimal("1000"));
							BigDecimal kbps = kbyps.multiply(new BigDecimal("8"));
							BigDecimal mbps = kbyps.multiply(new BigDecimal("0.008"));
							BigDecimal mbyps = kbyps.multiply(new BigDecimal("0.001"));
							BigDecimal gbps = kbyps.multiply(new BigDecimal("0.000008"));
							BigDecimal gbyps = kbyps.multiply(new BigDecimal("0.000001"));
							BigDecimal tbps = kbyps.multiply(new BigDecimal("0.000000008"));
							BigDecimal tbyps = kbyps.multiply(new BigDecimal("0.000000001"));

							editTextBps.setText(bps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.stripTrailingZeros().toPlainString(), TextView.BufferType.EDITABLE);
						} catch (NumberFormatException e) {
							if (DEBUG)
								e.printStackTrace();
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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherMbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			// conversion code here

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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherMbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			// conversion code here

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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherGbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			// conversion code here

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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherGbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			// conversion code here

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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherTbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			// conversion code here

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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherTbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
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

			// conversion code here

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

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (DEBUG)
			Log.d(TAG, "Entered onCreateView");

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		if (mPrefs != null)
			fieldLength = mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1);
		if (fieldLength == -1)
			fieldLength = 8;

		View rootView = inflater.inflate(R.layout.fragment_data_transfer_speed, container, false);

		editTextBps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_bps));
		editTextByps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_byps));
		editTextKbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_kbps));
		editTextKbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_kbyps));
		editTextMbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_mbps));
		editTextMbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_mbyps));
		editTextGbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_gbps));
		editTextGbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_gbyps));
		editTextTbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_tbps));
		editTextTbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_tbyps));

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

        return rootView;
    }
}
