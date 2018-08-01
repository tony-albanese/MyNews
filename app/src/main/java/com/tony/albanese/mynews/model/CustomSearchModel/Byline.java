
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;

import java.util.List;


@SuppressWarnings("unused")
public class Byline {

    @Expose
    private Object organization;
    @Expose
    private String original;
    @Expose
    private List<Person> person;

    public Object getOrganization() {
        return organization;
    }

    public void setOrganization(Object organization) {
        this.organization = organization;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

}
