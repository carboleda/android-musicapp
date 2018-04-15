package co.devhack.musicapp.repository.impl;

import java.util.HashMap;
import java.util.Map;

import co.devhack.musicapp.domain.dto.SessionDto;
import co.devhack.musicapp.domain.usecase.SignCallUseCase;
import co.devhack.musicapp.domain.usecase.impl.SignCallUseCaseImpl;
import co.devhack.musicapp.helpers.Constants;
import co.devhack.musicapp.helpers.Globals;
import co.devhack.musicapp.helpers.RetrofitSingleton;
import co.devhack.musicapp.repository.LastFmAuthRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by krlosf on 15/04/18.
 */

public class LastFmAuthRepositoryImpl implements LastFmAuthRepository {

    interface AuthServices {
        @POST("2.0/")
        Call<SessionDto> getMobileSession(@QueryMap Map<String, String> params);
    }

    @Override
    public SessionDto getMobileSession(String username, String password) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        String method = "auth.getMobileSession";
        params.put("method", method);
        params.put("api_key", Globals.API_KEY);
        params.put("password", password);
        params.put("username", username);

        SignCallUseCase signCallUseCase = SignCallUseCaseImpl.getInstance();
        String apiSig = signCallUseCase.signature(method, params, Globals.SECRET);
        params.put("api_sig", apiSig);
        params.put("format", Constants.API_RESPONSE_FORMAT);

        AuthServices authServices = RetrofitSingleton.getInstance().create(AuthServices.class);

        Call<SessionDto> call = authServices.getMobileSession(params);
        Response<SessionDto> response = call.execute();

        if(response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }
}
