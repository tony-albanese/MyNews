
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Keyword {

    @Expose
    private String major;
    @Expose
    private String name;
    @Expose
    private Long rank;
    @Expose
    private String value;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
