package com.github.xuanyu66.tidbcloudtoolkit.plugin.factory;

import com.github.xuanyu66.tidbcloudtoolkit.plugin.module.ViewBars;
import com.github.xuanyu66.tidbcloudtoolkit.plugin.ui.TiCloudUI;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class TiCloudFactory implements ToolWindowFactory {

    private TiCloudUI cloudUI = new TiCloudUI();


    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ViewBars viewPanel = new ViewBars(project);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(viewPanel, "", false);
        toolWindow.getContentManager().addContent(content, 0);

//        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//        Content content = contentFactory.createContent(cloudUI.getPanel(), "", false);
//        toolWindow.getContentManager().addContent(content);
    }
}
