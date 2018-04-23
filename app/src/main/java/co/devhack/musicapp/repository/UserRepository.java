package co.devhack.musicapp.repository;

import co.devhack.musicapp.domain.model.LovedTracksResponse;

/**
 * Created by krlosf on 23/04/18.
 */

public interface UserRepository {

    LovedTracksResponse getLovedTracks(String user) throws Exception;

}
