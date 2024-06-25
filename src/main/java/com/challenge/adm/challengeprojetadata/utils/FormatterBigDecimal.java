package com.challenge.adm.challengeprojetadata.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatterBigDecimal {
    public static String forFormatBrazil(BigDecimal number) {
        NumberFormat numberFormatter = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        numberFormatter.setMinimumFractionDigits(2);
        numberFormatter.setMaximumFractionDigits(2);
        return numberFormatter.format(number);
    }
}
