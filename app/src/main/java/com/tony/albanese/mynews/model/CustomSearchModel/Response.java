
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;

import java.util.List;


@SuppressWarnings("unused")
public class Response {

    @Expose
    private List<Doc> docs;
    @Expose
    private Meta meta;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
