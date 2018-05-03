package co.devhack.musicapp.domain.usecase.impl;

import java.util.List;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.usecase.UserUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.Globals;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.UserRepository;
import co.devhack.musicapp.repository.impl.UserRepositoryLastFm;

/**
 * Created by krlosf on 23/04/18.
 */

public class UserUseCaseImpl implements UserUseCase {

    private UserRepository userRepository;

    public UserUseCaseImpl() {
        this.userRepository = new UserRepositoryLastFm();
    }

    @Override
    public void getLovedTracks(final Callback<List<Track>> callback) {
        new ThreadExecutor<List<Track>>()
                .execute(new ThreadExecutor.Task<List<Track>>() {
                    @Override
                    public List<Track> execute() throws Exception {
                        //Llamo al metodo sincrono del UseCase
                        return getLovedTracks();
                    }

                    @Override
                    public void finish(Exception error, List<Track> result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }

    /**
     * Metodo sincrono para obtener las caciones que le gustan al usuario.
     * Para usar este metodo debe hacerse el llamado dentro de un hilo
     * @return
     */
    @Override
    public List<Track> getLovedTracks() throws Exception {
        //Aqui iria la logica del metodo getLovedTracks
        return userRepository.getLovedTracks(Globals.USERNAME);
    }


}
