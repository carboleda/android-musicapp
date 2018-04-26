package co.devhack.musicapp.repository.impl;

import java.util.HashMap;
import java.util.Map;

import co.devhack.musicapp.domain.model.TopTracksResponse;
import co.devhack.musicapp.helpers.Constants;
import co.devhack.musicapp.helpers.RetrofitSingleton;
import co.devhack.musicapp.repository.GeoRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by krlosf on 23/04/18.
 */

public class GeoRepositoryLastFm implements GeoRepository {

    //RestAdapter de Retrofit
    interface GeoServices {
        @GET("2.0/")
        Call<TopTracksResponse> getTopTracks(@QueryMap Map<String, String> params);
    }

    @Override
    public TopTracksResponse getTopTracks(String country) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        String method = "geo.getTopTracks";
        params.put("method", method);
        params.put("api_key", Constants.API_KEY);
        params.put("country", country);
        params.put("format", Constants.API_RESPONSE_FORMAT);

        GeoServices geoServices = RetrofitSingleton.getInstance().create(GeoServices.class);

        Call<TopTracksResponse> call = geoServices.getTopTracks(params);
        Response<TopTracksResponse> response = call.execute();

        if(response.isSuccessful()) {
            return response.body();
        } else {
            throw new Exception(response.message());
        }
    }

}
