package com.github.xuanyu66.tidbcloudtoolkit.plugin.factory;

import com.github.xuanyu66.tidbcloudtoolkit.plugin.ui.SettingUI;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SettingFactory implements SearchableConfigurable {

    private final SettingUI settingUI = new SettingUI();

    @Override
    public @NotNull @NonNls String getId() {
        return "tidb-cloud-toolkit.setting";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "TiDB Cloud Toolkit";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return settingUI.getPanel();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    /**
     * 获取设置信息
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        String userName = settingUI.getUsername().getText().trim();
        String password = settingUI.getPassword().getText().trim();
    }
}
