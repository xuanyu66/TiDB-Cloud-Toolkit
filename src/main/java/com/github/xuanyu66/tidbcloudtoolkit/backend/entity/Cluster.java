package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

import java.util.Map;

public class Cluster {

  private String id;
  private String name;
  private String status;
  private String secondary_status;
  private String version;
  private String provider;
  private String region;
  private String profile_name;
  private String created_at;
  private String create_progress;
  private boolean prometheus_ready;
  private Endpoint endpoint;

  public class Endpoint {

    private boolean ready;
    private String address;
    private int port;

    public boolean isReady() {
      return ready;
    }

    public void setReady(boolean ready) {
      this.ready = ready;
    }

    public String getAddress() {
      return address;
    }

    public void setAddress(String address) {
      this.address = address;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }
  }


  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getStatus() {
    return status;
  }

  public String getSecondary_status() {
    return secondary_status;
  }

  public String getVersion() {
    return version;
  }

  public String getProvider() {
    return provider;
  }

  public String getRegion() {
    return region;
  }

  public String getProfile_name() {
    return profile_name;
  }

  public String getCreated_at() {
    return created_at;
  }

  public String getCreate_progress() {
    return create_progress;
  }

  public boolean isPrometheus_ready() {
    return prometheus_ready;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setSecondary_status(String secondary_status) {
    this.secondary_status = secondary_status;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public void setProfile_name(String profile_name) {
    this.profile_name = profile_name;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public void setCreate_progress(String create_progress) {
    this.create_progress = create_progress;
  }

  public void setPrometheus_ready(boolean prometheus_ready) {
    this.prometheus_ready = prometheus_ready;
  }

  public Endpoint getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(Endpoint endpoint) {
    this.endpoint = endpoint;
  }
}