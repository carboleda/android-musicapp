package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.model.LovedTracksResponse;
import co.devhack.musicapp.domain.usecase.UserUseCase;
import co.devhack.musicapp.helpers.Callback;
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
    public void getLovedTracks(final String user, final Callback<LovedTracksResponse> callback) {
        new ThreadExecutor<LovedTracksResponse>()
                .execute(new ThreadExecutor.Task<LovedTracksResponse>() {
                    @Override
                    public LovedTracksResponse execute() throws Exception {
                        return userRepository.getLovedTracks(user);
                    }

                    @Override
                    public void finish(Exception error, LovedTracksResponse result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }
}
