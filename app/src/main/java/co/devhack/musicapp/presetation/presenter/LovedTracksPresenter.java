package co.devhack.musicapp.presetation.presenter;

import java.util.ArrayList;
import java.util.List;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.usecase.TrackUseCase;
import co.devhack.musicapp.domain.usecase.UserUseCase;
import co.devhack.musicapp.domain.usecase.impl.TrackUseCaseImpl;
import co.devhack.musicapp.domain.usecase.impl.UserUseCaseImpl;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.presetation.contract.LovedTracksContract;

/**
 * Created by krlosf on 23/04/18.
 */

public class LovedTracksPresenter implements LovedTracksContract.Presenter {

    private LovedTracksContract.View view;
    private UserUseCase userUseCase;
    private TrackUseCase trackUseCase;
    private final List<Track> lstTracks;

    public LovedTracksPresenter(LovedTracksContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
        this.trackUseCase = new TrackUseCaseImpl();
        this.lstTracks = new ArrayList<>(0);
    }

    @Override
    public void loadTracks() {
        //TODO CAMBIAR A CONSTANTE Y POSTERIORMENTE IDENTIFICAR EL PAIS POR GPS
        view.showLoadingIndicator();
        userUseCase.getLovedTracks(new Callback<List<Track>>() {
            @Override
            public void success(List<Track> result) {
                //Actualizar la lista con los nuevos items
                lstTracks.clear();
                lstTracks.addAll(result);

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
    public void onUnlove(int position) {
        Track track = lstTracks.get(position);
        view.showLoadingIndicator();
        trackUseCase.unlove(track, new Callback<Boolean>() {
            @Override
            public void success(Boolean result) {
                view.hideLoadingIndicator();
                loadTracks();
            }

            @Override
            public void error(Exception error) {
                view.hideLoadingIndicator();
                view.showLoginErrorMessage(error);
            }
        });
    }
}
