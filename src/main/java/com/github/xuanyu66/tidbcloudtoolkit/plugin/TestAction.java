package com.github.xuanyu66.tidbcloudtoolkit.plugin;

import com.github.xuanyu66.tidbcloudtoolkit.backend.entity.ClusterWrapper;
import com.github.xuanyu66.tidbcloudtoolkit.backend.service.TiDBCloudToolkitServiceImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import java.io.IOException;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        TiDBCloudToolkitServiceImpl toolkitService = new TiDBCloudToolkitServiceImpl("cs68614@gmail.com", "Xiaosong66");

        try {
            for (ClusterWrapper clusterWrapper : toolkitService.listCluster()) {
                System.out.println(clusterWrapper.getCluster().getId());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Project project = e.getData(PlatformDataKeys.PROJECT);
        PsiFile psiFile = e.getData(PlatformDataKeys.PSI_FILE);
        assert psiFile != null;
        String classPath = psiFile.getVirtualFile().getPath();
        Messages.showMessageDialog(project, "the files path:" + classPath, "Path", Messages.getInformationIcon());
        // trigger the notification by using the notification manager
    }
}
