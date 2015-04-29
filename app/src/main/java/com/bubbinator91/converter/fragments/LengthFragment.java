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
 * Inch, Foot, Yard, Mile, Millimeter, Centimeter, Meter, Kilometer
 * Conversions comply with the conversions through Google.com
 */

public class LengthFragment extends BaseFragment {
    private final String TAG = "FragmentLength";

    private EditText editTextInch, editTextFoot, editTextYard, editTextMile, editTextMillimeter,
                    editTextCentimeter, editTextMeter, editTextKilometer;

    private TextWatcher textWatcherInch = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherInch.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal inch = new BigDecimal(s.toString());
							BigDecimal foot = inch.divide(new BigDecimal("12")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal yard = foot.divide(new BigDecimal("3")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , getFieldLength()
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, getFieldLength()
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

                            editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
                            editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
                            editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherInch.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherInch.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherFoot = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherFoot.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal foot = new BigDecimal(s.toString());
							BigDecimal inch = foot.multiply(new BigDecimal("12"));
							BigDecimal yard = foot.divide(new BigDecimal("3")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , getFieldLength()
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, getFieldLength()
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherFoot.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherFoot.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherYard = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherYard.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal yard = new BigDecimal(s.toString());
							BigDecimal inch = yard.multiply(new BigDecimal("36"));
							BigDecimal foot = yard.multiply(new BigDecimal("3"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , getFieldLength()
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, getFieldLength()
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherYard.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherYard.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMile = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMile.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal mile = new BigDecimal(s.toString());
							BigDecimal inch = mile.multiply(new BigDecimal("63360"));
							BigDecimal foot = mile.multiply(new BigDecimal("5280"));
							BigDecimal yard = mile.multiply(new BigDecimal("1760"));
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , getFieldLength()
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, getFieldLength()
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMile.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMile.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMillimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMillimeter.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal millimeter = new BigDecimal(s.toString());
							BigDecimal inch = millimeter.multiply(new BigDecimal("0.03937007874015748031496062992126"));
							BigDecimal foot = millimeter.multiply(new BigDecimal("0.00328083989501312335958005249344"));
							BigDecimal yard = millimeter.multiply(new BigDecimal("0.00109361329833770778652668416448"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , getFieldLength()
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, getFieldLength()
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMillimeter.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMillimeter.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherCentimeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherCentimeter.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal centimeter = new BigDecimal(s.toString());
							BigDecimal inch = centimeter.multiply(new BigDecimal("0.3937007874015748031496062992126"));
							BigDecimal foot = centimeter.multiply(new BigDecimal("0.0328083989501312335958005249344"));
							BigDecimal yard = centimeter.multiply(new BigDecimal("0.0109361329833770778652668416448"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = centimeter.multiply(new BigDecimal("10"));
							BigDecimal meter = centimeter.divide(new BigDecimal("100")
																		, getFieldLength()
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = centimeter.divide(new BigDecimal("100000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherCentimeter.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherCentimeter.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherMeter = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherMeter.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal meter = new BigDecimal(s.toString());
							BigDecimal inch = meter.multiply(new BigDecimal("39.37007874015748031496062992126"));
							BigDecimal foot = meter.multiply(new BigDecimal("3.28083989501312335958005249344"));
							BigDecimal yard = meter.multiply(new BigDecimal("1.09361329833770778652668416448"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = meter.multiply(new BigDecimal("1000"));
							BigDecimal centimeter = meter.multiply(new BigDecimal("100"));
							BigDecimal kilometer = meter.divide(new BigDecimal("1000")
																			, getFieldLength()
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherMeter.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherMeter.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
    };

    private TextWatcher textWatcherKilometer = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            editTextInch.removeTextChangedListener(textWatcherInch);
            editTextFoot.removeTextChangedListener(textWatcherFoot);
            editTextYard.removeTextChangedListener(textWatcherYard);
            editTextMile.removeTextChangedListener(textWatcherMile);
            editTextMillimeter.removeTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.removeTextChangedListener(textWatcherCentimeter);
            editTextMeter.removeTextChangedListener(textWatcherMeter);
            editTextKilometer.removeTextChangedListener(textWatcherKilometer);

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext())) {
				Log.d(TAG + ".textWatcherKilometer.s.before", s.toString());
			}

            if (s.length() != 0) {
                s = Utils.sanitizeEditable(s);
                if (s != null) {
                    if (Utils.isNumeric(s.toString())) {
                        try {
							BigDecimal kilometer = new BigDecimal(s.toString());
							BigDecimal inch = kilometer.multiply(new BigDecimal("39370.07874015748031496062992126"));
							BigDecimal foot = kilometer.multiply(new BigDecimal("3280.83989501312335958005249344"));
							BigDecimal yard = kilometer.multiply(new BigDecimal("1093.61329833770778652668416448"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , getFieldLength()
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = kilometer.multiply(new BigDecimal("1000000"));
							BigDecimal centimeter = kilometer.multiply(new BigDecimal("100000"));
							BigDecimal meter = kilometer.multiply(new BigDecimal("1000"));

							editTextInch.setText(inch.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(getFieldLength(), BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
            }

            if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s != null)) {
				Log.d(TAG + ".textWatcherKilometer.s.after", s.toString());
			} else if (Utils.isDebugEnabled(getCurrentActivity().getApplicationContext()) && (s == null)) {
				Log.d(TAG + ".textWatcherKilometer.s.after", "null");
			}

            editTextInch.addTextChangedListener(textWatcherInch);
            editTextFoot.addTextChangedListener(textWatcherFoot);
            editTextYard.addTextChangedListener(textWatcherYard);
            editTextMile.addTextChangedListener(textWatcherMile);
            editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
            editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
            editTextMeter.addTextChangedListener(textWatcherMeter);
            editTextKilometer.addTextChangedListener(textWatcherKilometer);
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

		if (getRootView() != null) {
			Typeface tf = Typeface.createFromAsset(getCurrentActivity().getAssets(), "fonts/Roboto-Regular.ttf");

			TextView textViewInch = ((TextView) getRootView().findViewById(R.id.textView_length_inch));
			TextView textViewFoot = ((TextView) getRootView().findViewById(R.id.textView_length_foot));
			TextView textViewYard = ((TextView) getRootView().findViewById(R.id.textView_length_yard));
			TextView textViewMile = ((TextView) getRootView().findViewById(R.id.textView_length_mile));
			TextView textViewMillimeter = ((TextView) getRootView().findViewById(R.id.textView_length_millimeter));
			TextView textViewCentimeter = ((TextView) getRootView().findViewById(R.id.textView_length_centimeter));
			TextView textViewMeter = ((TextView) getRootView().findViewById(R.id.textView_length_meter));
			TextView textViewKilometer = ((TextView) getRootView().findViewById(R.id.textView_length_kilometer));

			textViewInch.setTypeface(tf);
			textViewFoot.setTypeface(tf);
			textViewYard.setTypeface(tf);
			textViewMile.setTypeface(tf);
			textViewMillimeter.setTypeface(tf);
			textViewCentimeter.setTypeface(tf);
			textViewMeter.setTypeface(tf);
			textViewKilometer.setTypeface(tf);

			editTextInch = ((EditText) getRootView().findViewById(R.id.editText_length_inch));
			editTextFoot = ((EditText) getRootView().findViewById(R.id.editText_length_foot));
			editTextYard = ((EditText) getRootView().findViewById(R.id.editText_length_yard));
			editTextMile = ((EditText) getRootView().findViewById(R.id.editText_length_mile));
			editTextMillimeter = ((EditText) getRootView().findViewById(R.id.editText_length_millimeter));
			editTextCentimeter = ((EditText) getRootView().findViewById(R.id.editText_length_centimeter));
			editTextMeter = ((EditText) getRootView().findViewById(R.id.editText_length_meter));
			editTextKilometer = ((EditText) getRootView().findViewById(R.id.editText_length_kilometer));

			editTextInch.addTextChangedListener(textWatcherInch);
			editTextFoot.addTextChangedListener(textWatcherFoot);
			editTextYard.addTextChangedListener(textWatcherYard);
			editTextMile.addTextChangedListener(textWatcherMile);
			editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
			editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
			editTextMeter.addTextChangedListener(textWatcherMeter);
			editTextKilometer.addTextChangedListener(textWatcherKilometer);
		}

        return getRootView();
    }

	@Override
	protected String getChildTag() { return TAG; }

	@Override
	protected int getLayoutResource() { return R.layout.fragment_length; }

	@Override
	protected int getScrollViewResource() { return  R.id.fragment_length; }
}
