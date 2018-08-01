
package com.tony.albanese.mynews.model.SubjectTopStoriesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Result {

    @Expose
    private String articleAbstract;
    @SerializedName("adx_keywords")
    private String adxKeywords;
    @SerializedName("asset_id")
    private Long assetId;
    @Expose
    private String byline;
    @Expose
    private String column;
    @SerializedName("des_facet")
    private List<String> desFacet;
    @SerializedName("geo_facet")
    private String geoFacet;
    @Expose
    private Long id;
    @Expose
    private List<Medium> media;
    @SerializedName("org_facet")
    private List<String> orgFacet;
    @SerializedName("per_facet")
    private String perFacet;
    @SerializedName("published_date")
    private String publishedDate;
    @Expose
    private String section;
    @Expose
    private String source;
    @Expose
    private String title;
    @Expose
    private String type;
    @Expose
    private String url;
    @Expose
    private Long views;

    public String getAbstract() {
        return articleAbstract;
    }

    public void setAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public String getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(String geoFacet) {
        this.geoFacet = geoFacet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public String getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(String perFacet) {
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

}
