package co.devhack.musicapp.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by krlosf on 26/10/16.
 */

public class RetrofitSingleton {

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if(retrofit == null) {
            retrofit = getInstance(Constants.SERVER);
        }

        return retrofit;
    }

    public static Retrofit getInstance(String baseUrl, Interceptor...requestInterceptors) {
        OkHttpClient.Builder builder =
                new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS)
                        .readTimeout(5, TimeUnit.SECONDS)
                        .writeTimeout(5, TimeUnit.SECONDS);

        if(requestInterceptors != null) {
            for (Interceptor requestInterceptor : requestInterceptors) {
                builder.addInterceptor(requestInterceptor);
            }
        }

        OkHttpClient client = builder.build();

        Gson gson = getGsonWithConverters();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    public static Gson getGsonWithConverters() {
        Gson gson = new GsonBuilder()
                //.registerTypeAdapter(Date.class, new DateGsonAdapter())
                //.registerTypeAdapter(Time.class, new TimeGsonAdapter())
                .create();

        return gson;
    }
}