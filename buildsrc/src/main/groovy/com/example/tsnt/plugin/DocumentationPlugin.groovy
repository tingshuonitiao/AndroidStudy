package com.example.tsnt.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @Author: zhangxiaozong
 * @Date: 2018-03-23 14:51
 * @Description:
 */

class DocumentationPlugin implements Plugin<Project> {
    void apply(Project project) {
        // Create a container of Book instances
        def books = project.container(com.example.tsnt.entity.Book)
        books.all {
            sourceFile = project.file("src/docs/$name")
        }
        // Add the container as an extension object
        project.extensions.books = books
    }
}
