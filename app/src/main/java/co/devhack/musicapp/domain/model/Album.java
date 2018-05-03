package co.devhack.musicapp.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krlosf on 29/04/18.
 */

public class Album {
    @SerializedName("artist")
    private String artistName;
    private String title;
    private String url;
    @SerializedName("image")
    private List<Image> lstImages;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Image> getLstImages() {
        return lstImages;
    }

    public void setLstImages(List<Image> lstImages) {
        this.lstImages = lstImages;
    }
}
