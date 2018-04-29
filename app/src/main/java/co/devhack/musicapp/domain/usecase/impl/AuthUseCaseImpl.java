package co.devhack.musicapp.domain.usecase.impl;

import co.devhack.musicapp.domain.model.MobileSessionResponse;
import co.devhack.musicapp.domain.usecase.AuthUseCase;
import co.devhack.musicapp.domain.usecase.PrefUseCase;
import co.devhack.musicapp.helpers.Callback;
import co.devhack.musicapp.helpers.Constants;
import co.devhack.musicapp.helpers.Globals;
import co.devhack.musicapp.helpers.ThreadExecutor;
import co.devhack.musicapp.repository.AuthRepository;
import co.devhack.musicapp.repository.impl.AuthRepositoryLastFm;

/**
 * Created by krlosf on 15/04/18.
 */

public class AuthUseCaseImpl implements AuthUseCase {

    private AuthRepository authRepository;
    private PrefUseCase prefUseCase;

    public AuthUseCaseImpl() {
        this.authRepository = new AuthRepositoryLastFm();
        this.prefUseCase = new PrefUseCaseImpl();
    }

    @Override
    public void getMobileSession(final String username, final String password,
                                 final Callback<MobileSessionResponse.Session> callback) {
        new ThreadExecutor<MobileSessionResponse>()
                .execute(new ThreadExecutor.Task<MobileSessionResponse>() {
                    @Override
                    public MobileSessionResponse execute() throws Exception {
                        return authRepository.getMobileSession(username, password);
                    }

                    @Override
                    public void finish(Exception error, MobileSessionResponse result) {
                        if(error == null && result != null) {
                            Globals.SESSION_KEY = result.getSession().getKey();
                            Globals.USERNAME = result.getSession().getUsername();

                            try {
                                //Guardar el session_key y el username en las preferencias
                                prefUseCase.setData(Constants.PrefKeys.SESSION_KEY, result.getSession().getKey());
                                prefUseCase.setData(Constants.PrefKeys.USERNAME, result.getSession().getUsername());
                                //Me indica que el usuario ya esta autenticado
                                prefUseCase.setData(Constants.PrefKeys.IS_AUTH, true);

                                callback.success(result.getSession());
                            } catch (Exception e) {
                                callback.error(e);
                            }
                        } else {
                            callback.error(error);
                        }
                    }
                });
    }

    @Override
    public void validateSession(Callback<Boolean> callback) {
        try {
            Boolean isAuth = prefUseCase.getData(Constants.PrefKeys.IS_AUTH, false, Boolean.class);
            //Si ya esta autenticado se carga el session_key y el username para usarlos en los reposiries
            if(isAuth) {
                Globals.SESSION_KEY = prefUseCase.getData(Constants.PrefKeys.SESSION_KEY, null, String.class);
                Globals.USERNAME = prefUseCase.getData(Constants.PrefKeys.USERNAME, null, String.class);
            }
            callback.success(isAuth);
        } catch (Exception error) {
            callback.error(error);
        }
    }
}
