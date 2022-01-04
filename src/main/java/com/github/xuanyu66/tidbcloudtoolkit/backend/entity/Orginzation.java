package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class Orginzation {

  private String id;
  private String name;
  private String created_at;
  private String email;
  private String country;
  private int timezone;
  private String status;
  private String plan;
  private String total_credit;
  private String remaining_credit;
  private int active_clusters_count;
  private boolean has_discount;
  private String discount_description;
  private double discount;
  private boolean can_replication;
  private boolean acknowledge_cn_policy;
  private int free_clusters_count;
  private boolean has_tried_dev_tier;
  private String third_party_account_provider;
  private String third_party_account_code;
  private int whitelist_flag;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return this.id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getCreated_at() {
    return this.created_at;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountry() {
    return this.country;
  }

  public void setTimezone(int timezone) {
    this.timezone = timezone;
  }

  public int getTimezone() {
    return this.timezone;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    return this.status;
  }

  public void setPlan(String plan) {
    this.plan = plan;
  }

  public String getPlan() {
    return this.plan;
  }

  public void setTotal_credit(String total_credit) {
    this.total_credit = total_credit;
  }

  public String getTotal_credit() {
    return this.total_credit;
  }

  public void setRemaining_credit(String remaining_credit) {
    this.remaining_credit = remaining_credit;
  }

  public String getRemaining_credit() {
    return this.remaining_credit;
  }

  public void setActive_clusters_count(int active_clusters_count) {
    this.active_clusters_count = active_clusters_count;
  }

  public int getActive_clusters_count() {
    return this.active_clusters_count;
  }

  public void setHas_discount(boolean has_discount) {
    this.has_discount = has_discount;
  }

  public boolean getHas_discount() {
    return this.has_discount;
  }

  public void setDiscount_description(String discount_description) {
    this.discount_description = discount_description;
  }

  public String getDiscount_description() {
    return this.discount_description;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

  public double getDiscount() {
    return this.discount;
  }

  public void setCan_replication(boolean can_replication) {
    this.can_replication = can_replication;
  }

  public boolean getCan_replication() {
    return this.can_replication;
  }

  public void setAcknowledge_cn_policy(boolean acknowledge_cn_policy) {
    this.acknowledge_cn_policy = acknowledge_cn_policy;
  }

  public boolean getAcknowledge_cn_policy() {
    return this.acknowledge_cn_policy;
  }

  public void setFree_clusters_count(int free_clusters_count) {
    this.free_clusters_count = free_clusters_count;
  }

  public int getFree_clusters_count() {
    return this.free_clusters_count;
  }

  public void setHas_tried_dev_tier(boolean has_tried_dev_tier) {
    this.has_tried_dev_tier = has_tried_dev_tier;
  }

  public boolean getHas_tried_dev_tier() {
    return this.has_tried_dev_tier;
  }

  public void setThird_party_account_provider(String third_party_account_provider) {
    this.third_party_account_provider = third_party_account_provider;
  }

  public String getThird_party_account_provider() {
    return this.third_party_account_provider;
  }

  public void setThird_party_account_code(String third_party_account_code) {
    this.third_party_account_code = third_party_account_code;
  }

  public String getThird_party_account_code() {
    return this.third_party_account_code;
  }

  public void setWhitelist_flag(int whitelist_flag) {
    this.whitelist_flag = whitelist_flag;
  }

  public int getWhitelist_flag() {
    return this.whitelist_flag;
  }
}
