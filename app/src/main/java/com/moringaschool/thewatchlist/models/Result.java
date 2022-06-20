
package com.moringaschool.thewatchlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("display_title")
    @Expose
    private String displayTitle;
    @SerializedName("mpaa_rating")
    @Expose
    private String mpaaRating;
    @SerializedName("critics_pick")
    @Expose
    private Integer criticsPick;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("summary_short")
    @Expose
    private String summaryShort;
    @SerializedName("publication_date")
    @Expose
    private String publicationDate;
    @SerializedName("opening_date")
    @Expose
    private String openingDate;
    @SerializedName("date_updated")
    @Expose
    private String dateUpdated;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("multimedia")
    @Expose
    private Multimedia multimedia;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param displayTitle
     * @param multimedia
     * @param summaryShort
     * @param criticsPick
     * @param link
     * @param byline
     * @param headline
     * @param publicationDate
     * @param openingDate
     * @param mpaaRating
     * @param dateUpdated
     */
    public Result(String displayTitle, String mpaaRating, Integer criticsPick, String byline, String headline, String summaryShort, String publicationDate, String openingDate, String dateUpdated, Link link, Multimedia multimedia) {
        super();
        this.displayTitle = displayTitle;
        this.mpaaRating = mpaaRating;
        this.criticsPick = criticsPick;
        this.byline = byline;
        this.headline = headline;
        this.summaryShort = summaryShort;
        this.publicationDate = publicationDate;
        this.openingDate = openingDate;
        this.dateUpdated = dateUpdated;
        this.link = link;
        this.multimedia = multimedia;
    }

    public String getDisplayTitle() {
        return displayTitle;
    }

    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Integer getCriticsPick() {
        return criticsPick;
    }

    public void setCriticsPick(Integer criticsPick) {
        this.criticsPick = criticsPick;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummaryShort() {
        return summaryShort;
    }

    public void setSummaryShort(String summaryShort) {
        this.summaryShort = summaryShort;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

}
