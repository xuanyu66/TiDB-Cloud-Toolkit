package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.plugin.service.DataService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TiCloudUI {

    private JPanel mainPanel;
    private JTabbedPane TiCloudTabbedPane;
    private JPanel onePanel;
    private JPanel twoPanel;
    private JPanel threePanel;
    private JButton createAClusterButton;

    public TiCloudUI() {
        createAClusterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataService.getDataService().createCluster("");
            }
        });
    }

    public JComponent getPanel() {
        return mainPanel;
    }
}
