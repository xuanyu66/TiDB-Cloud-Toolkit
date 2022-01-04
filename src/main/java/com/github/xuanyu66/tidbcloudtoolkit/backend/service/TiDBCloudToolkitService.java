package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Cluster;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;

public interface TiDBCloudToolkitService {

    /**
     * get Developer Tier Cluster detail
     *
     * @return
     */
    Cluster getClusterDetail();

    /**
     * create Developer Tier Cluster
     *
     * @param cluster name and root_password is required, region is optional
     *                others are unchanged when create Developer Tier
     * @return
     */
    boolean createCluster(ClusterVO cluster);

    /**
     * delete Developer Tier Cluster
     *
     * @return
     */
    boolean deleteCluster();

    /**
     * TrafficFilter config the ip which can request db
     *
     * @param all true means all ip can request db
     *            false means local ip can request db
     * @return
     */
    boolean setTrafficFilter(boolean all);
}
