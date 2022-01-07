package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.*;
import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.ToolkitException;
import com.github.xuanyu66.tidbcloudtoolkit.backend.util.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TiDBCloudToolkitServiceImpl implements TiDBCloudToolkitService {

  private final TiDBCloudToolkitRepository tiDBCloudToolkitRepository;

  public TiDBCloudToolkitServiceImpl(String name, String password) {
    tiDBCloudToolkitRepository = new TiDBCloudToolkitRepository(name, password);
  }

  @Override
  public void refreshToken(String name, String password) {
    tiDBCloudToolkitRepository.refreshToken(name, password);
  }

  @Override
  public Cluster getCluster(ClustersSummary clustersSummary) {
    try {
      String json = tiDBCloudToolkitRepository.getClusterDetail(clustersSummary.getOrgId(),
          clustersSummary.getProjectId(), clustersSummary.getClusterId());
      return GsonUtil.from(json, Cluster.class);
    } catch (Exception e) {
      throw new ToolkitException("getCluster fail", e);
    }
  }

  @Override
  public boolean createCluster(ClusterVO cluster) {
    try {
      Orginzation org = tiDBCloudToolkitRepository.getOrg();
      String orgId = org.getId();

      ProjectList projects = tiDBCloudToolkitRepository.getProjects(orgId);
      String projectId = projects.getItems().get(0).getId();

      return tiDBCloudToolkitRepository.createCluster(orgId, projectId, GsonUtil.to(cluster));
    } catch (Exception e) {
      throw new ToolkitException("createCluster fail", e);
    }

  }

  @Override
  public boolean deleteCluster(ClustersSummary clustersSummary) {
    try {
      return tiDBCloudToolkitRepository.deleteCluster(clustersSummary.getOrgId(),
          clustersSummary.getProjectId(), clustersSummary.getClusterId());
    } catch (Exception e) {
      throw new ToolkitException("deleteCluster fail", e);
    }

  }

  @Override
  public boolean setTrafficFilter(boolean all, ClustersSummary clustersSummary) {
    try {
      List<Cidr> cidrList = new ArrayList<>();
      Traffic traffic = new Traffic(new Traffic.Cidrs(cidrList));
      Cidr cidr = new Cidr();
      cidrList.add(cidr);

      if (all) {
        return tiDBCloudToolkitRepository.setTrafficFilter(clustersSummary.getOrgId(),
            clustersSummary.getProjectId(), clustersSummary.getClusterId(), GsonUtil.to(traffic));
      } else {
        String myIp = GsonUtil.getAsString(tiDBCloudToolkitRepository.getMyIP(), "ip");
        cidr.setCidr(myIp + "/32");
        return tiDBCloudToolkitRepository.setTrafficFilter(clustersSummary.getOrgId(),
            clustersSummary.getProjectId(), clustersSummary.getClusterId(), GsonUtil.to(traffic));
      }
    } catch (Exception e) {
      throw new ToolkitException("setTrafficFilter fail", e);
    }
  }

  @Override
  public List<Cluster> listClusters() throws IOException {
    Orginzation org = tiDBCloudToolkitRepository.getOrg();
    String orgId = org.getId();

    ProjectList projects = tiDBCloudToolkitRepository.getProjects(orgId);
    String projectId = projects.getItems().get(0).getId();

    ClusterList clusters = tiDBCloudToolkitRepository.getClusters(orgId, projectId);

    return clusters.getItems();
  }
}
