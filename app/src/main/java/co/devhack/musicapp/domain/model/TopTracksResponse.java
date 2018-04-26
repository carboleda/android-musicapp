package co.devhack.musicapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krlosf on 23/04/18.
 */

public class TopTracksResponse {

    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public class Tracks {

        @SerializedName("track")
        private List<Track> lstTracks;

        public List<Track> getLstTracks() {
            return lstTracks;
        }

        public void setLstTracks(List<Track> lstTracks) {
            this.lstTracks = lstTracks;
        }
    }

}
