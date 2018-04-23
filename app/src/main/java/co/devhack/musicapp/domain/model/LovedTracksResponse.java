package co.devhack.musicapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krlosf on 20/04/18.
 */

public class LovedTracksResponse {

    @SerializedName("lovedtracks")
    private LovedTracks lovedTracks;

    public LovedTracks getLovedTracks() {
        return lovedTracks;
    }

    public void setLovedTracks(LovedTracks lovedTracks) {
        this.lovedTracks = lovedTracks;
    }

    public class LovedTracks {
        @SerializedName("track")
        private List<Track> tracks;

        public List<Track> getTracks() {
            return tracks;
        }

        public void setTracks(List<Track> tracks) {
            this.tracks = tracks;
        }
    }

}
