package co.devhack.musicapp.helpers;

import android.support.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by krlosf on 15/04/18.
 */

public class StringUtilities {
    public static boolean isEmpty(String sb) {
        return sb == null || sb.equals("") || sb.trim().length() == 0;
    }

    public static String md5(@NonNull String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Metodo para convertir segundos a minutos y aplicar un formato.
     * Ej: 272 => 04:53
     * @param second segundos
     * @return
     */
    public static String formatSecondsToDuration(int second) {
        String minutes = String.format(Locale.getDefault(), "0%.2f", (double)(second/60.0F));
        return minutes.replace(",", ":").replace(".", ":");
    }

    /**
     * https://stackoverflow.com/questions/9769554/how-to-convert-number-into-k-thousands-m-million-and-b-billion-suffix-in-jsp
     * @param count numero a convertir
     * @return
     */
    public static String numberToSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format(Locale.getDefault(), "%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }
}
