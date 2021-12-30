package com.github.xuanyu66.tidbcloudtoolkit.backend;

import java.io.IOException;
import okhttp3.Request;
import okhttp3.Response;

//TODO to delete
public class TestMain {

  public static void main(String[] args) throws IOException {
    BaseHttpClient baseHttpClient = BaseHttpClient.newBuilder().withUserName("mockName")
        .withPassword("mockPassword")
        .build();
    Request request = new Request.Builder().url(
            "mockUrl")
        .build();
    Response response = baseHttpClient.request(request);
    System.out.println(response.body().string());
  }
}