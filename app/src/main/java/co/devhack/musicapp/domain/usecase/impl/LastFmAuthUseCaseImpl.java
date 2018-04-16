package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.LastFmAuthRepository;
import co.devhack.musicapp.repository.impl.LastFmAuthRepositoryImpl;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by krlosf on 15/04/18.
 */

public class LastFmAuthUseCaseImpl implements LastFmAuthUseCase {

    private LastFmAuthRepository lastFmAuthRepository;

    public LastFmAuthUseCaseImpl() {
        this.lastFmAuthRepository = new LastFmAuthRepositoryImpl();
    }

    @Override
    public Observable<SessionDto> getMobileSession(final String username, final String password) {
        return lastFmAuthRepository.getMobileSession(username, password);
    }
}
