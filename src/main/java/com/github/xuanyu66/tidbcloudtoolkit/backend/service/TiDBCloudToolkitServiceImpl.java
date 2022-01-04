package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.*;
import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.ToolkitException;
import com.github.xuanyu66.tidbcloudtoolkit.backend.util.GsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TiDBCloudToolkitServiceImpl implements TiDBCloudToolkitService {

    private TiDBCloudToolkitRepository tiDBCloudToolkitRepository;

    private TiDBCloudToolkitServiceImpl() {
    }

    public TiDBCloudToolkitServiceImpl(String name, String password) {
        tiDBCloudToolkitRepository = new TiDBCloudToolkitRepository(name, password);
    }

    public Cluster getClusterDetail() {
        try {
            ClustersSummary clustersSummary = getClustersSummary();
            String json = tiDBCloudToolkitRepository.getClusterDetail(clustersSummary.getOrgId(), clustersSummary.getProjectId(), clustersSummary.getClusterId());
            return GsonUtil.from(json, Cluster.class);
        } catch (Exception e) {
            throw new ToolkitException("getCluster fail", e);
        }

    }

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

    public boolean deleteCluster() {
        try {
            ClustersSummary clustersSummary = getClustersSummary();
            return tiDBCloudToolkitRepository.deleteCluster(clustersSummary.getOrgId(), clustersSummary.getProjectId(), clustersSummary.getClusterId());
        } catch (Exception e) {
            throw new ToolkitException("deleteCluster fail", e);
        }

    }

    public boolean setTrafficFilter(boolean all) {
        try {
            ClustersSummary clustersSummary = getClustersSummary();
            List<Cidr> cidrList = new ArrayList<>();
            Traffic traffic = new Traffic(new Traffic.Cidrs(cidrList));
            Cidr cidr = new Cidr();
            cidrList.add(cidr);

            if (all) {
                return tiDBCloudToolkitRepository.setTrafficFilter(clustersSummary.getOrgId(), clustersSummary.getProjectId(), clustersSummary.getClusterId(), GsonUtil.to(traffic));
            } else {
                String myIp = GsonUtil.getAsString(tiDBCloudToolkitRepository.getMyIP(), "ip");
                cidr.setCidr(myIp + "/32");
                return tiDBCloudToolkitRepository.setTrafficFilter(clustersSummary.getOrgId(), clustersSummary.getProjectId(), clustersSummary.getClusterId(), GsonUtil.to(traffic));
            }
        } catch (Exception e) {
            throw new ToolkitException("setTrafficFilter fail", e);
        }
    }

    private ClustersSummary getClustersSummary() throws IOException {
        Orginzation org = tiDBCloudToolkitRepository.getOrg();
        String orgId = org.getId();

        ProjectList projects = tiDBCloudToolkitRepository.getProjects(orgId);
        String projectId = projects.getItems().get(0).getId();

        ClusterList clusters = tiDBCloudToolkitRepository.getClusters(orgId, projectId);
        String clusterId = clusters.getItems().get(0).getId();

        ClustersSummary clustersSummary = new ClustersSummary();
        clustersSummary.setOrgId(orgId);
        clustersSummary.setProjectId(projectId);
        clustersSummary.setClusterId(clusterId);

        return clustersSummary;
    }

    public static void main(String[] args) throws InterruptedException {
        TiDBCloudToolkitServiceImpl toolkitService = new TiDBCloudToolkitServiceImpl("", "");
        ClusterVO cluster = new ClusterVO();
        cluster.setName("test1");
        cluster.setRoot_password("shiyuhang");
        toolkitService.createCluster(cluster);
        Thread.sleep(5000);
        Cluster cluster1 = toolkitService.getClusterDetail();
        System.out.println(cluster1.getId());

    }
}
