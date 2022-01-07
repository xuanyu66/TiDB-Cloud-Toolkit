package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class ClusterWrapper {

  private final ClustersSummary clustersSummary;

  private final Cluster cluster;

  public ClusterWrapper(
      ClustersSummary clustersSummary,
      Cluster cluster) {
    this.clustersSummary = clustersSummary;
    this.cluster = cluster;
  }

  public ClustersSummary getClustersSummary() {
    return clustersSummary;
  }

  public Cluster getCluster() {
    return cluster;
  }
}