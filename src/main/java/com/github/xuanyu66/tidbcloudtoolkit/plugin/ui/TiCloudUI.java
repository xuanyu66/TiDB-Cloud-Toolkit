package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.JTableUtils;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.module.ClusterTableModel;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.service.DataService;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.ui.awt.RelativePoint;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
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
  private JButton createAClusterButton;
  private JTable table1;
  private JButton refreshButton;
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
      table1.removeColumn(table1.getColumnModel().getColumn(2));
      table1.removeColumn(table1.getColumnModel().getColumn(1));
      table1.removeColumn(table1.getColumnModel().getColumn(0));
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

    setTrafficFiltersButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int selectedRow = table1.getSelectedRow();
        String orgId = table1.getModel().getValueAt(selectedRow, 0).toString();
        String projectId = table1.getModel().getValueAt(selectedRow, 1).toString();
        String clusterId = table1.getModel().getValueAt(selectedRow, 2).toString();
        ClustersSummary clustersSummary = new ClustersSummary();
        clustersSummary.setOrgId(orgId);
        clustersSummary.setProjectId(projectId);
        clustersSummary.setClusterId(clusterId);

        JBPopupFactory instance = JBPopupFactory.getInstance();
        // 创建需要执行的任务
        Runnable all = () -> {
          try {
            DataService.getDataService().setTrafficFilter(true, clustersSummary);
            JOptionPane.showMessageDialog(null, "设置成功");
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "设置失败",
                JOptionPane.ERROR_MESSAGE);
          }
        };
        Runnable myIp = () -> {
          try {
            DataService.getDataService().setTrafficFilter(false, clustersSummary);
            JOptionPane.showMessageDialog(null, "设置成功");
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "设置失败",
                JOptionPane.ERROR_MESSAGE);
          }
        };
        ListPopup popup = instance.createConfirmation("Traffic Filter", "All", "Only myIp", all,
            myIp, 1);
        popup.setSize(new Dimension(200,70));
        popup.showInFocusCenter();
        table1.clearSelection();

      }
    });
    TiCloudTabbedPane.addComponentListener(new ComponentAdapter() {
    });
  }

  public JComponent getPanel() {
    return mainPanel;
  }
}