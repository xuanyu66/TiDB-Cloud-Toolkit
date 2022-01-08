package com.github.xuanyu66.tidbcloudtoolkit.backend;

import java.io.IOException;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitServiceImpl;
import okhttp3.Request;
import okhttp3.Response;

//TODO to delete
public class TestMain {

  public static void main(String[] args) throws IOException {
    TiDBCloudToolkitServiceImpl toolkitService = new TiDBCloudToolkitServiceImpl("cs68614@gmail.com", "Xiaosong66");
    for (ClusterWrapper clusterWrapper : toolkitService.listCluster()) {
      System.out.println(clusterWrapper.getCluster().getId());
    }

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