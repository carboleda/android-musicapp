package co.devhack.musicapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krlosf on 23/04/18.
 */

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
