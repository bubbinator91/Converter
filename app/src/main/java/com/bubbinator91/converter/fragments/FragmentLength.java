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

public class FragmentLength extends Fragment {
    private final boolean DEBUG = false;
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

            if (DEBUG)
                Log.d(TAG + ".textWatcherInch.s.before", s.toString());

            if (s.length() != 0) {
                s = Util.sanitizeEditable(s);
                if (s != null) {
                    if (Util.isNumeric(s.toString())) {
                        try {
                            double inch = Double.parseDouble(s.toString());
                            double foot = (inch / 12.0);
                            double yard = ((inch / 12.0) / 3.0);
                            double mile = ((inch / 12.0) / 5280.0);
                            double millimeter = (inch / 0.03937007874015748031496062992126);
                            double centimeter = (inch / 0.3937007874015748031496062992126);
                            double meter = (inch / 39.37007874015748031496062992126);
                            double kilometer = (inch / 39370.07874015748031496062992126);

                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
                            editTextKilometer.setText(Double.toString(kilometer), TextView.BufferType.EDITABLE);
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
                            double foot = Double.parseDouble(s.toString());
                            double inch = (foot * 12.0);
                            double yard = (foot / 3.0);
                            double mile = (foot / 5280.0);
                            double millimeter = (foot / 0.00328083989501312335958005249344);
                            double centimeter = (foot / 0.0328083989501312335958005249344);
                            double meter = (foot / 3.28083989501312335958005249344);
                            double kilometer = (foot / 3280.83989501312335958005249344);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
                            editTextKilometer.setText(Double.toString(kilometer), TextView.BufferType.EDITABLE);
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
                            double yard = Double.parseDouble(s.toString());
                            double inch = (yard * 36.0);
                            double foot = (yard * 3.0);
                            double mile = (yard / 1760.0);
                            double millimeter = (yard / 0.00109361329833770778652668416448);
                            double centimeter = (yard / 0.0109361329833770778652668416448);
                            double meter = (yard / 1.09361329833770778652668416448);
                            double kilometer = (yard / 1093.61329833770778652668416448);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
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
                            double mile = Double.parseDouble(s.toString());
                            double inch = (mile * 63360.0);
                            double foot = (mile * 5280.0);
                            double yard = (mile * 1760.0);
                            double millimeter = (mile / 0.00000062137119223733396961743418436332);
                            double centimeter = (mile / 0.0000062137119223733396961743418436332);
                            double meter = (mile / 0.00062137119223733396961743418436332);
                            double kilometer = (mile / 0.62137119223733396961743418436332);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
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
                            double millimeter = Double.parseDouble(s.toString());
                            double inch = (millimeter * 0.03937007874015748031496062992126);
                            double foot = (millimeter * 0.00328083989501312335958005249344);
                            double yard = (millimeter * 0.00109361329833770778652668416448);
                            double mile = (millimeter * 0.00000062137119223733396961743418436332);
                            double centimeter = (millimeter / 10.0);
                            double meter = (millimeter / 1000.0);
                            double kilometer = (millimeter / 1000000.0);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextCentimeter.setText(Double.toString(centimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
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
                            double centimeter = Double.parseDouble(s.toString());
                            double inch = (centimeter * 0.3937007874015748031496062992126);
                            double foot = (centimeter * 0.0328083989501312335958005249344);
                            double yard = (centimeter * 0.0109361329833770778652668416448);
                            double mile = (centimeter * 0.0000062137119223733396961743418436332);
                            double millimeter = (centimeter * 10.0);
                            double meter = (centimeter / 100.0);
                            double kilometer = (centimeter / 100000.0);

                            editTextInch.setText(Double.toString(inch), TextView.BufferType.EDITABLE);
                            editTextFoot.setText(Double.toString(foot), TextView.BufferType.EDITABLE);
                            editTextYard.setText(Double.toString(yard), TextView.BufferType.EDITABLE);
                            editTextMile.setText(Double.toString(mile), TextView.BufferType.EDITABLE);
                            editTextMillimeter.setText(Double.toString(millimeter), TextView.BufferType.EDITABLE);
                            editTextMeter.setText(Double.toString(meter), TextView.BufferType.EDITABLE);
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
        View rootView = inflater.inflate(R.layout.fragment_length, container, false);

        editTextInch = ((EditText)rootView.findViewById(R.id.editText_length_inch));
        editTextFoot = ((EditText)rootView.findViewById(R.id.editText_length_foot));
        editTextYard = ((EditText)rootView.findViewById(R.id.editText_length_yard));
        editTextMile = ((EditText)rootView.findViewById(R.id.editText_length_mile));
        editTextMillimeter = ((EditText)rootView.findViewById(R.id.editText_length_millimeter));
        editTextCentimeter = ((EditText)rootView.findViewById(R.id.editText_length_centimeter));
        editTextMeter = ((EditText)rootView.findViewById(R.id.editText_length_meter));
        editTextKilometer = ((EditText)rootView.findViewById(R.id.editText_length_kilometer));

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
