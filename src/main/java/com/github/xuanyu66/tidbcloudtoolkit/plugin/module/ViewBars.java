package com.github.xuanyu66.tidbcloudtoolkit.plugin.module;

import com.github.xuanyu66.tidbcloudtoolkit.plugin.ui.TiCloudUI;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.ui.JBSplitter;

public class ViewBars extends SimpleToolWindowPanel {

    private Project project;
    private TiCloudUI tiCloudUI;

    public ViewBars(Project project) {
        super(false, true);
        this.project = project;
        tiCloudUI = new TiCloudUI();

        // 设置窗体侧边栏按钮
        DefaultActionGroup group = new DefaultActionGroup();
        group.add(new SettingBar(this));

        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("bar", group, false);
        toolbar.setTargetComponent(this);
        setToolbar(toolbar.getComponent());

        // 添加
        JBSplitter splitter = new JBSplitter(false);
        splitter.setSplitterProportionKey("main.splitter.key");
        splitter.setFirstComponent(tiCloudUI.getPanel());
        splitter.setProportion(0.3f);
        setContent(splitter);
    }

    public Project getProject() {
        return project;
    }

    public TiCloudUI getCloudUI() {
        return tiCloudUI;
    }
}