package com.bubbinator91.converter.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bubbinator91.converter.R;
import com.bubbinator91.converter.util.Utils;

import java.math.BigDecimal;
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
				new Thread(new ConversionFromFpsRunnable(editTextFps.getText(), "onResume")).start();
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KNOT) {
			if ((getHandler() != null) && (editTextKnot.getText() != null)) {
				new Thread(new ConversionFromKnotRunnable(editTextKnot.getText(), "onResume")).start();
			}
		} else if (lastEditTextFocused == LastEditTextFocused.KPH) {
			if ((getHandler() != null) && (editTextKph.getText() != null)) {
				new Thread(new ConversionFromKphRunnable(editTextKph.getText(), "onResume")).start();
			}
		} else if (lastEditTextFocused == LastEditTextFocused.MPS) {
			if ((getHandler() != null) && (editTextMps.getText() != null)) {
				new Thread(new ConversionFromMpsRunnable(editTextMps.getText(), "onResume")).start();
			}
		} else if (lastEditTextFocused == LastEditTextFocused.MPH) {
			if ((getHandler() != null) && (editTextMph.getText() != null)) {
				new Thread(new ConversionFromMphRunnable(editTextMph.getText(), "onResume")).start();
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

		public ConversionFromFpsRunnable(Editable editableFps, String callingClassName) {
			mEditableFps = editableFps;
			mCallingClassName = callingClassName;
		}

		@Override
		public void run() {
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("before = " + mEditableFps.toString());

			final ArrayList<String> results = new ArrayList<>();
			if (mEditableFps.length() != 0) {
				mEditableFps = Utils.sanitizeEditable(mEditableFps);
				if (mEditableFps != null) {
					if (Utils.isNumeric(mEditableFps.toString())) {
						try {
							BigDecimal fps = new BigDecimal(mEditableFps.toString());
							BigDecimal mph = fps.multiply(new BigDecimal(60*60))
									.divide(new BigDecimal("5280")
											, getFieldLength()
											, BigDecimal.ROUND_HALF_UP);
							BigDecimal mps = fps.multiply(new BigDecimal("0.3048"));
							BigDecimal kph = fps.multiply(new BigDecimal("1.09728"));
							BigDecimal knot = fps.multiply(new BigDecimal(".592484"));

							results.add(knot
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(kph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
						} catch (NumberFormatException e) {
							Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
							addWhitespaceItems(results, 4);
						}
					} else {
						addWhitespaceItems(results, 4);
					}
				} else {
					addWhitespaceItems(results, 4);
				}
			} else {
				addWhitespaceItems(results, 4);
			}

			if (mEditableFps != null) {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = " + mEditableFps.toString());
			} else {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = null");
			}

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

	private class ConversionFromKnotRunnable implements Runnable {
		private final String TAG = "ConversionFromKnotRunnable";

		private Editable mEditableKnot;
		private String mCallingClassName;

		public ConversionFromKnotRunnable(Editable editableKnot, String callingClassName) {
			mEditableKnot = editableKnot;
			mCallingClassName = callingClassName;
		}

		@Override
		public void run() {
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("before = " + mEditableKnot.toString());

			final ArrayList<String> results = new ArrayList<>();
			if (mEditableKnot.length() != 0) {
				mEditableKnot = Utils.sanitizeEditable(mEditableKnot);
				if (mEditableKnot != null) {
					if (Utils.isNumeric(mEditableKnot.toString())) {
						try {
							BigDecimal knot = new BigDecimal(mEditableKnot.toString());
							BigDecimal fps = knot.divide(new BigDecimal(".592484")
									, getFieldLength()
									, BigDecimal.ROUND_HALF_UP);
							BigDecimal mph = knot.multiply(new BigDecimal("1.150779"));
							BigDecimal mps = knot.multiply(new BigDecimal(".514444"));
							BigDecimal kph = knot.multiply(new BigDecimal("1.852"));

							results.add(fps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(kph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
						} catch (NumberFormatException e) {
							Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
							addWhitespaceItems(results, 4);
						}
					} else {
						addWhitespaceItems(results, 4);
					}
				} else {
					addWhitespaceItems(results, 4);
				}
			} else {
				addWhitespaceItems(results, 4);
			}

			if (mEditableKnot != null) {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = " + mEditableKnot.toString());
			} else {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = null");
			}

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

	private class ConversionFromKphRunnable implements Runnable {
		private final String TAG = "ConversionFromKphRunnable";

		private Editable mEditableKph;
		private String mCallingClassName;

		public ConversionFromKphRunnable(Editable editableKph, String callingClassName) {
			mEditableKph = editableKph;
			mCallingClassName = callingClassName;
		}

		@Override
		public void run() {
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("before = " + mEditableKph.toString());

			final ArrayList<String> results = new ArrayList<>();
			if (mEditableKph.length() != 0) {
				mEditableKph = Utils.sanitizeEditable(mEditableKph);
				if (mEditableKph != null) {
					if (Utils.isNumeric(mEditableKph.toString())) {
						try {
							BigDecimal kph = new BigDecimal(mEditableKph.toString());
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

							results.add(fps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(knot
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
						} catch (NumberFormatException e) {
							Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
							addWhitespaceItems(results, 4);
						}
					} else {
						addWhitespaceItems(results, 4);
					}
				} else {
					addWhitespaceItems(results, 4);
				}
			} else {
				addWhitespaceItems(results, 4);
			}

			if (mEditableKph != null) {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = " + mEditableKph.toString());
			} else {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = null");
			}

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

	private class ConversionFromMpsRunnable implements Runnable {
		private final String TAG = "ConversionFromMpsRunnable";

		private Editable mEditableMps;
		private String mCallingClassName;

		public ConversionFromMpsRunnable(Editable editableMps, String callingClassName) {
			mEditableMps = editableMps;
			mCallingClassName = callingClassName;
		}

		@Override
		public void run() {
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("before = " + mEditableMps.toString());

			final ArrayList<String> results = new ArrayList<>();
			if (mEditableMps.length() != 0) {
				mEditableMps = Utils.sanitizeEditable(mEditableMps);
				if (mEditableMps != null) {
					if (Utils.isNumeric(mEditableMps.toString())) {
						try {
							BigDecimal mps = new BigDecimal(mEditableMps.toString());
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

							results.add(fps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(knot
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(kph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
						} catch (NumberFormatException e) {
							Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
							addWhitespaceItems(results, 4);
						}
					} else {
						addWhitespaceItems(results, 4);
					}
				} else {
					addWhitespaceItems(results, 4);
				}
			} else {
				addWhitespaceItems(results, 4);
			}

			if (mEditableMps != null) {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = " + mEditableMps.toString());
			} else {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = null");
			}

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

	private class ConversionFromMphRunnable implements Runnable {
		private final String TAG = "ConversionFromMphRunnable";

		private Editable mEditableMph;
		private String mCallingClassName;

		public ConversionFromMphRunnable(Editable editableMph, String callingClassName) {
			mEditableMph = editableMph;
			mCallingClassName = callingClassName;
		}

		@Override
		public void run() {
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("Entered");
			Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("before = " + mEditableMph.toString());

			final ArrayList<String> results = new ArrayList<>();
			if (mEditableMph.length() != 0) {
				mEditableMph = Utils.sanitizeEditable(mEditableMph);
				if (mEditableMph != null) {
					if (Utils.isNumeric(mEditableMph.toString())) {
						try {
							BigDecimal mph = new BigDecimal(mEditableMph.toString());
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

							results.add(fps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(knot
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(kph
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
							results.add(mps
									.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
									.stripTrailingZeros()
									.toPlainString());
						} catch (NumberFormatException e) {
							Timber.tag(mCallingClassName + "." + this.TAG + ".run").e(e.getMessage());
							addWhitespaceItems(results, 4);
						}
					} else {
						addWhitespaceItems(results, 4);
					}
				} else {
					addWhitespaceItems(results, 4);
				}
			} else {
				addWhitespaceItems(results, 4);
			}

			if (mEditableMph != null) {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = " + mEditableMph.toString());
			} else {
				Timber.tag(mCallingClassName + "." + this.TAG + ".run").i("after = null");
			}

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

	// endregion
}