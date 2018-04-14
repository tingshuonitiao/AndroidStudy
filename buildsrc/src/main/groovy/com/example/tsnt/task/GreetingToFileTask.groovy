package com.example.tsnt.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-03-22 10:15
 * @Description:
 */

class GreetingToFileTask extends DefaultTask {

    def destination

    File getDestination() {
        project.file(destination)
    }

    @TaskAction
    def greet() {
        def file = getDestination()
        file.parentFile.mkdirs()
        file.write 'Hello!'
    }
}
