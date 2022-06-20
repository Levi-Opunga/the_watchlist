
package com.moringaschool.thewatchlist.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Multimedia {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Multimedia() {
    }

    /**
     * 
     * @param src
     * @param width
     * @param type
     * @param height
     */
    public Multimedia(String type, String src, Integer height, Integer width) {
        super();
        this.type = type;
        this.src = src;
        this.height = height;
        this.width = width;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
