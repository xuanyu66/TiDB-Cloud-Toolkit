package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

import java.util.List;

public class ClusterList {

  private int total;
  private List<Cluster> items;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Cluster> getItems() {
    return items;
  }

  public void setItems(List<Cluster> items) {
    this.items = items;
  }
}
