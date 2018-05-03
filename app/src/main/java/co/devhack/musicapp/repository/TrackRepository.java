package co.devhack.musicapp.repository;

import co.devhack.musicapp.domain.model.Track;

/**
 * Created by krlosf on 15/04/18.
 */

public interface TrackRepository {

    Boolean love(String artist, String track) throws Exception;

    Boolean unlove(String artist, String track) throws Exception;

    Track getInfo(String artist, String track) throws Exception;

}
