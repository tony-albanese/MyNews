
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class Meta {

    @Expose
    private Long hits;
    @Expose
    private Long offset;
    @Expose
    private Long time;

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
