package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import javax.swing.*;

public class SettingUI {
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;

    public JComponent getPanel() {
        return mainPanel;
    }

    public JTextField getUsername() {
        return usernameTextField;
    }

    public JTextField getPassword() {
        return passwordTextField;
    }
}
