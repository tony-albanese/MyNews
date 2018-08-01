
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Multimedium {

    @Expose
    private Object caption;
    @Expose
    private Object credit;
    @SerializedName("crop_name")
    private Object cropName;
    @Expose
    private Long height;
    @Expose
    private Legacy legacy;
    @Expose
    private Long rank;
    @Expose
    private String subType;
    @Expose
    private String subtype;
    @Expose
    private String type;
    @Expose
    private String url;
    @Expose
    private Long width;

    public Object getCaption() {
        return caption;
    }

    public void setCaption(Object caption) {
        this.caption = caption;
    }

    public Object getCredit() {
        return credit;
    }

    public void setCredit(Object credit) {
        this.credit = credit;
    }

    public Object getCropName() {
        return cropName;
    }

    public void setCropName(Object cropName) {
        this.cropName = cropName;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
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

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

}
