
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Legacy {

    @Expose
    private String xlarge;
    @Expose
    private Long xlargeheight;
    @Expose
    private Long xlargewidth;

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    public Long getXlargeheight() {
        return xlargeheight;
    }

    public void setXlargeheight(Long xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    public Long getXlargewidth() {
        return xlargewidth;
    }

    public void setXlargewidth(Long xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

}
