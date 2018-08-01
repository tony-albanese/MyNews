
package com.tony.albanese.mynews.model.SubjectTopStoriesModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class SubjectSearch {

    @Expose
    private String copyright;
    @SerializedName("num_results")
    private Long numResults;
    @Expose
    private List<Result> results;
    @Expose
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Long getNumResults() {
        return numResults;
    }

    public void setNumResults(Long numResults) {
        this.numResults = numResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
