package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.JTableUtils;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.module.ClusterTableModel;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.service.DataService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class TiCloudUI {

  private JPanel mainPanel;
  private JTabbedPane TiCloudTabbedPane;
  private JPanel onePanel;
  private JPanel twoPanel;
  private JPanel threePanel;
  private JButton createAClusterButton;
  private JTable table1;
  private JButton refreshButton;
  private JPanel refresh;
  private JButton deleteClusterButton;
  private JButton setTrafficFiltersButton;
  private JButton generateConfigButton;
  private JTextField nameTextField;
  private JPasswordField passwordField;
  private JPanel create;

  public TiCloudUI() {
    createAClusterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ClusterVO clusterVO = new ClusterVO();
        clusterVO.setName(nameTextField.getText().trim());
        clusterVO.setRoot_password(passwordField.getText().trim());
        try {
          DataService.getDataService().createCluster(clusterVO);
          JOptionPane.showMessageDialog(null, "创建成功");
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage(), "创建失败",
              JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });

    refreshButton.addActionListener(e -> {
      ClusterTableModel model = new ClusterTableModel();
      table1.setModel(model);
      List<ClusterWrapper> clusterWrappers = DataService.getDataService().listClusters();
      for (int i = 0; i < clusterWrappers.size(); i++) {
        ClusterWrapper clusterWrapper = clusterWrappers.get(i);
        model.add(clusterWrapper);
      }

      // 不显示 org id，project id， cluster id
      table1.removeColumn(table1.getColumnModel().getColumn(0));
      table1.removeColumn(table1.getColumnModel().getColumn(1));
      table1.removeColumn(table1.getColumnModel().getColumn(2));
      JTableUtils.setTableStyle(table1);
    });

    deleteClusterButton.addActionListener(e -> {
      int selectedRow = table1.getSelectedRow();
      String orgId = table1.getModel().getValueAt(selectedRow, 0).toString();
      String projectId = table1.getModel().getValueAt(selectedRow, 1).toString();
      String clusterId = table1.getModel().getValueAt(selectedRow, 2).toString();
      ClustersSummary clustersSummary = new ClustersSummary();
      clustersSummary.setOrgId(orgId);
      clustersSummary.setProjectId(projectId);
      clustersSummary.setClusterId(clusterId);
      try {
        DataService.getDataService().deleteCluster(clustersSummary);
        JOptionPane.showMessageDialog(null, "删除成功");
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "删除失败",
            JOptionPane.ERROR_MESSAGE);
      }
      table1.clearSelection();
    });
  }

  public JComponent getPanel() {
    return mainPanel;
  }
}