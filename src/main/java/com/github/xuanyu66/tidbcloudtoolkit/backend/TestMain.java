package com.github.xuanyu66.tidbcloudtoolkit.backend;

import com.github.xuanyu66.tidbcloudtoolkit.plugin.service.DataService;
import java.io.IOException;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitServiceImpl;
import java.util.List;

//TODO to delete
public class TestMain {

  public static void main(String[] args) throws IOException {
    TiDBCloudToolkitServiceImpl toolkitService = new TiDBCloudToolkitServiceImpl("1136742008@qq.com", "shiyuhangA1");
    List<ClusterWrapper> clusterWrappers = toolkitService.listCluster();
    ClusterWrapper clusterWrapper = clusterWrappers.get(0);
    toolkitService.getConnection(clusterWrapper.getClustersSummary());

//    BaseHttpClient baseHttpClient = BaseHttpClient.newBuilder().withUserName("mockName")
//        .withPassword("mockPassword")
//        .build();
//    Request request = new Request.Builder().url(
//            "mockUrl")
//        .build();
//    Response response = baseHttpClient.request(request);
//    System.out.println(response.body().string());
  }
}