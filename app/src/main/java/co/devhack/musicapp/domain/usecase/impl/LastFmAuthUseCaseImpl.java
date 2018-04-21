package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.model.MobileSessionResponse;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.Globals;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.LastFmAuthRepository;
import co.devhack.musicapp.repository.impl.AuthRepositoryLastFm;

/**
 * Created by krlosf on 15/04/18.
 */

public class LastFmAuthUseCaseImpl implements LastFmAuthUseCase {

    private LastFmAuthRepository lastFmAuthRepository;

    public LastFmAuthUseCaseImpl() {
        this.lastFmAuthRepository = new AuthRepositoryLastFm();
    }

    @Override
    public void getMobileSession(final String username, final String password,
                                 final Callback<MobileSessionResponse.Session> callback) {
        new ThreadExecutor<MobileSessionResponse>()
                .execute(new ThreadExecutor.Task<MobileSessionResponse>() {
                    @Override
                    public MobileSessionResponse execute() throws Exception {
                        return lastFmAuthRepository.getMobileSession(username, password);
                    }

                    @Override
                    public void finish(Exception error, MobileSessionResponse result) {
                        if(error == null && result != null) {
                            Globals.SESSION_KEY = result.getSession().getKey();
                            Globals.USERNAME = result.getSession().getUsername();
                            callback.success(result.getSession());
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }
}
