package co.devhack.musicapp.presetation.presenter;

import co.devhack.musicapp.domain.model.MobileSessionResponse;
import co.devhack.musicapp.domain.usecase.AuthUseCase;
import co.devhack.musicapp.domain.usecase.impl.AuthUseCaseImpl;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.presetation.contract.LoginContract;

/**
 * Created by krlosf on 18/04/18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;
    private AuthUseCase authUseCase;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.authUseCase = new AuthUseCaseImpl();
    }

    @Override
    public void validateSession() {
        authUseCase.validateSession(new Callback<Boolean>() {
            @Override
            public void success(Boolean isAuth) {
                //Si el usuario ya esta autenticado
                if (isAuth) {
                    view.goToMain();
                } else {
                    //Si no esta autenticado se intenta obtener el usuarios recordado
                    getRememberedUser();
                }
            }

            @Override
            public void error(Exception error) {
                //view.showValidateSessionError(error);
            }
        });
    }

    @Override
    public void onLogin(String username, String password, boolean remember) {
        view.disableButtons();

        authUseCase.getMobileSession(username, password, remember,
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

    private void getRememberedUser() {
        authUseCase.getRememberedUser(new Callback<String>() {
            @Override
            public void success(String username) {
                //Si hay un usuario recordado
                if(username != null) {
                    //Se muestra en la pantalla
                    view.showRememberedUser(username);
                }
            }

            @Override
            public void error(Exception error) {
                view.showLoginErrorMessage(error);
            }
        });
    }
}
