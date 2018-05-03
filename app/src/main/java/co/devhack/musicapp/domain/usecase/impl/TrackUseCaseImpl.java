package co.devhack.musicapp.domain.usecase.impl;

import java.util.List;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.usecase.TrackUseCase;
import co.devhack.musicapp.domain.usecase.UserUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.TrackRepository;
import co.devhack.musicapp.repository.impl.TrackRepositoryLastFm;

/**
 * Created by krlosf on 15/04/18.
 */

public class TrackUseCaseImpl implements TrackUseCase {

    private TrackRepository trackRepository;
    private UserUseCase userUseCase;

    public TrackUseCaseImpl() {
        this.trackRepository = new TrackRepositoryLastFm();
        this.userUseCase = new UserUseCaseImpl();
    }


    @Override
    public void loveUnlove(final Track track, final Callback<Boolean> callback) {
        new ThreadExecutor<Boolean>()
                .execute(new ThreadExecutor.Task<Boolean>() {
                    @Override
                    public Boolean execute() throws Exception {
                        //Si al usuario ya le gusta el track
                        if(track.isLove()) {
                            //Llamamos el metodo unlove
                            return trackRepository.unlove(track.getArtist().getName(), track.getName());
                        } else {
                            //De lo contrario llamos el metodo love
                            return trackRepository.love(track.getArtist().getName(), track.getName());
                        }
                    }

                    @Override
                    public void finish(Exception error, Boolean result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }

    @Override
    public void love(final Track track, final Callback<Boolean> callback) {
        new ThreadExecutor<Boolean>()
                .execute(new ThreadExecutor.Task<Boolean>() {
                    @Override
                    public Boolean execute() throws Exception {
                        return trackRepository.love(track.getArtist().getName(), track.getName());
                    }

                    @Override
                    public void finish(Exception error, Boolean result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }

    @Override
    public void unlove(final Track track, final Callback<Boolean> callback) {
        new ThreadExecutor<Boolean>()
                .execute(new ThreadExecutor.Task<Boolean>() {
                    @Override
                    public Boolean execute() throws Exception {
                        return trackRepository.unlove(track.getArtist().getName(), track.getName());
                    }

                    @Override
                    public void finish(Exception error, Boolean result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }

    @Override
    public void getInfo(final String artist, final String track, final Callback<Track> callback) {
        new ThreadExecutor<Track>()
                .execute(new ThreadExecutor.Task<Track>() {
                    @Override
                    public Track execute() throws Exception {
                        Track trackInfo = trackRepository.getInfo(artist, track);

                        List<Track> lstLovedTracks = userUseCase.getLovedTracks();

                        for (Track lovedTrack : lstLovedTracks) {
                            if(lovedTrack.getName().equals(track) && lovedTrack.getArtist().getName().equals(artist)) {
                                trackInfo.setLove(true);
                                break;
                            }
                        }

                        return trackInfo;
                    }

                    @Override
                    public void finish(Exception error, Track result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }
}
