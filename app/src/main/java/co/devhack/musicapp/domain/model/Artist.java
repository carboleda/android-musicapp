package co.devhack.musicapp.domain.model;

import android.arch.persistence.room.ColumnInfo;

/**
 * Created by krlosf on 23/04/18.
 */

public class Artist {
    @ColumnInfo(name = "artist_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
