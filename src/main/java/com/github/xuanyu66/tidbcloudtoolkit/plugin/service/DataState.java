package com.github.xuanyu66.tidbcloudtoolkit.plugin.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;

import java.util.ArrayList;
import java.util.List;

public class DataState {
    private List<ClusterWrapper> clusterLists = new ArrayList<>();

    public List<ClusterWrapper> getClusterLists() {
        return clusterLists;
    }

    public void setClusterLists(List<ClusterWrapper> clusterLists) {
        this.clusterLists = clusterLists;
    }

}
