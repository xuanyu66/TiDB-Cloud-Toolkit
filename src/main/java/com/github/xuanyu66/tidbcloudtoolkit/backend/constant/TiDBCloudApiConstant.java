package com.github.xuanyu66.tidbcloudtoolkit.backend.constant;

import com.github.xuanyu66.tidbcloudtoolkit.backend.service.ToolkitService;

import java.io.IOException;

public class TiDBCloudApiConstant {

    public static final String CLOUDURL = "https://us-west-2.prod.aws.tidbcloud.com";

    public static final String GET_ORGS = "/api/v1/orgs/me";

    public static final String GET_PROJECTS = "/api/v1/orgs/{0}/projects";

    public static final String GET_CLUSTERS = "/api/v1/orgs/{0}/projects/{1}/clusters";

    public static final String CREATE_CLUSTERS = "/api/v1/orgs/{0}/projects/{1}/clusters";

    public static final String DELETE_CLUSTERS = "/api/v1/orgs/{0}/projects/{1}/clusters/{2}";

    public static final String GET_CLUSTERDETAIL = "/api/v1/orgs/{0}/projects/{1}/clusters/{2}";

    public static final String GET_MYIP = "/api/v1/my-ip";

    public static final String SET_TRAFFIC_FILTER = "/api/v1/orgs/{0}/projects/{1}/clusters/{2}/traffic_filter";

    public static final String LOGIN = "https://tidb.auth0.com/usernamepassword/login";

}
