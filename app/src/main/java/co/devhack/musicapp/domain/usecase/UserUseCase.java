package co.devhack.musicapp.domain.usecase;

import co.devhack.musicapp.domain.model.LovedTracksResponse;
import co.devhack.musicapp.helpers.Callback;

/**
 * Created by krlosf on 23/04/18.
 */

public interface UserUseCase {

    void getLovedTracks(String user, Callback<LovedTracksResponse> callback);

}
