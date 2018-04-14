package com.example.tsnt.template;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-03-28 15:42
 * @Description:
 */

class ActivityNodeTemplate {
    def template = '''
        <activity android:name=".${packageName}.${activityName}Activity"/>
'''
}
