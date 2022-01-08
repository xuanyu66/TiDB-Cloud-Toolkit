package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClustersSummary;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitService;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitServiceImpl;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class SettingUI implements Configurable {
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton loginButton;

    private TiCloudUI tiCloudUI;

    public SettingUI(TiCloudUI tiCloudUI){
        this.tiCloudUI = tiCloudUI;
    }


    public JComponent getPanel() {
        return mainPanel;
    }

    public JTextField getUsername() {
        return usernameTextField;
    }

    public JTextField getPassword() {
        return passwordTextField;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "TiCloud Setting";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {

    }
}
