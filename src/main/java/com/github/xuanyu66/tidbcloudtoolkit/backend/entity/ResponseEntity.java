package com.github.xuanyu66.tidbcloudtoolkit.backend.entity;

import java.util.List;
import java.util.Map;

public class ResponseEntity {

    public int total;
    public List<Map<String, Object>> items;

    public int getTotal() {
        return total;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public String getFirstId() {
        return items.get(0).get("id").toString();
    }
}
