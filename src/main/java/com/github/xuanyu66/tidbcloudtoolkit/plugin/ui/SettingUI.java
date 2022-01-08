package com.github.xuanyu66.tidbcloudtoolkit.plugin.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingUI implements Configurable {
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton testConnectionButton;
    private JTextField tokenTextField;

    private TiCloudUI tiCloudUI;

    /**
     * test connection
     * @param tiCloudUI
     */
    public SettingUI(TiCloudUI tiCloudUI){
        this.tiCloudUI = tiCloudUI;
        testConnectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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

    /**
     * apply info
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        String tokenStr = tokenTextField.getText().trim();
        System.out.println(tokenStr);
    }
}
