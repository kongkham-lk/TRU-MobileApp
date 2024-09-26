package com.example.calculatorapp;


import android.text.InputFilter;
import android.text.Spanned;

public class DecimalInputFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String input = dest.toString() + source.toString();

        // Allow only one decimal point
        if (input.contains(".") && input.substring(input.indexOf(".") + 1).contains(".")) {
            return "";
        }

        // Allow only one negative sign and it should be at the start
        if (input.contains("-") && (input.indexOf("-") != 0 || input.lastIndexOf("-") != 0)) {
            return "";
        }

        return null;
    }
}
