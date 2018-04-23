package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.model.TopTracksResponse;
import co.devhack.musicapp.domain.usecase.GeoUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.GeoRepository;
import co.devhack.musicapp.repository.impl.GeoRepositoryLastFm;

/**
 * Created by krlosf on 23/04/18.
 */

public class GeoUseCaseImpl implements GeoUseCase {

    private GeoRepository geoRepository;

    public GeoUseCaseImpl() {
        this.geoRepository = new GeoRepositoryLastFm();
    }

    @Override
    public void getTopTracks(final String country, final Callback<TopTracksResponse> callback) {
        new ThreadExecutor<TopTracksResponse>()
                .execute(new ThreadExecutor.Task<TopTracksResponse>() {
                    @Override
                    public TopTracksResponse execute() throws Exception {
                        return geoRepository.getTopTracks(country);
                    }

                    @Override
                    public void finish(Exception error, TopTracksResponse result) {
                        if(error == null) {
                            callback.success(result);
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }
}
