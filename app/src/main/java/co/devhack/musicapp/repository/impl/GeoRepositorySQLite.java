package co.devhack.musicapp.repository.impl;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.helpers.AppDatabaseSingleton;
import co.devhack.musicapp.repository.GeoRepository;

/**
 * Created by krlosf on 27/04/18.
 */

public class GeoRepositorySQLite implements GeoRepository {

    //Dao de Room
    @Dao
    public interface TrackDao {
        @Query("SELECT * FROM track")
        List<Track> getAll();

        @Insert
        void insertAll(Track... tracks);

        @Delete()
        void deletedAll(List<Track> topTracks);

    }

    @Override
    public List<Track> getTopTracks(String country) throws Exception {
        AppDatabaseSingleton db = AppDatabaseSingleton.getInstance();
        TrackDao dao = db.geoTrackDao();
        return dao.getAll();
    }

    @Override
    public void deleteTopTracks() throws Exception {
        AppDatabaseSingleton db = AppDatabaseSingleton.getInstance();
        TrackDao dao = db.geoTrackDao();

        dao.deletedAll(dao.getAll());
    }

    @Override
    public void insertTopTracks(List<Track> lstTracks) throws Exception {
        AppDatabaseSingleton db = AppDatabaseSingleton.getInstance();
        TrackDao dao = db.geoTrackDao();
        //Convertir el List a un Array []
        Track[] arrTracks = new Track[lstTracks.size()];
        arrTracks = lstTracks.toArray(arrTracks);

        dao.insertAll(arrTracks);
    }
}
