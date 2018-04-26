package co.devhack.musicapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by krlosf on 25/04/18.
 */

public class SharedPreferencesUtil {

    private static SharedPreferences sharedPreferences;

    /**
     * Permite inicializar el SharedPreferencesUtil la inicio de la aplicación
     * y asi evitar tener que pasar el Context por multiples clases de la aplicación
     * @param context
     */
    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("", Context.MODE_PRIVATE);
    }

    public static SharedPreferences getInstance() {
        return sharedPreferences;
    }

}
