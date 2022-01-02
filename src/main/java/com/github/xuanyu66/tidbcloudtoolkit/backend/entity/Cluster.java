package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

import com.github.xuanyu66.tidbcloudtoolkit.backend.constant.RegionEnum;

public class Cluster {

    public String org_id;
    public String project_id;
    public String provider = "aws";
    //required
    public String name;
    //required
    public String root_password;
    public String region = RegionEnum.REGION.getName();
    public String profile_name = "s1.dev";
    public int tidb_count = 1;
    public int tikv_count = 1;
    public int tiflash_count = 1;

    public String getOrg_id() {
        return org_id;
    }

    public String getProject_id() {
        return project_id;
    }

    public String getProvider() {
        return provider;
    }

    public String getName() {
        return name;
    }

    public String getRoot_password() {
        return root_password;
    }

    public String getRegion() {
        return region;
    }

    public String getProfile_name() {
        return profile_name;
    }

    public int getTidb_count() {
        return tidb_count;
    }

    public int getTikv_count() {
        return tikv_count;
    }

    public int getTiflash_count() {
        return tiflash_count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoot_password(String root_password) {
        this.root_password = root_password;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
