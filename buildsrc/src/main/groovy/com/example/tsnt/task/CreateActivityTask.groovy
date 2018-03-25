package com.example.tsnt.task

import com.example.tsnt.extension.CreateActivityExtension
import com.example.tsnt.template.ActivityTemplate
import com.example.tsnt.template.XmlTemplate
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-24 21:30
 * @Description:
 */

class CreateActivityTask extends DefaultTask {
    @TaskAction
    void createActivity() {
        def extension = project.extensions.findByType(CreateActivityExtension)
        def applicationId = extension.applicationId
        def activityName = extension.activityName
        def packageName = extension.packageName
        generateXml(activityName)
        generateClass(activityName, packageName, applicationId)
    }

    // 创建Activiy对应的xml文件
    void generateXml(String activityName) {
        def xmlPath = project.projectDir.toString() + "/src/main/res/layout/"
        def fileName = "activity_" + activityName.toLowerCase() + ".xml"
        def template = new XmlTemplate().template
        generateFile(xmlPath, fileName, template)
    }

    // 创建Activity对应的class文件
    void generateClass(String activityName, String packageName, String applicationId) {
        def packagePath = applicationId.replace(".", "/") + "/" + packageName
        def activityPath = project.projectDir.toString() + "/src/main/java/" + packagePath + "/"
        def fileName = activityName + "Activity" + ".java"
        def binding = [
                applicationId: applicationId,
                packageName  : packageName,
                activityName : activityName + "Activity",
                xmlName      : "activity_" + activityName.toLowerCase()
        ]
        def activityTemplate = new ActivityTemplate()
        def template = makeTemplate(activityTemplate.template, binding)
        generateFile(activityPath, fileName, template)
    }

    // 加载模板
    def makeTemplate(def template, def binding) {
        def engine = new groovy.text.GStringTemplateEngine()
        return engine.createTemplate(template).make(binding)
    }

    /**
     * 生成文件
     *
     * @param path
     * @param fileName
     * @param template
     */
    void generateFile(def path, def fileName, def template) {
        File dir = new File(path)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        File file = new File(path + fileName)
        if (!file.exists()) {
            file.createNewFile()
        } else {
            return
        }
        FileOutputStream out = new FileOutputStream(file, false)
        out.write(template.toString().getBytes("utf-8"))
        out.close()
    }
}
