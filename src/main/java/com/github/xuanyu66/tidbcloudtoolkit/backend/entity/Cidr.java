package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class Cidr {

    private String cidr = "0.0.0.0/32";
    private String description = "";

    public String getCidr() {
        return cidr;
    }

    public String getDescription() {
        return description;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
