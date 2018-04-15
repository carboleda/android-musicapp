package co.devhack.musicapp.domain.usecase;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.helpers.Callback;

/**
 * Created by krlosf on 14/04/18.
 */

public interface LastFmAuthUseCase {

    void getMobileSession(String username, String password, Callback<SessionDto> callback);

}
