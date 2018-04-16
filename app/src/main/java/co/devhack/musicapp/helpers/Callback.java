package co.devhack.musicapp.helpers;

/**
 * Created by krlosf on 2/12/17.
 */

public interface Callback<T> {
    void success(T result);

    void error(Throwable error);
}