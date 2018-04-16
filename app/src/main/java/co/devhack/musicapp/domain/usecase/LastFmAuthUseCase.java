package co.devhack.musicapp.domain.usecase;

import co.devhack.musicapp.domain.dto.SessionDto;
import io.reactivex.Observable;

/**
 * Created by krlosf on 14/04/18.
 */

public interface LastFmAuthUseCase {

    Observable<SessionDto> getMobileSession(String username, String password);

}
