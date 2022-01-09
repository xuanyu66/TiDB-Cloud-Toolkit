package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.Link;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.JTableUtils;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.module.ClusterTableModel;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.service.DataService;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
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

    generateConfigButton.addActionListener(e -> {
      int selectedRow = table1.getSelectedRow();
      String orgId = table1.getModel().getValueAt(selectedRow, 0).toString();
      String projectId = table1.getModel().getValueAt(selectedRow, 1).toString();
      String clusterId = table1.getModel().getValueAt(selectedRow, 2).toString();
      ClustersSummary clustersSummary = new ClustersSummary();
      clustersSummary.setOrgId(orgId);
      clustersSummary.setProjectId(projectId);
      clustersSummary.setClusterId(clusterId);
      Link config = DataService.getDataService().getConfig(clustersSummary);

      JFrame frame=new JFrame("Config ");    //创建Frame窗口
      frame.setLayout(new GridLayout(8, 1));
      MJTextArea txtfield1=new MJTextArea(config.getCommandLine());    //创建文本框
      MJTextArea txtfield2=new MJTextArea(config.getDsn());
      MJTextArea txtfield3=new MJTextArea(config.getSpringBootProperties());
      MJTextArea txtfield4=new MJTextArea(config.getSpringBootYMAL());
      JScrollPane scrollPane1 = new JScrollPane(txtfield1);
      JScrollPane scrollPane2 = new JScrollPane(txtfield2);
      JScrollPane scrollPane3 = new JScrollPane(txtfield3);
      JScrollPane scrollPane4 = new JScrollPane(txtfield4);
      JLabel jLabel1 = new JLabel("Mysql 命令", JLabel.CENTER);
      frame.getContentPane().add(jLabel1);
      frame.getContentPane().add(scrollPane1);
      JLabel jLabel2 = new JLabel("DSN 风格", JLabel.CENTER);
      frame.getContentPane().add(jLabel2);
      frame.getContentPane().add(scrollPane2);
      JLabel jLabel3 = new JLabel("Spring Properties 风格", JLabel.CENTER);
      frame.getContentPane().add(jLabel3);
      frame.getContentPane().add(scrollPane3);
      JLabel jLabel4 = new JLabel("Spring Yaml 风格", JLabel.CENTER);
      frame.getContentPane().add(jLabel4);
      frame.getContentPane().add(scrollPane4);
      frame.setBounds(300,200,800,1000);
      frame.setVisible(true);
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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