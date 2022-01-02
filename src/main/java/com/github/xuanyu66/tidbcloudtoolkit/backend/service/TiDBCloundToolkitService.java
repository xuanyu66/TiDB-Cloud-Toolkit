package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.BaseHttpClient;
import com.github.xuanyu66.tidbcloudtoolkit.backend.constant.TiDBCloudApiConstant;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Cluster;
import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.ToolkitException;
import com.github.xuanyu66.tidbcloudtoolkit.backend.util.GsonUtil;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.util.Objects;

public class TiDBCloundToolkitService {

    private BaseHttpClient baseHttpClient;

    private TiDBCloundToolkitService() {
    }

    public TiDBCloundToolkitService(String name, String password) {
        refreshToken(name, password);
    }

    public void refreshToken(String name, String password) {
        baseHttpClient = BaseHttpClient.newBuilder().withUserName(name)
                .withPassword(password)
                .build();
    }


    private String getOrg() {
        try {
            //String url = urlGenerate(TiDBCloudApiConstant.GET_ORGS);
            HttpUrl url = new HttpUrl.Builder()
                    .scheme("https")
                    .host(TiDBCloudApiConstant.HOST)
                    .addPathSegments(TiDBCloudApiConstant.GET_ORGS)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Response response = baseHttpClient.request(request);
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            throw new ToolkitException("getOrg fail", e);
        }
    }

    public String getProject() {
        String orgId = GsonUtil.getAsString(getOrg(), "id");
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.ORGS)
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .build();
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
}
