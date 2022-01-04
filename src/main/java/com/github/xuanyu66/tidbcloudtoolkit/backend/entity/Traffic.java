package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

import java.util.List;

public class Traffic {

    public Traffic(Cidrs traffic_filters) {
        this.traffic_filters = traffic_filters;
    }

    public Cidrs traffic_filters;

    public Cidrs getTraffic_filters() {
        return traffic_filters;
    }

    public void setTraffic_filters(Cidrs traffic_filters) {
        this.traffic_filters = traffic_filters;
    }

    public static class Cidrs {

        public Cidrs(List<Cidr> cidrs) {
            this.cidrs = cidrs;
        }

        public List<Cidr> cidrs;

        public List<Cidr> getCidrs() {
            return cidrs;
        }

        public void setCidrs(List<Cidr> cidrs) {
            this.cidrs = cidrs;
        }
    }

}
