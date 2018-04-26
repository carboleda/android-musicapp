package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.usecase.PrefUseCase;
import co.devhack.musicapp.repository.PrefRepository;
import co.devhack.musicapp.repository.impl.PrefRepositoryImpl;

/**
 * Created by krlosf on 25/04/18.
 */

public class PrefUseCaseImpl implements PrefUseCase {

    private PrefRepository prefRepository;

    public PrefUseCaseImpl() {
        this.prefRepository = new PrefRepositoryImpl();
    }

    @Override
    public <V> boolean setData(String key, V value) throws Exception {
        return prefRepository.setData(key, value);
    }

    @Override
    public <V> V getData(String key, V defaultValue, Class<V> type) throws Exception {
        return prefRepository.getData(key, defaultValue, type);
    }

    @Override
    public void clearAllData() throws Exception {
        prefRepository.clearAllData();
    }
}
