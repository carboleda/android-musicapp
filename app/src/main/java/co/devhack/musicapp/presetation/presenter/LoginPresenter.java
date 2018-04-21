package co.devhack.musicapp.presetation.presenter;

import co.devhack.musicapp.domain.model.MobileSessionResponse;
import co.devhack.musicapp.domain.usecase.LastFmAuthUseCase;
import co.devhack.musicapp.domain.usecase.impl.LastFmAuthUseCaseImpl;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.presetation.contract.LoginContract;

/**
 * Created by krlosf on 18/04/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private LastFmAuthUseCase lastFmAuthUseCase;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.lastFmAuthUseCase = new LastFmAuthUseCaseImpl();
    }

    @Override
    public void onLogin(String username, String password, boolean remember) {
        view.disableButtons();

        lastFmAuthUseCase.getMobileSession(username, password,
                new Callback<MobileSessionResponse.Session>() {
            @Override
            public void success(MobileSessionResponse.Session session) {
                view.enableButtons();
                view.goToMain();
            }

            @Override
            public void error(Exception error) {
                view.enableButtons();
                view.showLoginErrorMessage(error);
            }
        });
    }

    @Override
    public void onDontHaveAccount() {
        view.goToCreateAccount();
    }
}
