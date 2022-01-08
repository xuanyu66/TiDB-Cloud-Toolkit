package com.github.xuanyu66.tidbcloudtoolkit.plugin.module;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Cluster;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.table.AbstractTableModel;

public class ClusterTableModel extends AbstractTableModel {

  protected static String[] COLUMN_NAMES = {"Cluster Id", "Cluster Name", "Status",
      "Secondary Status", "Version", "Provider", "Region", "Profile Name", "Created At",
      "Create Progress", "Prometheus Ready", "Selected"};
  protected static Class[] COLUMN_CLASSES = {String.class, String.class, String.class, String.class,
      String.class, String.class, String.class, String.class, String.class, String.class,
      Boolean.class, Boolean.class};

  private Set<Integer> selected;
  private List<Cluster> clusters;

  public ClusterTableModel() {
    clusters = new ArrayList<>(25);
    selected = new TreeSet<Integer>();
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
    return columnIndex == 11;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Cluster cluster = clusters.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return cluster.getId();
      case 1:
        return cluster.getName();
      case 2:
        return cluster.getStatus();
      case 3:
        return cluster.getSecondary_status();
      case 4:
        return cluster.getVersion();
      case 5:
        return cluster.getProvider();
      case 6:
        return cluster.getRegion();
      case 7:
        return cluster.getProfile_name();
      case 8:
        return cluster.getCreated_at();
      case 9:
        return cluster.getCreate_progress();
      case 10:
        return cluster.isPrometheus_ready();

    }
    return null;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    if (columnIndex != 11) {
      return;
    }
    if (!(aValue instanceof Boolean)) {
      return;
    }
    boolean isSelected = (Boolean) aValue;
    if (isSelected) {
      selected.add(rowIndex);
    } else {
      selected.remove(rowIndex);
    }

    fireTableCellUpdated(rowIndex, columnIndex);
  }

  public void add(Cluster cluster) {
    int index = clusters.size();
    clusters.add(cluster);
    fireTableRowsInserted(index, index);
  }

  public Cluster clusterAt(int rowIndex) {
    return clusters.get(rowIndex);
  }

}