package co.devhack.musicapp.domain.model;

/**
 * Created by krlosf on 29/04/18.
 */

public class Wiki {
    private String published;
    private String summary;
    private String content;

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
