
package com.moringaschool.thewatchlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("suggested_link_text")
    @Expose
    private String suggestedLinkText;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Link() {
    }

    /**
     * 
     * @param suggestedLinkText
     * @param type
     * @param url
     */
    public Link(String type, String url, String suggestedLinkText) {
        super();
        this.type = type;
        this.url = url;
        this.suggestedLinkText = suggestedLinkText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSuggestedLinkText() {
        return suggestedLinkText;
    }

    public void setSuggestedLinkText(String suggestedLinkText) {
        this.suggestedLinkText = suggestedLinkText;
    }

}
