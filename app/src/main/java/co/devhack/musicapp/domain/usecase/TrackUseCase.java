package co.devhack.musicapp.domain.usecase;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.helpers.Callback;

/**
 * Created by krlosf on 15/04/18.
 */

public interface TrackUseCase {

    void loveUnlove(Track track, Callback<Boolean> callback);

    void love(Track track, Callback<Boolean> callback);

    void unlove(Track track, Callback<Boolean> callback);

    void getInfo(String artist, String track, Callback<Track> callback);

}
