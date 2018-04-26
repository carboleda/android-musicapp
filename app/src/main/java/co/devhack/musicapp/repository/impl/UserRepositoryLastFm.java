package co.devhack.musicapp.repository.impl;

import java.util.HashMap;
import java.util.Map;

import co.devhack.musicapp.domain.model.LovedTracksResponse;
import co.devhack.musicapp.helpers.Constants;
import co.devhack.musicapp.helpers.RetrofitSingleton;
import co.devhack.musicapp.repository.UserRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by krlosf on 23/04/18.
 */

public class UserRepositoryLastFm implements UserRepository {

    //RestAdapter de Retrofit
    interface UserServices {
        @GET("2.0/")
        Call<LovedTracksResponse> getLovedTracks(@QueryMap Map<String, String> params);
    }

    @Override
    public LovedTracksResponse getLovedTracks(String user) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        String method = "user.getLovedTracks";
        params.put("method", method);
        params.put("api_key", Constants.API_KEY);
        params.put("user", user);
        params.put("format", Constants.API_RESPONSE_FORMAT);

        UserServices userServices = RetrofitSingleton.getInstance().create(UserServices.class);

        Call<LovedTracksResponse> call = userServices.getLovedTracks(params);
        Response<LovedTracksResponse> response = call.execute();

        if(response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }

}
