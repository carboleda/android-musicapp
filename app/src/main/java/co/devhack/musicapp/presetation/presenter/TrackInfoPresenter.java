package co.devhack.musicapp.presetation.presenter;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.usecase.TrackUseCase;
import co.devhack.musicapp.domain.usecase.impl.TrackUseCaseImpl;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.presetation.contract.TrackInfoContract;

/**
 * Created by krlosf on 29/04/18.
 */

public class TrackInfoPresenter implements TrackInfoContract.Presenter {

    private TrackInfoContract.View view;
    private TrackUseCase trackUseCase;
    private Track trackInfo;

    public TrackInfoPresenter(TrackInfoContract.View view) {
        this.view = view;
        this.trackUseCase = new TrackUseCaseImpl();
    }

    @Override
    public void loadInfo(String artist, String track) {
        view.showLoadingIndicator();
        trackUseCase.getInfo(artist, track, new Callback<Track>() {
            @Override
            public void success(Track result) {
                trackInfo = result;
                view.hideLoadingIndicator();
                view.showInfo(trackInfo);
            }

            @Override
            public void error(Exception error) {
                view.hideLoadingIndicator();
                view.showLoginErrorMessage(error);
            }
        });
    }

    @Override
    public void onLoveUnlove() {
        view.showLoadingIndicator();
        trackUseCase.loveUnlove(trackInfo, new Callback<Boolean>() {
            @Override
            public void success(Boolean result) {
                view.hideLoadingIndicator();
                loadInfo(trackInfo.getArtist().getName(), trackInfo.getName());
            }

            @Override
            public void error(Exception error) {
                view.hideLoadingIndicator();
                view.showLoginErrorMessage(error);
            }
        });
    }
}
