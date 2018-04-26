package co.devhack.musicapp.domain.usecase;

/**
 * Created by krlosf on 25/04/18.
 */

public interface PrefUseCase {

    <V> boolean setData(String key, V value) throws Exception;
    <V> V getData(String key, V defaultValue, Class<V> type) throws Exception;
    void clearAllData() throws Exception;

}
