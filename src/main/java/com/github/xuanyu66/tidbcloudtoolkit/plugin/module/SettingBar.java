package com.github.xuanyu66.tidbcloudtoolkit.plugin.module;

import com.github.xuanyu66.tidbcloudtoolkit.plugin.ui.SettingUI;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

public class SettingBar extends DumbAwareAction {

    private final ViewBars panel;

    public SettingBar(ViewBars panel) {
        super("Setting", "Click to setting", AllIcons.Modules.AddExcludedRoot);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ShowSettingsUtil.getInstance().editConfigurable(panel.getProject(), new SettingUI(panel.getCloudUI()));
    }

}
