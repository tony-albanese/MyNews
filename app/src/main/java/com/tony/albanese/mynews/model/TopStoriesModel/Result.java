
package com.tony.albanese.mynews.model.TopStoriesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Result {

    @Expose
    private String articleAbstract;
    @Expose
    private String byline;
    @SerializedName("created_date")
    private String createdDate;
    @SerializedName("des_facet")
    private List<Object> desFacet;
    @SerializedName("geo_facet")
    private List<Object> geoFacet;
    @SerializedName("item_type")
    private String itemType;
    @Expose
    private String kicker;
    @SerializedName("material_type_facet")
    private String materialTypeFacet;
    @Expose
    private List<Multimedium> multimedia;
    @SerializedName("org_facet")
    private List<Object> orgFacet;
    @SerializedName("per_facet")
    private List<Object> perFacet;
    @SerializedName("published_date")
    private String publishedDate;
    @Expose
    private String section;
    @SerializedName("short_url")
    private String shortUrl;
    @Expose
    private String subsection;
    @Expose
    private String title;
    @SerializedName("updated_date")
    private String updatedDate;
    @Expose
    private String url;

    public String getAbstract() {
        return articleAbstract;
    }

    public void setAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<Object> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<Object> desFacet) {
        this.desFacet = desFacet;
    }

    public List<Object> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<Object> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    public List<Object> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<Object> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<Object> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<Object> perFacet) {
        this.perFacet = perFacet;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
