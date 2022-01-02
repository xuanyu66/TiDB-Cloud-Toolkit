package com.github.xuanyu66.tidbcloudtoolkit.backend.service;

import com.github.xuanyu66.tidbcloudtoolkit.backend.BaseHttpClient;
import com.github.xuanyu66.tidbcloudtoolkit.backend.constant.TiDBCloudApiConstant;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class TiDBCloudToolkitRepository {

    private BaseHttpClient baseHttpClient;

    private final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private TiDBCloudToolkitRepository() {
    }

    public TiDBCloudToolkitRepository(String name, String password) {
        refreshToken(name, password);
    }

    public void refreshToken(String name, String password) {
        baseHttpClient = BaseHttpClient.newBuilder().withUserName(name)
                .withPassword(password)
                .build();
    }

    public String getOrg() throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegments(TiDBCloudApiConstant.GET_ORGS)
                .build();

        return getResponse(url);
    }

    public String getProjects(String orgId) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment("orgs")
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .build();

        return getResponse(url);
    }

    public String getClusters(String orgId, String projectId) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment("orgs")
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .addPathSegment(projectId)
                .addPathSegment("clusters")
                .build();

        return getResponse(url);
    }

    public boolean createCluster(String orgId, String projectId, String json) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment("orgs")
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .addPathSegment(projectId)
                .addPathSegment("clusters")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, JSON))
                .build();

        Response response = baseHttpClient.request(request);
        return response.isSuccessful();
    }

    public String getClusterDetail(String orgId, String projectId, String clusterId) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment("orgs")
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .addPathSegment(projectId)
                .addPathSegment("clusters")
                .addPathSegment(clusterId)
                .build();

        return getResponse(url);

    }

    public boolean deleteCluster(String orgId, String projectId, String clusterId) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment("orgs")
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .addPathSegment(projectId)
                .addPathSegment("clusters")
                .addPathSegment(clusterId)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();

        Response response = baseHttpClient.request(request);
        return response.isSuccessful();
    }

    public boolean setTrafficFilter(String orgId, String projectId, String clusterId, String json) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment("orgs")
                .addPathSegment(orgId)
                .addPathSegment("projects")
                .addPathSegment(projectId)
                .addPathSegment("clusters")
                .addPathSegment(clusterId)
                .addPathSegment(TiDBCloudApiConstant.SET_TRAFFIC_FILTER)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, JSON))
                .build();

        Response response = baseHttpClient.request(request);
        return response.isSuccessful();
    }

    public String getMyIP() throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(TiDBCloudApiConstant.HOST)
                .addPathSegments(TiDBCloudApiConstant.VERSION)
                .addPathSegment(TiDBCloudApiConstant.GET_MYIP)
                .build();

        return getResponse(url);
    }

    private String getResponse(HttpUrl url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = baseHttpClient.request(request);
        return Objects.requireNonNull(response.body()).string();
    }
}
