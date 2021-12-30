package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.BaseHttpClient;
import com.github.xuanyu66.tidbcloudtoolkit.backend.constant.TiDBCloudApiConstant;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Cluster;
import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.ToolkitException;
import com.github.xuanyu66.tidbcloudtoolkit.backend.util.GsonUtil;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

public class ToolkitService {

    private BaseHttpClient baseHttpClient;

    private ToolkitService() {
    }

    public ToolkitService(String name, String password) {
        baseHttpClient = BaseHttpClient.newBuilder().withUserName(name)
                .withPassword(password)
                .build();
    }

    public void refreshToken(String name, String password) {
        baseHttpClient = BaseHttpClient.newBuilder().withUserName(name)
                .withPassword(password)
                .build();
    }


    private String getOrg() {
        try {
            String url = urlGenerate(TiDBCloudApiConstant.GET_ORGS);
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = baseHttpClient.request(request);
            String result = Objects.requireNonNull(response.body()).string();
            return GsonUtil.getAsString(result, "id");
        } catch (Exception e) {
            throw new ToolkitException("getOrg fail", e);
        }
    }

    public String getProject() {
        String url = urlGenerate(TiDBCloudApiConstant.GET_PROJECTS, getOrg());
        return null;
    }

    public String getCluster(String clusterId) {
        return null;
    }

    public void createCluster(Cluster cluster) {

    }

    public void getClusterDetail(String clusterId) {

    }

    public void deleteCluster(String clusterId) {

    }

    public void setTrafficFilter(boolean all) {

    }

    private void getMyIP() {

    }

    private String urlGenerate(String api, String... args) {
        String url = MessageFormat.format(api, (Object) args);
        return TiDBCloudApiConstant.CLOUDURL + url;
    }

    public static void main(String[] args) {
        ToolkitService r = new ToolkitService("", "");
        System.out.println(r.getOrg());
    }

}
