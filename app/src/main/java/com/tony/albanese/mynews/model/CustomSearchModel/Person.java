
package com.tony.albanese.mynews.model.CustomSearchModel;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Person {

    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private Object middlename;
    @Expose
    private String organization;
    @Expose
    private Object qualifier;
    @Expose
    private Long rank;
    @Expose
    private String role;
    @Expose
    private Object title;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Object getMiddlename() {
        return middlename;
    }

    public void setMiddlename(Object middlename) {
        this.middlename = middlename;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Object getQualifier() {
        return qualifier;
    }

    public void setQualifier(Object qualifier) {
        this.qualifier = qualifier;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

}
