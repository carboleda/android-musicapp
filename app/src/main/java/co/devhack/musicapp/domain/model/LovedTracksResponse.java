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

    public class Track {
        private String name;
        private String url;
        private Artist artist;
        @SerializedName("image")
        private List<Image> images;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Artist getArtist() {
            return artist;
        }

        public void setArtist(Artist artist) {
            this.artist = artist;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }
    }

    public class Artist {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Image {
        @SerializedName("#text")
        private String url;
        private String size;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

}
