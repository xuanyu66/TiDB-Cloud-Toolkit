package com.github.xuanyu66.tidbcloudtoolkit.backend.constant;

public enum RegionEnum {

  REGION(0, "us-west-2"),
  TOKYO(1, "ao-northeast-1"),
  VIRGINIA(2, "us-east-1"),
  SINGAPORE(3, "ap-southeast-1");

  private final String name;

  RegionEnum(int number, String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
