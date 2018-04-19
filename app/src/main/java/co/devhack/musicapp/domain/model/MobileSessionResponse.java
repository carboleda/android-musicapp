package co.devhack.musicapp.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by krlosf on 14/04/18.
 */

public class MobileSessionResponse {

    private Session session;

    public class Session {
        @SerializedName("name")
        private String username;
        private String key;
        @SerializedName("subscriber")
        private Integer subscriber;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getSubscriber() {
            return subscriber;
        }

        public void setSubscriber(Integer subscriber) {
            this.subscriber = subscriber;
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
