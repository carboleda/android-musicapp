package co.devhack.musicapp.presetation.presenter;

import co.devhack.musicapp.presetation.contract.LoginContract;

/**
 * Created by krlosf on 18/04/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onLogin(String username, String password, boolean remember) {
        view.disableButtons();
        //TODO LLAMAR EL CASO DE USO

        view.enableButtons();

        //Si ok
        view.goToMain();

        //Si error
        view.showLoginErrorMessage(null);
    }

    @Override
    public void onDontHaveAccount() {
        view.goToCreateAccount();
    }
}
