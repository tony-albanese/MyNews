
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Headline {

    @SerializedName("content_kicker")
    private Object contentKicker;
    @Expose
    private Object kicker;
    @Expose
    private String main;
    @Expose
    private Object name;
    @SerializedName("print_headline")
    private String printHeadline;
    @Expose
    private Object seo;
    @Expose
    private Object sub;

    public Object getContentKicker() {
        return contentKicker;
    }

    public void setContentKicker(Object contentKicker) {
        this.contentKicker = contentKicker;
    }

    public Object getKicker() {
        return kicker;
    }

    public void setKicker(Object kicker) {
        this.kicker = kicker;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    public Object getSeo() {
        return seo;
    }

    public void setSeo(Object seo) {
        this.seo = seo;
    }

    public Object getSub() {
        return sub;
    }

    public void setSub(Object sub) {
        this.sub = sub;
    }

}
