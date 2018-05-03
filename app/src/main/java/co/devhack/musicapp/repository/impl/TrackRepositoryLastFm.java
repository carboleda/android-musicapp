package co.devhack.musicapp.repository.impl;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.devhack.musicapp.domain.model.Track;
import co.devhack.musicapp.domain.model.TrackInfoResponse;
import co.devhack.musicapp.domain.usecase.SignCallUseCase;
import co.devhack.musicapp.domain.usecase.impl.SignCallUseCaseImpl;
import co.devhack.musicapp.helpers.Constants;
import co.devhack.musicapp.helpers.Globals;
import co.devhack.musicapp.helpers.RetrofitSingleton;
import co.devhack.musicapp.repository.TrackRepository;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by krlosf on 15/04/18.
 */

public class TrackRepositoryLastFm implements TrackRepository {

    //RestAdapter de Retrofit
    interface TrackServices {
        @FormUrlEncoded
        @POST("2.0/")
        Call<JSONObject> love(@FieldMap Map<String, String> params);

        @FormUrlEncoded
        @POST("2.0/")
        Call<JSONObject> unlove(@FieldMap Map<String, String> params);

        @GET("2.0/")
        Call<TrackInfoResponse> getInfo(@QueryMap Map<String, String> params);
    }

    @Override
    public Boolean love(String artist, String track) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        String method = "track.love";
        params.put("method", method);
        params.put("api_key", Constants.API_KEY);
        params.put("sk", Globals.SESSION_KEY);
        params.put("artist", artist);
        params.put("track", track);

        SignCallUseCase signCallUseCase = SignCallUseCaseImpl.getInstance();
        String apiSig = signCallUseCase.signature(method, params, Constants.SECRET);
        params.put("api_sig", apiSig);
        params.put("format", Constants.API_RESPONSE_FORMAT);

        TrackServices trackServices = RetrofitSingleton.getInstance().create(TrackServices.class);

        Call<JSONObject> call = trackServices.love(params);
        Response<JSONObject> response = call.execute();

        if(response.isSuccessful()) {
            return true;
        } else {
            throw new Exception(response.message());
        }
    }

    @Override
    public Boolean unlove(String artist, String track) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        String method = "track.unlove";
        params.put("method", method);
        params.put("api_key", Constants.API_KEY);
        params.put("sk", Globals.SESSION_KEY);
        params.put("artist", artist);
        params.put("track", track);

        SignCallUseCase signCallUseCase = SignCallUseCaseImpl.getInstance();
        String apiSig = signCallUseCase.signature(method, params, Constants.SECRET);
        params.put("api_sig", apiSig);
        params.put("format", Constants.API_RESPONSE_FORMAT);

        TrackServices trackServices = RetrofitSingleton.getInstance().create(TrackServices.class);

        Call<JSONObject> call = trackServices.unlove(params);
        Response<JSONObject> response = call.execute();

        if(response.isSuccessful()) {
            return true;
        } else {
            throw new Exception(response.message());
        }
    }

    @Override
    public Track getInfo(String artist, String track) throws Exception {
        Map<String, String> params = new HashMap<>(0);
        String method = "track.getInfo";
        params.put("method", method);
        params.put("api_key", Constants.API_KEY);
        params.put("artist", artist);
        params.put("track", track);
        params.put("format", Constants.API_RESPONSE_FORMAT);

        TrackServices trackServices = RetrofitSingleton.getInstance().create(TrackServices.class);

        Call<TrackInfoResponse> call = trackServices.getInfo(params);
        Response<TrackInfoResponse> response = call.execute();

        if(response.isSuccessful()) {
            TrackInfoResponse trackInfoResponse = response.body();
            if(trackInfoResponse != null) {
                return trackInfoResponse.getTrack();
            }
        } else {
            throw new Exception(response.message());
        }

        return null;
    }
}
