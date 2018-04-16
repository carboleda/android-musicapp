package co.devhack.musicapp.repository;

import co.devhack.musicapp.domain.dto.SessionDto;
import io.reactivex.Observable;

/**
 * Created by krlosf on 14/04/18.
 */

public interface LastFmAuthRepository {

    Observable<SessionDto> getMobileSession(String username, String password);

}
