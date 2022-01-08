package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class Connection {

  public static final String COMMADNLINE = "mysql -u root -h %s -P %s -p";

  public static final String SPRINGBOOT_YMAL = "spring:\n"
      + "  datasource:\n"
      + "    username: root\n"
      + "    password: \n"
      + "    url: jdbc:mysql://%s:%s/mysql?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC\n";

  public static final String SPRINGBOOT_PROPERTIES = "spring.datasource.url=jdbc:mysql://%s:%s/mysql\n"
      + "spring.datasource.username=root\n"
      + "spring.datasource.password=";
}
