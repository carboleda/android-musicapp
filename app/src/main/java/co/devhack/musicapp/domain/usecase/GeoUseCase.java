package co.devhack.musicapp.domain.usecase;

import java.util.List;

import co.devhack.musicapp.domain.model.TopTracksResponse;
import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.helpers.Callback;

/**
 * Created by krlosf on 23/04/18.
 */

public interface GeoUseCase {

    void getTopTracks(String country, Callback<List<Track>> callback);

}
