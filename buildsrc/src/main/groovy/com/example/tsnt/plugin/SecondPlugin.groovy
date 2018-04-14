package com.example.tsnt.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @Author: tingshuonitiao
 * @Date: 2018-03-18 20:59
 * @Description: my first gradle plugin
 */

class SecondPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        System.out.println("====================");
        System.out.println("Hello, SecondPlugin!");
        System.out.println("====================");
    }
}
