package co.devhack.musicapp.repository.impl;

import android.content.SharedPreferences;

import java.util.Map;

import co.devhack.musicapp.helpers.SharedPreferencesUtil;
import co.devhack.musicapp.repository.PrefRepository;

/**
 * Created by krlosf on 25/04/18.
 */

public class PrefRepositoryImpl implements PrefRepository {

    @Override
    public <V> boolean setData(String key, V value) throws Exception {
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getInstance();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(value == null) {
            editor.remove(key);
        } else if(value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if(value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if(value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if(value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if(value instanceof String) {
            editor.putString(key, (String) value);
        }

        return editor.commit();
    }

    @Override
    public <V> V getData(String key, V defaultValue, Class<V> type) throws Exception {
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getInstance();
        Map<String, Object> mapPreferences = (Map<String, Object>) sharedPreferences.getAll();
        Object value = mapPreferences.get(key);

        try {
            if(value != null) {
                //Ejemplo: Integer.class.cast(value);
                return type.cast(value);
            }

            return defaultValue;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void clearAllData() throws Exception {
        try {
            SharedPreferences sharedPreferences = SharedPreferencesUtil.getInstance();
            sharedPreferences.edit().clear().commit();
        } catch (Exception e) {
            throw e;
        }
    }

}
