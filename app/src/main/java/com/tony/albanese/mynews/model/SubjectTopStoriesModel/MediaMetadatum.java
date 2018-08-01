
package com.tony.albanese.mynews.model.SubjectTopStoriesModel;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class MediaMetadatum {

    @Expose
    private String format;
    @Expose
    private Long height;
    @Expose
    private String url;
    @Expose
    private Long width;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
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
