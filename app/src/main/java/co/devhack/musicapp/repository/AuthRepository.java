package co.devhack.musicapp.repository;

import co.devhack.musicapp.domain.model.MobileSessionResponse;

/**
 * Created by krlosf on 14/04/18.
 */

public interface AuthRepository {

    MobileSessionResponse getMobileSession(String username, String password) throws Exception;

}
