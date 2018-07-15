package com.example.tsnt.hotfix;

import android.content.Context;
import android.widget.Toast;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-07-15 20:41
 * @Description:
 */

class SimpleHotFixBugTest {
    public void getBug(Context context) {
        int i = 10;
        int a = 0;
        Toast.makeText(context, "Hello,I am " + i / a + " years old.", Toast.LENGTH_SHORT).show();
    }
}
