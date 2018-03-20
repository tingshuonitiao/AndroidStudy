package com.example.tsnt.local_plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-18 20:59
 * @Description: my first gradle plugin
 */

class FirstPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        System.out.println("====================");
        System.out.println("Hello, FirstPlugin!");
        System.out.println("====================");

        project.tasks.create('new Task') {
            println("Hello, new Task -- created by FirstPlugin!")
        }
    }
}
