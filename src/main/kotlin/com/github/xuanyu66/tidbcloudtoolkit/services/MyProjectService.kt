package com.github.xuanyu66.tidbcloudtoolkit.services

import com.intellij.openapi.project.Project
import com.github.xuanyu66.tidbcloudtoolkit.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
