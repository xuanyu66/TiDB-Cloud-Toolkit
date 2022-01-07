package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Cidr;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Cluster;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterList;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Orginzation;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ProjectList;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Traffic;
import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.ToolkitException;
import com.github.xuanyu66.tidbcloudtoolkit.backend.util.GsonUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TiDBCloudToolkitServiceImpl implements TiDBCloudToolkitService {

  private final TiDBCloudToolkitRepository tiDBCloudToolkitRepository;

  public TiDBCloudToolkitServiceImpl(String name, String password) {
    tiDBCloudToolkitRepository = new TiDBCloudToolkitRepository(name, password);
  }

  public TiDBCloudToolkitServiceImpl(String token) {
    tiDBCloudToolkitRepository = new TiDBCloudToolkitRepository(token);
  }

  @Deprecated
  @Override
  public void refreshToken(String name, String password) {
    tiDBCloudToolkitRepository.refreshToken(name, password);
  }

  @Override
  public void refreshToken(String token) {
    tiDBCloudToolkitRepository.refreshToken(token);
  }

  @Override
  public ClusterWrapper getCluster(ClustersSummary clustersSummary) {
    try {
      String json = tiDBCloudToolkitRepository.getClusterDetail(clustersSummary.getOrgId(),
          clustersSummary.getProjectId(), clustersSummary.getClusterId());
      Cluster cluster = GsonUtil.from(json, Cluster.class);
      ClustersSummary clustersSummary1 = new ClustersSummary();
      clustersSummary1.setOrgId(clustersSummary.getOrgId());
      clustersSummary1.setProjectId(clustersSummary.getOrgId());
      clustersSummary1.setClusterId(clustersSummary.getClusterId());

      return new ClusterWrapper(clustersSummary1, cluster);
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
  public List<ClusterWrapper> listCluster() throws IOException {
    Orginzation org = tiDBCloudToolkitRepository.getOrg();
    String orgId = org.getId();

    ProjectList projects = tiDBCloudToolkitRepository.getProjects(orgId);
    String projectId = projects.getItems().get(0).getId();

    ClusterList clusters = tiDBCloudToolkitRepository.getClusters(orgId, projectId);

    return clusters.getItems().stream().map(cluster -> {
      ClustersSummary clustersSummary = new ClustersSummary();
      clustersSummary.setOrgId(orgId);
      clustersSummary.setProjectId(projectId);
      clustersSummary.setClusterId(cluster.getId());
      return new ClusterWrapper(clustersSummary, cluster);
    }).collect(Collectors.toList());
  }
}
