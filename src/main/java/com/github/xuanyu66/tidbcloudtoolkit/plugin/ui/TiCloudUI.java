package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterVO;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.service.DataService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.checkerframework.checker.units.qual.C;

public class TiCloudUI {

  private JPanel mainPanel;
  private JTabbedPane TiCloudTabbedPane;
  private JPanel onePanel;
  private JPanel twoPanel;
  private JPanel threePanel;
  private JButton createAClusterButton;
  private JPanel create;
  private JTextField nameTextField;
  private JPasswordField passwordField;

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
  }

  public JComponent getPanel() {
    return mainPanel;
  }
}
