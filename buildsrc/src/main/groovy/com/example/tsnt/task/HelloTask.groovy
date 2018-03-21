package com.example.tsnt.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-18 23:30
 * @Description:
 */

class HelloTask extends DefaultTask {
    String nameOfPerson = "David"

    @TaskAction
    void hello() {
        println "Hello, $nameOfPerson !"
    }
}

