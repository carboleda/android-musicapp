package co.devhack.musicapp.presetation.presenter;

import java.util.ArrayList;
import java.util.List;

import co.devhack.musicapp.domain.model.TopTracksResponse;
import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.usecase.GeoUseCase;
import co.devhack.musicapp.domain.usecase.impl.GeoUseCaseImpl;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.presetation.contract.TopTracksContract;

/**
 * Created by krlosf on 23/04/18.
 */

public class TopTracksPresenter implements TopTracksContract.Presenter {

    private TopTracksContract.View view;
    private GeoUseCase geoUseCase;
    private final List<Track> lstTracks;

    public TopTracksPresenter(TopTracksContract.View view) {
        this.view = view;
        this.geoUseCase = new GeoUseCaseImpl();
        this.lstTracks = new ArrayList<>(0);
    }

    @Override
    public void loadTracks() {
        //TODO CAMBIAR A CONSTANTE Y POSTERIORMENTE IDENTIFICAR EL PAIS POR GPS
        view.showLoadingIndicator();
        geoUseCase.getTopTracks("colombia", new Callback<TopTracksResponse>() {
            @Override
            public void success(TopTracksResponse result) {
                //Actualizar la lista con los nuevos items
                lstTracks.clear();
                lstTracks.addAll(result.getTracks().getLstTracks());

                view.refreshTracks();
                view.hideLoadingIndicator();
            }

            @Override
            public void error(Exception error) {
                view.hideLoadingIndicator();
                view.showLoginErrorMessage(error);
            }
        });
    }

    @Override
    public List<Track> getTracks() {
        return lstTracks;
    }

    @Override
    public void onLove(int position) {

    }

    @Override
    public void onUnlove(int position) {

    }
}
