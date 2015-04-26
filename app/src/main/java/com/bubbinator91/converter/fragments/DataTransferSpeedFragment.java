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

public class DataTransferSpeedFragment extends BaseFragment {
	private final String TAG = "FragmentDataTransferSpeed";

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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherBps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
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

							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherBps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherBps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherByps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
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

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherByps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherByps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherKbps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
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

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherKbps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherKbps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherKbyps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
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

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherKbyps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherKbyps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMbps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						try {
							BigDecimal mbps = new BigDecimal(s.toString());
							BigDecimal bps = mbps.multiply(new BigDecimal("1000000"));
							BigDecimal byps = mbps.multiply(new BigDecimal("125000"));
							BigDecimal kbps = mbps.multiply(new BigDecimal("1000"));
							BigDecimal kbyps = mbps.multiply(new BigDecimal("125"));
							BigDecimal mbyps = mbps.multiply(new BigDecimal("0.125"));
							BigDecimal gbps = mbps.multiply(new BigDecimal("0.001"));
							BigDecimal gbyps = mbps.multiply(new BigDecimal("0.000125"));
							BigDecimal tbps = mbps.multiply(new BigDecimal("0.000001"));
							BigDecimal tbyps = mbps.multiply(new BigDecimal("0.000000125"));

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMbps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMbps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMbyps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						try {
							BigDecimal mbyps = new BigDecimal(s.toString());
							BigDecimal bps = mbyps.multiply(new BigDecimal("8000000"));
							BigDecimal byps = mbyps.multiply(new BigDecimal("1000000"));
							BigDecimal kbps = mbyps.multiply(new BigDecimal("8000"));
							BigDecimal kbyps = mbyps.multiply(new BigDecimal("1000"));
							BigDecimal mbps = mbyps.multiply(new BigDecimal("8"));
							BigDecimal gbps = mbyps.multiply(new BigDecimal("0.008"));
							BigDecimal gbyps = mbyps.multiply(new BigDecimal("0.001"));
							BigDecimal tbps = mbyps.multiply(new BigDecimal("0.000008"));
							BigDecimal tbyps = mbyps.multiply(new BigDecimal("0.000001"));

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMbyps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMbyps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherGbps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						try {
							BigDecimal gbps = new BigDecimal(s.toString());
							BigDecimal bps = gbps.multiply(new BigDecimal("1000000000"));
							BigDecimal byps = gbps.multiply(new BigDecimal("125000000"));
							BigDecimal kbps = gbps.multiply(new BigDecimal("1000000"));
							BigDecimal kbyps = gbps.multiply(new BigDecimal("125000"));
							BigDecimal mbps = gbps.multiply(new BigDecimal("1000"));
							BigDecimal mbyps = gbps.multiply(new BigDecimal("125"));
							BigDecimal gbyps = gbps.multiply(new BigDecimal("0.125"));
							BigDecimal tbps = gbps.multiply(new BigDecimal("0.001"));
							BigDecimal tbyps = gbps.multiply(new BigDecimal("0.000125"));

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherGbps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherGbps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherGbyps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						try {
							BigDecimal gbyps = new BigDecimal(s.toString());
							BigDecimal bps = gbyps.multiply(new BigDecimal("8000000000"));
							BigDecimal byps = gbyps.multiply(new BigDecimal("1000000000"));
							BigDecimal kbps = gbyps.multiply(new BigDecimal("8000000"));
							BigDecimal kbyps = gbyps.multiply(new BigDecimal("1000000"));
							BigDecimal mbps = gbyps.multiply(new BigDecimal("8000"));
							BigDecimal mbyps = gbyps.multiply(new BigDecimal("1000"));
							BigDecimal gbps = gbyps.multiply(new BigDecimal("8"));
							BigDecimal tbps = gbyps.multiply(new BigDecimal("0.008"));
							BigDecimal tbyps = gbyps.multiply(new BigDecimal("0.001"));

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherGbyps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherGbyps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherTbps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						try {
							BigDecimal tbps = new BigDecimal(s.toString());
							BigDecimal bps = tbps.multiply(new BigDecimal("1000000000000"));
							BigDecimal byps = tbps.multiply(new BigDecimal("125000000000"));
							BigDecimal kbps = tbps.multiply(new BigDecimal("1000000000"));
							BigDecimal kbyps = tbps.multiply(new BigDecimal("125000000"));
							BigDecimal mbps = tbps.multiply(new BigDecimal("1000000"));
							BigDecimal mbyps = tbps.multiply(new BigDecimal("125000"));
							BigDecimal gbps = tbps.multiply(new BigDecimal("1000"));
							BigDecimal gbyps = tbps.multiply(new BigDecimal("125"));
							BigDecimal tbyps = tbps.multiply(new BigDecimal("0.125"));

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextTbyps.setText(tbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherTbps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherTbps.s.after", "null");
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherTbyps.s.before", s.toString());
			}

			if (s.length() != 0) {
				s = Utils.sanitizeEditable(s);
				if (s != null) {
					if (Utils.isNumeric(s.toString())) {
						try {
							BigDecimal tbyps = new BigDecimal(s.toString());
							BigDecimal bps = tbyps.multiply(new BigDecimal("8000000000000"));
							BigDecimal byps = tbyps.multiply(new BigDecimal("1000000000000"));
							BigDecimal kbps = tbyps.multiply(new BigDecimal("8000000000"));
							BigDecimal kbyps = tbyps.multiply(new BigDecimal("1000000000"));
							BigDecimal mbps = tbyps.multiply(new BigDecimal("8000000"));
							BigDecimal mbyps = tbyps.multiply(new BigDecimal("1000000"));
							BigDecimal gbps = tbyps.multiply(new BigDecimal("8000"));
							BigDecimal gbyps = tbyps.multiply(new BigDecimal("1000"));
							BigDecimal tbps = tbyps.multiply(new BigDecimal("8"));

							editTextBps.setText(bps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														.stripTrailingZeros()
														.toPlainString()
													   , TextView.BufferType.EDITABLE);
							editTextByps.setText(byps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbps.setText(kbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextKbyps.setText(kbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextMbps.setText(mbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMbyps.setText(mbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextGbps.setText(gbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextGbyps.setText(gbyps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextTbps.setText(tbps.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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

			if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherTbyps.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherTbyps.s.after", "null");
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (Utils.isDebugEnabled(getActivity().getApplicationContext())) {
			Log.d(TAG, "Entered onCreateView");
		}

		View rootView = super.onCreateView(inflater, container, savedInstanceState);

		if (rootView != null) {
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
		}

        return rootView;
    }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_data_transfer_speed; }

	@Override
	protected int getScrollViewResource() { return  R.id.fragment_dts; }
}
