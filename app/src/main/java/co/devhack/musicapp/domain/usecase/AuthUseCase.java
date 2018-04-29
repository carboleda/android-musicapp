package co.devhack.musicapp.domain.usecase;

import co.devhack.musicapp.domain.model.MobileSessionResponse;
import co.devhack.musicapp.helpers.Callback;

/**
 * Created by krlosf on 14/04/18.
 */

public interface AuthUseCase {

    void getMobileSession(String username, String password, Callback<MobileSessionResponse.Session> callback);

    void validateSession(Callback<Boolean> callback);

}
