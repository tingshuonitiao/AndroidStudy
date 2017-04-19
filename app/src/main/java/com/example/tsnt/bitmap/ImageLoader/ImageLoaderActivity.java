package com.example.tsnt.bitmap.ImageLoader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

public class ImageLoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212363&di=74ebc2ecb385802edd97af6e3ba96e94&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201510%2F10%2F20151010211325_ZdA4R.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492617667611&di=db5e80646ffc9e0912d8cfbe13cbfbcb&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F14%2F25%2F43Y58PICfJB_1024.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212400&di=17b073cb9a7dd040b2814a41a3a81cbc&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_big%2F201502%2F0e93d8ab02314174a933b5f00438d357.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212363&di=74ebc2ecb385802edd97af6e3ba96e94&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201510%2F10%2F20151010211325_ZdA4R.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492617667611&di=db5e80646ffc9e0912d8cfbe13cbfbcb&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F14%2F25%2F43Y58PICfJB_1024.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212400&di=17b073cb9a7dd040b2814a41a3a81cbc&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_big%2F201502%2F0e93d8ab02314174a933b5f00438d357.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212363&di=74ebc2ecb385802edd97af6e3ba96e94&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201510%2F10%2F20151010211325_ZdA4R.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492617667611&di=db5e80646ffc9e0912d8cfbe13cbfbcb&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F14%2F25%2F43Y58PICfJB_1024.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212400&di=17b073cb9a7dd040b2814a41a3a81cbc&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_big%2F201502%2F0e93d8ab02314174a933b5f00438d357.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212363&di=74ebc2ecb385802edd97af6e3ba96e94&imgtype=jpg&er=1&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201510%2F10%2F20151010211325_ZdA4R.jpeg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492617667611&di=db5e80646ffc9e0912d8cfbe13cbfbcb&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F14%2F25%2F43Y58PICfJB_1024.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493212400&di=17b073cb9a7dd040b2814a41a3a81cbc&imgtype=jpg&er=1&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_big%2F201502%2F0e93d8ab02314174a933b5f00438d357.jpg");
        GridViewAdapter adapter = new GridViewAdapter(list);
        gridView.setAdapter(adapter);
    }
}
