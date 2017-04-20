package app.ericdock.iupui.edu.listenup.model;

import java.io.Serializable;

/**
 * Created by ericd on 4/10/2017.
 */

public class Podcast implements Serializable {
    public String url;
    public String website;
    public String subscribers;
    public String subscribers_last_week;
    public String title;
    public String my_gpo_link;
    public String scaled_logo_url;
    public String description;
    public String logo_url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers;
    }

    public String getSubscribers_last_week() {
        return subscribers_last_week;
    }

    public void setSubscribers_last_week(String subscribers_last_week) {
        this.subscribers_last_week = subscribers_last_week;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMy_gpo_link() {
        return my_gpo_link;
    }

    public void setMy_gpo_link(String my_gpo_link) {
        this.my_gpo_link = my_gpo_link;
    }

    public String getScaled_logo_url() {
        return scaled_logo_url;
    }

    public void setScaled_logo_url(String scaled_logo_url) {
        this.scaled_logo_url = scaled_logo_url;
    }

    public String getDescription() {
        // get rid of those ugly line breaks in the descriptions returned by GPodder
        return description.replace("\n", " ");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
