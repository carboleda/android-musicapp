package co.devhack.musicapp.repository;

import co.devhack.musicapp.domain.dto.SessionDto;

/**
 * Created by krlosf on 14/04/18.
 */

public interface LastFmAuthRepository {

    SessionDto getMobileSession(String username, String password) throws Exception;

}
