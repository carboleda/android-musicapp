package co.devhack.musicapp.helpers;

/**
 * Created by krlosf on 15/04/18.
 */

public class Constants {
    private static String PROTOCOL = "https";
    private static String HOST = "ws.audioscrobbler.com";
    private static String PORT = "";
    public static final String SERVER = PROTOCOL + "://" + HOST + PORT + "/";
    public static final String API_RESPONSE_FORMAT="json";

    public static final String API_KEY = "aa0b02bc042ecb05525fcb7ad4199874";
    public static final String SECRET = "daff54aeeb740724f3018841570bd25e";

    public static class PrefKeys {
        public static final String SESSION_KEY = "session_key";
        public static final String USERNAME = "username";
        public static final String IS_AUTH = "is_auth";
    }
}
