package com.example.tsnt.plugin;

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

        project.tasks.create('ballTask') {
            println("ballTask in configuration!")
            doLast {
                println 'ballTask in execution!'
            }
        }
    }
}
