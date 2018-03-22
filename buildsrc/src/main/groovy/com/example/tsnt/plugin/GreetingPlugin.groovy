package com.example.tsnt.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-22 09:46
 * @Description:
 */

class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        def extension = project.extensions.create('greeting', com.example.tsnt.extension.GreetingPluginExtension)
        project.task('hello') {
            doLast {
                println "${extension.message} from ${extension.greeter}"
            }
        }
    }
}

