package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

import com.github.xuanyu66.tidbcloudtoolkit.backend.constant.RegionEnum;

public class ClusterVO {

  private String provider = "aws";
  //required
  private String name;
  //required
  private String root_password;
  private String region = RegionEnum.REGION.getName();
  private String profile_name = "s1.dev";
  private int tidb_count = 1;
  private int tikv_count = 1;
  private int tiflash_count = 1;

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

  public void setProvider(String provider) {
    this.provider = provider;
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

  public void setProfile_name(String profile_name) {
    this.profile_name = profile_name;
  }

  public void setTidb_count(int tidb_count) {
    this.tidb_count = tidb_count;
  }

  public void setTikv_count(int tikv_count) {
    this.tikv_count = tikv_count;
  }

  public void setTiflash_count(int tiflash_count) {
    this.tiflash_count = tiflash_count;
  }
}
