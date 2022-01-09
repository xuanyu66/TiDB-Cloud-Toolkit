package com.github.xuanyu66.tidbcloudtoolkit.plugin.module;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClusterTableModel extends AbstractTableModel {

  protected static String[] COLUMN_NAMES = {"Org Id", "Project Id", "Cluster Id", "Cluster Name",
      "Status", "Secondary Status", "Version", "Provider", "Region", "Profile Name", "Created At",
      "Create Progress", "Prometheus Ready"};
  protected static Class[] COLUMN_CLASSES = {String.class, String.class, String.class, String.class,
      String.class, String.class, String.class, String.class, String.class, String.class,
      String.class, String.class, Boolean.class};

  private List<ClusterWrapper> clusters;

  public ClusterTableModel() {
    clusters = new ArrayList<>(25);
  }

  @Override
  public int getRowCount() {
    return clusters.size();
  }

  @Override
  public int getColumnCount() {
    return COLUMN_NAMES.length;
  }

  @Override
  public String getColumnName(int column) {
    return COLUMN_NAMES[column];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return COLUMN_CLASSES[columnIndex];
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return false;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ClusterWrapper cluster = clusters.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return cluster.getClustersSummary().getOrgId();
      case 1:
        return cluster.getClustersSummary().getProjectId();
      case 2:
        return cluster.getCluster().getId();
      case 3:
        return cluster.getCluster().getName();
      case 4:
        return cluster.getCluster().getStatus();
      case 5:
        return cluster.getCluster().getSecondary_status();
      case 6:
        return cluster.getCluster().getVersion();
      case 7:
        return cluster.getCluster().getProvider();
      case 8:
        return cluster.getCluster().getRegion();
      case 9:
        return cluster.getCluster().getProfile_name();
      case 10:
        return cluster.getCluster().getCreated_at();
      case 11:
        return cluster.getCluster().getCreate_progress();
      case 12:
        return cluster.getCluster().isPrometheus_ready();

    }
    return null;
  }

  public void add(ClusterWrapper cluster) {
    int index = clusters.size();
    clusters.add(cluster);
    fireTableRowsInserted(index, index);
  }

  public ClusterWrapper clusterAt(int rowIndex) {
    return clusters.get(rowIndex);
  }

}