package co.devhack.musicapp.domain.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by krlosf on 23/04/18.
 */
@Entity
public class Track {
    @PrimaryKey
    @NonNull
    private String name;
    @ColumnInfo
    private String url;
    @ColumnInfo
    private Integer duration;
    @ColumnInfo
    private Integer listeners;
    @Embedded
    private Artist artist;
    @Ignore
    @SerializedName("image")
    private List<Image> images;

    //Este atributo no lo devuelve last.fm, lo usaremos para la logica propia del app
    //Si es true ya le gusta al usuario; si es false aun no le gusta al usuario
    private boolean love;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
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

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }
}
