package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

public class Project {

    private String id;
    private String name;
    private String created_at;
    private int active_clusters_count;
    private int users_count;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public int getActive_clusters_count() {
        return active_clusters_count;
    }

    public int getUsers_count() {
        return users_count;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setActive_clusters_count(int active_clusters_count) {
        this.active_clusters_count = active_clusters_count;
    }

    public void setUsers_count(int users_count) {
        this.users_count = users_count;
    }
}
