package co.devhack.musicapp.repository;

import java.util.List;

import co.devhack.musicapp.domain.model.Track;

/**
 * Created by krlosf on 23/04/18.
 */

public interface GeoRepository {

    List<Track> getTopTracks(String country) throws Exception;

    void insertTopTracks(List<Track> lstTracks) throws Exception;

}
