
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class CustomSearch {

    @Expose
    private String copyright;
    @Expose
    private Response response;
    @Expose
    private String status;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
