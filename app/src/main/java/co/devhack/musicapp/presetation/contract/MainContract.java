package co.devhack.musicapp.presetation.contract;

/**
 * Created by krlosf on 18/04/18.
 */

public interface MainContract {

    interface View {

        void goToLogin();

        void showLoginErrorMessage(Throwable error);
    }

    interface Presenter {

        void closeSession();

    }

}
