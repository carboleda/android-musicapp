package co.devhack.musicapp.presetation.contract;

/**
 * Created by krlosf on 18/04/18.
 */

public interface LoginContract {

    interface View {

        void disableButtons();

        void enableButtons();

        void goToMain();

        void showLoginErrorMessage(Throwable error);

        void goToCreateAccount();

    }

    interface Presenter {

        void onLogin(String username, String password, boolean remember);

        void onDontHaveAccount();

    }

}
