package co.devhack.musicapp.presetation.contract;

import co.devhack.musicapp.domain.model.Track;

/**
 * Created by krlosf on 29/04/18.
 */

public interface TrackInfoContract {

    interface View {

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showInfo(Track track);

        void showLoginErrorMessage(Throwable error);

    }

    interface Presenter {

        /**
         * Invoca el metodo del UseCase que obtiene los datos desde el backend o API
         */
        void loadInfo(String artist, String track);

        void onLoveUnlove();

    }

}
