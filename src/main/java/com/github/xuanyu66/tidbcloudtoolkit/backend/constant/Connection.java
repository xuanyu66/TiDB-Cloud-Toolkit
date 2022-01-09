package com.github.xuanyu66.tidbcloudtoolkit.backend.constant;

public class Connection {

  public static final String COMMADNLINE = "mysql -u root -h %s -P %s -p %s";

  public static final String SPRINGBOOT_YMAL = "spring:\n"
      + "  datasource:\n"
      + "    username: root\n"
      + "    password: %s\n"
      + "    url: jdbc:mysql://%s:%s/mysql";

  public static final String SPRINGBOOT_PROPERTIES = "spring.datasource.url=jdbc:mysql://%s:%s/mysql\n"
      + "spring.datasource.username=root\n"
      + "spring.datasource.password=%s";

  public static final String DSN = "jdbc:mysql://root:%s@%s:%s/mysql";
}
