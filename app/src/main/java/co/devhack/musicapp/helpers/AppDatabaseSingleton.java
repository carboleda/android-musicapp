package co.devhack.musicapp.helpers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.repository.impl.GeoRepositorySQLite;

/**
 * Created by krlosf on 27/04/18.
 */
@Database(entities = {Track.class}, version = 1)
public abstract class AppDatabaseSingleton extends RoomDatabase {

    private static AppDatabaseSingleton db;

    public static void init(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabaseSingleton.class, "music-app").build();
        }
    }

    public static AppDatabaseSingleton getInstance() {
        return db;
    }

    public abstract GeoRepositorySQLite.TrackDao geoTrackDao();

}
