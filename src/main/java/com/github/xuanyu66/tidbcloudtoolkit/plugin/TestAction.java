package com.github.xuanyu66.tidbcloudtoolkit.plugin;

import com.intellij.notification.*;
import com.intellij.notification.impl.NotificationGroupEP;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getData(PlatformDataKeys.PROJECT);
        PsiFile psiFile = e.getData(PlatformDataKeys.PSI_FILE);
        assert psiFile != null;
        String classPath = psiFile.getVirtualFile().getPath();
        Messages.showMessageDialog(project, "the files path:" + classPath, "Path", Messages.getInformationIcon());
        // trigger the notification by using the notification manager
    }
}
