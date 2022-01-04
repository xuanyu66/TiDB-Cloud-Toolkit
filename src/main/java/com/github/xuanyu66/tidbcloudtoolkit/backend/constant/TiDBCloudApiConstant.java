package com.github.xuanyu66.tidbcloudtoolkit.backend.constant;

public class TiDBCloudApiConstant {

  public static final String HOST = "us-west-2.prod.aws.tidbcloud.com";

  public static final String VERSION = "api/v1";

  public static final String GET_ORGS = "orgs/me";

  public static final String GET_PROJECTS = "api/v1/orgs/{0}/projects";

  public static final String GET_CLUSTERS = "api/v1/orgs/{0}/projects/{1}/clusters";

  public static final String CREATE_CLUSTERS = "api/v1/orgs/{0}/projects/{1}/clusters";

  public static final String DELETE_CLUSTERS = "api/v1/orgs/{0}/projects/{1}/clusters/{2}";

  public static final String GET_CLUSTER_DETAIL = "api/v1/orgs/{0}/projects/{1}/clusters/{2}";

  public static final String GET_MYIP = "my-ip";

  public static final String SET_TRAFFIC_FILTER = "traffic_filter";

  public static final String AUTH_URL = "https://tidbcloud.com/";

}
