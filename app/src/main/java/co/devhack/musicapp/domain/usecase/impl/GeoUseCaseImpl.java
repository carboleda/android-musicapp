package co.devhack.musicapp.domain.usecase.impl;

import java.util.List;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.usecase.GeoUseCase;
import co.devhack.musicapp.domain.usecase.UserUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.GeoRepository;
import co.devhack.musicapp.repository.impl.GeoRepositoryLastFm;
import co.devhack.musicapp.repository.impl.GeoRepositorySQLite;

/**
 * Created by krlosf on 23/04/18.
 */

public class GeoUseCaseImpl implements GeoUseCase {

    private GeoRepository geoRepository;
    private GeoRepository geoRepositorySQLite;
    private UserUseCase userUseCase;
    private boolean hayConexion = true;

    public GeoUseCaseImpl() {
        this.geoRepository = new GeoRepositoryLastFm();
        this.geoRepositorySQLite = new GeoRepositorySQLite();
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void getTopTracks(final String country, final Callback<List<Track>> callback) {
        new ThreadExecutor<List<Track>>()
                .execute(new ThreadExecutor.Task<List<Track>>() {
                    @Override
                    public List<Track> execute() throws Exception {
                        if(hayConexion) {
                            //Este es el top de canciones
                            List<Track> lstTopTracks = geoRepository.getTopTracks(country);
                            //Estas son las caciones que le gustan al usuario
                            List<Track> lstLovedTracks = userUseCase.getLovedTracks();

                            //Se recorre la lista de caciones que le gustan al usuario
                            for (Track loveTrack : lstLovedTracks) {
                                //Se corre la lista de canciones top
                                for (Track topTrack : lstTopTracks) {
                                    //Se compara cada cacion que le gusta al usuario con cada cancion top
                                    //para identificar entre las canciones top cuales ya le gustan al usuario
                                    if (topTrack.getName().equals(loveTrack.getName())
                                            && topTrack.getArtist().getName().equals(loveTrack.getArtist().getName())) {
                                        //Si entra aqui es porque la cancion top ya le gusta al usuario
                                        topTrack.setLove(true);
                                    }
                                }
                            }

                            geoRepositorySQLite.deleteTopTracks();
                            geoRepositorySQLite.insertTopTracks(lstTopTracks);

                            return lstTopTracks;
                        } else {
                            return geoRepositorySQLite.getTopTracks(country);
                        }
                    }

                    @Override
                    public void finish(Exception error, List<Track> result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            try {
                                callback.success(result);
                            } catch (Exception e) {
                                callback.error(e);
                            }
                        }
                    }
                });
    }
}
