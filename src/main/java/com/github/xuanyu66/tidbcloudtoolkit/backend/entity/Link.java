package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class Link {

  private String dsn;
  private String commandLine;
  private String springBootProperties;

  public String getDsn() {
    return dsn;
  }

  public void setDsn(String dsn) {
    this.dsn = dsn;
  }

  public String getCommandLine() {
    return commandLine;
  }

  public void setCommandLine(String commandLine) {
    this.commandLine = commandLine;
  }

  public String getSpringBootProperties() {
    return springBootProperties;
  }

  public void setSpringBootProperties(String springBootProperties) {
    this.springBootProperties = springBootProperties;
  }

  public String getSpringBootYMAL() {
    return springBootYMAL;
  }

  public void setSpringBootYMAL(String springBootYMAL) {
    this.springBootYMAL = springBootYMAL;
  }

  private String springBootYMAL;

}
