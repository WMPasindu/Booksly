package com.pasindu.dev.booksly.common;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Common {
    public static String formatPrice(double price) {
        if (price != 0) {
            DecimalFormat df = new DecimalFormat("#,##0.00");
            df.setRoundingMode(RoundingMode.UP);
            String finalPrice = new StringBuilder(df.format(price)).toString();
            return finalPrice.replace(".", ",");
        } else
            return "0,00";
    }

    public static String formatRatings(int Ratings) {
        if (Ratings != 0) {
            DecimalFormat df = new DecimalFormat("#,##0.0");
            df.setRoundingMode(RoundingMode.UP);
            String finalPrice = new StringBuilder(df.format(Ratings)).toString();
            return finalPrice.replace(".", ",");
        } else
            return "0.0";
    }
}
