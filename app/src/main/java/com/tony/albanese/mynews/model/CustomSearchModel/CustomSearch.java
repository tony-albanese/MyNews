
package com.tony.albanese.mynews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomSearch {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
