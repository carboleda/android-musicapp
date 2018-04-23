package co.devhack.musicapp.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by krlosf on 23/04/18.
 */

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
