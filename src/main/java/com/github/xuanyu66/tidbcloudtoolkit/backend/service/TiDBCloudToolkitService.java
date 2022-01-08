package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import java.io.IOException;
import java.util.List;

public interface TiDBCloudToolkitService {

  /**
   * refresh token if token expired
   *
   * @return
   */
  void refreshToken(String name, String password);

  /**
   * get Developer Tier Cluster detail
   *
   * @return
   */
  ClusterWrapper getCluster(ClustersSummary clustersSummary);

  /**
   * create Developer Tier Cluster
   *
   * @param cluster name and root_password is required, region is optional others are unchanged when
   *                create Developer Tier
   * @return
   */
  boolean createCluster(ClusterVO cluster);

  /**
   * delete Developer Tier Cluster
   *
   * @return
   */
  boolean deleteCluster(ClustersSummary clustersSummary);

  /**
   * TrafficFilter config the ip which can request db
   *
   * @param all true means all ip can request db false means local ip can request db
   * @return
   */
  boolean setTrafficFilter(boolean all, ClustersSummary clustersSummary);

  /**
   * return all list clusters
   *
   * @return
   */
  List<ClusterWrapper> listCluster() throws IOException;

  String getConnection(ClustersSummary clustersSummary);
}
