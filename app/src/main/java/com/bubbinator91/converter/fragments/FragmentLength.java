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
 * Inch, Foot, Yard, Mile, Millimeter, Centimeter, Meter, Kilometer
 * Conversions comply with the conversions through Google.com
 */

public class FragmentLength extends Fragment {
    private boolean DEBUG = false;
    private final String TAG = "FragmentLength";

	private SharedPreferences mPrefs;

	private int fieldLength = -1;

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherInch.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal inch = new BigDecimal(s.toString());
							BigDecimal foot = inch.divide(new BigDecimal("12")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal yard = foot.divide(new BigDecimal("3")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , fieldLength
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, fieldLength
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, fieldLength
																			, BigDecimal.ROUND_HALF_UP);

                            editTextFoot.setText(foot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextYard.setText(yard.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextMile.setText(mile.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(millimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(centimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
                            editTextMeter.setText(meter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
                            editTextKilometer.setText(kilometer.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherInch.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherInch.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherFoot.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal foot = new BigDecimal(s.toString());
							BigDecimal inch = foot.multiply(new BigDecimal("12"));
							BigDecimal yard = foot.divide(new BigDecimal("3")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , fieldLength
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, fieldLength
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, fieldLength
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherFoot.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherFoot.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherYard.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal yard = new BigDecimal(s.toString());
							BigDecimal inch = yard.multiply(new BigDecimal("36"));
							BigDecimal foot = yard.multiply(new BigDecimal("3"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , fieldLength
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, fieldLength
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, fieldLength
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherYard.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherYard.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherMile.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal mile = new BigDecimal(s.toString());
							BigDecimal inch = mile.multiply(new BigDecimal("63360"));
							BigDecimal foot = mile.multiply(new BigDecimal("5280"));
							BigDecimal yard = mile.multiply(new BigDecimal("1760"));
							BigDecimal millimeter = inch.multiply(new BigDecimal("25.4"));
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , fieldLength
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, fieldLength
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, fieldLength
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherMile.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherMile.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherMillimeter.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal millimeter = new BigDecimal(s.toString());
							BigDecimal inch = millimeter.multiply(new BigDecimal("0.03937007874015748031496062992126"));
							BigDecimal foot = millimeter.multiply(new BigDecimal("0.00328083989501312335958005249344"));
							BigDecimal yard = millimeter.multiply(new BigDecimal("0.00109361329833770778652668416448"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal centimeter = millimeter.divide(new BigDecimal("10")
																			 , fieldLength
																			 , BigDecimal.ROUND_HALF_UP);
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, fieldLength
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, fieldLength
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextCentimeter.setText(centimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextCentimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherMillimeter.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherMillimeter.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherCentimeter.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
							BigDecimal centimeter = new BigDecimal(s.toString());
							BigDecimal inch = centimeter.multiply(new BigDecimal("0.3937007874015748031496062992126"));
							BigDecimal foot = centimeter.multiply(new BigDecimal("0.0328083989501312335958005249344"));
							BigDecimal yard = centimeter.multiply(new BigDecimal("0.0109361329833770778652668416448"));
							BigDecimal mile = foot.divide(new BigDecimal("5280")
																 , fieldLength
																 , BigDecimal.ROUND_HALF_UP);
							BigDecimal millimeter = centimeter.multiply(new BigDecimal("10"));
							BigDecimal meter = millimeter.divide(new BigDecimal("1000")
																		, fieldLength
																		, BigDecimal.ROUND_HALF_UP);
							BigDecimal kilometer = millimeter.divide(new BigDecimal("1000000")
																			, fieldLength
																			, BigDecimal.ROUND_HALF_UP);

							editTextInch.setText(inch.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextFoot.setText(foot.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextYard.setText(yard.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMile.setText(mile.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														 .stripTrailingZeros()
														 .toPlainString()
														, TextView.BufferType.EDITABLE);
							editTextMillimeter.setText(millimeter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
															   .stripTrailingZeros()
															   .toPlainString()
															  , TextView.BufferType.EDITABLE);
							editTextMeter.setText(meter.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
														  .stripTrailingZeros()
														  .toPlainString()
														 , TextView.BufferType.EDITABLE);
							editTextKilometer.setText(kilometer.setScale(fieldLength, BigDecimal.ROUND_HALF_UP)
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
                editTextInch.setText("", TextView.BufferType.EDITABLE);
                editTextFoot.setText("", TextView.BufferType.EDITABLE);
                editTextYard.setText("", TextView.BufferType.EDITABLE);
                editTextMile.setText("", TextView.BufferType.EDITABLE);
                editTextMillimeter.setText("", TextView.BufferType.EDITABLE);
                editTextMeter.setText("", TextView.BufferType.EDITABLE);
                editTextKilometer.setText("", TextView.BufferType.EDITABLE);
            }

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherCentimeter.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherCentimeter.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherMeter.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double meter = Double.parseDouble(s.toString());
                            double inch = (meter * 39.37007874015748031496062992126);
                            double foot = (meter * 3.28083989501312335958005249344);
                            double yard = (meter * 1.09361329833770778652668416448);
                            double mile = (meter * 0.00062137119223733396961743418436332);
                            double millimeter = (meter * 1000.0);
                            double centimeter = (meter * 100.0);
                            double kilometer = (meter / 1000.0);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextKilometer.setText(Double.toString(kilometer), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
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

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherMeter.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherMeter.s.after", "null");

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

            if (DEBUG)
                Log.d(TAG + ".textWatcherKilometer.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double kilometer = Double.parseDouble(s.toString());
                            double inch = (kilometer * 39370.07874015748031496062992126);
                            double foot = (kilometer * 3280.83989501312335958005249344);
                            double yard = (kilometer * 1093.61329833770778652668416448);
                            double mile = (kilometer * 0.62137119223733396961743418436332);
                            double millimeter = (kilometer * 1000000.0);
                            double centimeter = (kilometer * 100000.0);
                            double meter = (kilometer * 1000.0);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
                        } catch (NumberFormatException e) {
                            if (DEBUG)
                                e.printStackTrace();
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

            if (DEBUG && (s != null))
                Log.d(TAG + ".textWatcherKilometer.s.after", s.toString());
            else if (DEBUG && (s == null))
                Log.d(TAG + ".textWatcherKilometer.s.after", "null");

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
		if (DEBUG)
			Log.d(TAG + "onCreateView", "Entered");

		mPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

		if (mPrefs != null) {
			fieldLength = mPrefs.getInt(Util.PREFERENCE_FIELD_LENGTH, -1);
			if (mPrefs.getInt(Util.PREFERENCE_DEBUG, -1) == 1)
				DEBUG = true;
		}
		if (fieldLength == -1)
			fieldLength = 8;

        View rootView = inflater.inflate(R.layout.fragment_length, container, false);

        editTextInch = ((EditText) rootView.findViewById(R.id.editText_length_inch));
        editTextFoot = ((EditText) rootView.findViewById(R.id.editText_length_foot));
        editTextYard = ((EditText) rootView.findViewById(R.id.editText_length_yard));
        editTextMile = ((EditText) rootView.findViewById(R.id.editText_length_mile));
        editTextMillimeter = ((EditText) rootView.findViewById(R.id.editText_length_millimeter));
        editTextCentimeter = ((EditText) rootView.findViewById(R.id.editText_length_centimeter));
        editTextMeter = ((EditText) rootView.findViewById(R.id.editText_length_meter));
        editTextKilometer = ((EditText) rootView.findViewById(R.id.editText_length_kilometer));

        editTextInch.addTextChangedListener(textWatcherInch);
        editTextFoot.addTextChangedListener(textWatcherFoot);
        editTextYard.addTextChangedListener(textWatcherYard);
        editTextMile.addTextChangedListener(textWatcherMile);
        editTextMillimeter.addTextChangedListener(textWatcherMillimeter);
        editTextCentimeter.addTextChangedListener(textWatcherCentimeter);
        editTextMeter.addTextChangedListener(textWatcherMeter);
        editTextKilometer.addTextChangedListener(textWatcherKilometer);

        return rootView;
    }
}
