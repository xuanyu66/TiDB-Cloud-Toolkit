package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.*;
import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.ToolkitException;
import com.github.xuanyu66.tidbcloudtoolkit.backend.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;


public class TiDBCloudToolkitService {

    private TiDBCloudToolkitRepository tiDBCloudToolkitRepository;

    private TiDBCloudToolkitService() {
    }

    public TiDBCloudToolkitService(String name, String password) {
        tiDBCloudToolkitRepository = new TiDBCloudToolkitRepository(name, password);
    }

    public void refreshToken(String name, String password) {
        tiDBCloudToolkitRepository.refreshToken(name, password);
    }


    public ClusterVO getClusterDetail() {
        try {
            String org = tiDBCloudToolkitRepository.getOrg();
            String orgId = GsonUtil.getAsString(org, "id");

            ResponseEntity projects = GsonUtil.from(tiDBCloudToolkitRepository.getProjects(orgId), ResponseEntity.class);
            String projectId = projects.getFirstId();

            ResponseEntity clusters = GsonUtil.from(tiDBCloudToolkitRepository.getClusters(orgId, projectId), ResponseEntity.class);
            String clusterId = clusters.getFirstId();

            String json = tiDBCloudToolkitRepository.getClusterDetail(orgId, projectId, clusterId);
            return GsonUtil.from(json, ClusterVO.class);
        } catch (Exception e) {
            throw new ToolkitException("getCluster fail", e);
        }

    }

    public boolean createCluster(Cluster cluster) {
        try {
            String org = tiDBCloudToolkitRepository.getOrg();
            String orgId = GsonUtil.getAsString(org, "id");

            ResponseEntity projects = GsonUtil.from(tiDBCloudToolkitRepository.getProjects(orgId), ResponseEntity.class);
            String projectId = projects.getFirstId();

            return tiDBCloudToolkitRepository.createCluster(orgId, projectId, GsonUtil.to(cluster));
        } catch (Exception e) {
            throw new ToolkitException("createCluster fail", e);
        }

    }

    public boolean deleteCluster() {
        try {
            String org = tiDBCloudToolkitRepository.getOrg();
            String orgId = GsonUtil.getAsString(org, "id");

            ResponseEntity projects = GsonUtil.from(tiDBCloudToolkitRepository.getProjects(orgId), ResponseEntity.class);
            String projectId = projects.getFirstId();

            ResponseEntity clusters = GsonUtil.from(tiDBCloudToolkitRepository.getClusters(orgId, projectId), ResponseEntity.class);
            String clusterId = clusters.getFirstId();

            return tiDBCloudToolkitRepository.deleteCluster(orgId, projectId, clusterId);
        } catch (Exception e) {
            throw new ToolkitException("deleteCluster fail", e);
        }

    }

    public boolean setTrafficFilter(boolean all) {
        try {
            String org = tiDBCloudToolkitRepository.getOrg();
            String orgId = GsonUtil.getAsString(org, "id");

            ResponseEntity projects = GsonUtil.from(tiDBCloudToolkitRepository.getProjects(orgId), ResponseEntity.class);
            String projectId = projects.getFirstId();

            ResponseEntity clusters = GsonUtil.from(tiDBCloudToolkitRepository.getClusters(orgId, projectId), ResponseEntity.class);
            String clusterId = clusters.getFirstId();

            List<Cidr> cidrList = new ArrayList<>();
            Traffic traffic = new Traffic(new Traffic.Cidrs(cidrList));
            Cidr cidr = new Cidr();
            cidrList.add(cidr);
            if (all) {
                return tiDBCloudToolkitRepository.setTrafficFilter(orgId, projectId, clusterId, GsonUtil.to(traffic));
            } else {
                String myIp = GsonUtil.getAsString(tiDBCloudToolkitRepository.getMyIP(), "ip");
                cidr.setCidr(myIp + "/32");
                return tiDBCloudToolkitRepository.setTrafficFilter(orgId, projectId, clusterId, GsonUtil.to(traffic));
            }
        } catch (Exception e) {
            throw new ToolkitException("setTrafficFilter fail", e);
        }
    }


    public static void main(String[] args) {
        TiDBCloudToolkitService toolkitService = new TiDBCloudToolkitService("1136742008@qq.com", "shiyuhangA1");
        Cluster cluster = new Cluster();
        cluster.setName("test1");
        cluster.setRoot_password("shiyuhang");
        toolkitService.createCluster(cluster);
    }
}
