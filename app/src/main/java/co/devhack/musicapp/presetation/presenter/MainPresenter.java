package co.devhack.musicapp.presetation.presenter;

import co.devhack.musicapp.domain.usecase.AuthUseCase;
import co.devhack.musicapp.domain.usecase.impl.AuthUseCaseImpl;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.presetation.contract.MainContract;

/**
 * Created by krlosf on 18/04/18.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private AuthUseCase authUseCase;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.authUseCase = new AuthUseCaseImpl();
    }

    @Override
    public void closeSession() {
        authUseCase.closeSession(new Callback<Boolean>() {
            @Override
            public void success(Boolean isAuth) {
                //Si la sesion se cerro correctamente
                if (isAuth) {
                    //Se muestra la pantalla de login
                    view.goToLogin();
                }
            }

            @Override
            public void error(Exception error) {
                view.showLoginErrorMessage(error);
            }
        });
    }
}
