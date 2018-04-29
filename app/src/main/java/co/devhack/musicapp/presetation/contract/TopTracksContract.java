package co.devhack.musicapp.presetation.contract;

import java.util.List;

import co.devhack.musicapp.domain.model.Track;

/**
 * Created by krlosf on 23/04/18.
 */

public interface TopTracksContract {

    interface View {

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void refreshTracks();

        void showLoginErrorMessage(Throwable error);

    }

    interface Presenter {

        /**
         * Invoca el metodo del UseCase que obtiene los datos desde el backend o API
         */
        void loadTracks();

        /**
         * Devuelve la lista de canciones previamente obtenida por el metodo loadTracks
         * @return
         */
        List<Track> getTracks();

        void onLoveUnlove(int position);

    }

}
