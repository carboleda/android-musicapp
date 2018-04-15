package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.LastFmAuthRepository;
import co.devhack.musicapp.repository.impl.LastFmAuthRepositoryImpl;

/**
 * Created by krlosf on 15/04/18.
 */

public class LastFmAuthUseCaseImpl implements LastFmAuthUseCase {

    private LastFmAuthRepository lastFmAuthRepository;

    public LastFmAuthUseCaseImpl() {
        this.lastFmAuthRepository = new LastFmAuthRepositoryImpl();
    }

    @Override
    public void getMobileSession(final String username, final String password,
                                 final Callback<SessionDto> callback) {
        new ThreadExecutor<SessionDto>()
                .execute(new ThreadExecutor.Task<SessionDto>() {
                    @Override
                    public SessionDto execute() throws Exception {
                        return lastFmAuthRepository.getMobileSession(username, password);
                    }

                    @Override
                    public void finish(Exception error, SessionDto result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }
}
