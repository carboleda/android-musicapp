package co.devhack.musicapp.repository;

import co.devhack.musicapp.domain.model.TopTracksResponse;

/**
 * Created by krlosf on 23/04/18.
 */

public interface GeoRepository {

    TopTracksResponse getTopTracks(String country) throws Exception;

}
