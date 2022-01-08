package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
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
      List<ClusterWrapper> clusterWrappers = DataService.getDataService().listClusters();
      ClusterWrapper clusterWrapper = clusterWrappers.get(0);
      ClusterTableModel model = new ClusterTableModel();
      table1.setModel(model);
      model.add(clusterWrapper.getCluster());
      JTableUtils.setTableStyle(table1);
    });
  }

  public JComponent getPanel() {
    return mainPanel;
  }
}