package co.devhack.musicapp.repository;

import java.util.List;

import co.devhack.musicapp.domain.model.LovedTracksResponse;
import co.devhack.musicapp.domain.model.Track;

/**
 * Created by krlosf on 23/04/18.
 */

public interface UserRepository {

    List<Track> getLovedTracks(String user) throws Exception;

}
