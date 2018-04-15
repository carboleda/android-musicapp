package co.devhack.musicapp.domain.usecase;

import co.devhack.musicapp.helpers.Callback;

/**
 * Created by krlosf on 15/04/18.
 */

public interface TrackUseCase {

    void love(String artist, String track, Callback<Boolean> callback);

    void unlove(String artist, String track, Callback<Boolean> callback);

}
