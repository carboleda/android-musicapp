package co.devhack.musicapp.repository;

/**
 * Created by krlosf on 15/04/18.
 */

public interface TrackRepository {

    Boolean love(String artist, String track) throws Exception;

    Boolean unlove(String artist, String track) throws Exception;

}
