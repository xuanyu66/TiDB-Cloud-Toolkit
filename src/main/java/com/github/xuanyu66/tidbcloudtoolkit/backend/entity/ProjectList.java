package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;


import java.util.List;

public class ProjectList {

  private int total;
  private List<Project> items;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List<Project> getItems() {
    return items;
  }

  public void setItems(List<Project> items) {
    this.items = items;
  }
}
