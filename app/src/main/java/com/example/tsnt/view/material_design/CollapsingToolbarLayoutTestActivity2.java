package com.example.tsnt.view.material_design;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;

import com.example.tsnt.R;
import com.example.tsnt.utils.DisplayUtil;

public class CollapsingToolbarLayoutTestActivity2 extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout_test2);
        initView();
    }

    private void initView() {
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                // 注意，一般情况下verticalOffset是负值
                Log.d("verticalOffset", "verticalOffset = " + verticalOffset);
                if (Math.abs(verticalOffset) > DisplayUtil.dp2px(CollapsingToolbarLayoutTestActivity2.this, 250)) {
                    setTitleWhiteMode();
                } else {
                    setTitleTransparentMode();
                }
            }
        });
    }

    private void setTitleTransparentMode() {
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setStatusBarTransparent(CollapsingToolbarLayoutTestActivity2.this.getWindow());
    }

    private void setTitleWhiteMode() {
        toolbar.setBackgroundColor(Color.WHITE);
        setStatusBarNoTransparent(CollapsingToolbarLayoutTestActivity2.this.getWindow());
    }

    public static void setStatusBarTransparent(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.BLACK);
        }
    }

    public static void setStatusBarNoTransparent(Window window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(0x33000000);
            window.setNavigationBarColor(Color.BLACK);
        }
    }
}
