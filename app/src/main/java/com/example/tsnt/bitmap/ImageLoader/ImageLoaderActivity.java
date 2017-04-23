package com.example.tsnt.bitmap.ImageLoader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.GridView;

import com.example.tsnt.R;

import java.util.ArrayList;
import java.util.List;

public class ImageLoaderActivity extends Activity implements AbsListView.OnScrollListener {
    private List<String>    mUrlList;
    private GridView        mGridView;
    private GridViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageloader);
        initView();
        initData();
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.gridview);
        mUrlList = new ArrayList<>();
        mAdapter = new GridViewAdapter(mUrlList, ImageLoaderActivity.this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(this);
    }

    private void initData() {
        mUrlList.add("http://b.hiphotos.baidu.com/zhidao/pic/item/a6efce1b9d16fdfafee0cfb5b68f8c5495ee7bd8.jpg");
        mUrlList.add("http://pic47.nipic.com/20140830/7487939_180041822000_2.jpg");
        mUrlList.add("http://pic41.nipic.com/20140518/4135003_102912523000_2.jpg");
        mUrlList.add("http://img2.imgtn.bdimg.com/it/u=1133260524);1171054226&fm=21&gp=0.jpg");
        mUrlList.add("http://h.hiphotos.baidu.com/image/pic/item/3b87e950352ac65c0f1f6e9efff2b21192138ac0.jpg");
        mUrlList.add("http://pic42.nipic.com/20140618/9448607_210533564001_2.jpg");
        mUrlList.add("http://pic10.nipic.com/20101027/3578782_201643041706_2.jpg");
        mUrlList.add("http://picview01.baomihua.com/photos/20120805/m_14_634797817549375000_37810757.jpg");
        mUrlList.add("http://img2.3lian.com/2014/c7/51/d/26.jpg");
        mUrlList.add("http://img3.3lian.com/2013/c1/34/d/93.jpg");
        mUrlList.add("http://b.zol-img.com.cn/desk/bizhi/image/3/960x600/1375841395686.jpg");
        mUrlList.add("http://picview01.baomihua.com/photos/20120917/m_14_634834710114218750_41852580.jpg");
        mUrlList.add("http://cdn.duitang.com/uploads/item/201311/03/20131103171224_rr2aL.jpeg");
        mUrlList.add("http://imgrt.pconline.com.cn/images/upload/upc/tx/wallpaper/1210/17/c1/spcgroup/14468225_1350443478079_1680x1050.jpg");
        mUrlList.add("http://pic41.nipic.com/20140518/4135003_102025858000_2.jpg");
        mUrlList.add("http://www.1tong.com/uploads/wallpaper/landscapes/200-4-730x456.jpg");
        mUrlList.add("http://pic.58pic.com/58pic/13/00/22/32M58PICV6U.jpg");
        mUrlList.add("http://picview01.baomihua.com/photos/20120629/m_14_634765948339062500_11778706.jpg");
        mUrlList.add("http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=429e7b1b92ef76c6d087f32fa826d1cc/7acb0a46f21fbe09cc206a2e69600c338744ad8a.jpg");
        mUrlList.add("http://pica.nipic.com/2007-12-21/2007122115114908_2.jpg");
        mUrlList.add("http://cdn.duitang.com/uploads/item/201405/13/20140513212305_XcKLG.jpeg");
        mUrlList.add("http://photo.loveyd.com/uploads/allimg/080618/1110324.jpg");
        mUrlList.add("http://img4.duitang.com/uploads/item/201404/17/20140417105820_GuEHe.thumb.700_0.jpeg");
        mUrlList.add("http://cdn.duitang.com/uploads/item/201204/21/20120421155228_i52eX.thumb.600_0.jpeg");
        mUrlList.add("http://img4.duitang.com/uploads/item/201404/17/20140417105856_LTayu.thumb.700_0.jpeg");
        mUrlList.add("http://img04.tooopen.com/images/20130723/tooopen_20530699.jpg");
        mUrlList.add("http://www.qjis.com/uploads/allimg/120612/1131352Y2-16.jpg");
        mUrlList.add("http://pic.dbw.cn/0/01/33/59/1335968_847719.jpg");
        mUrlList.add("http://a.hiphotos.baidu.com/image/pic/item/a8773912b31bb051a862339c337adab44bede0c4.jpg");
        mUrlList.add("http://h.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0feeea8a30f5e6034a85edf720f.jpg");
        mUrlList.add("http://img0.pconline.com.cn/pconline/bizi/desktop/1412/ER2.jpg");
        mUrlList.add("http://pic.58pic.com/58pic/11/25/04/91v58PIC6Xy.jpg");
        mUrlList.add("http://img3.3lian.com/2013/c2/32/d/101.jpg");
        mUrlList.add("http://pic25.nipic.com/20121210/7447430_172514301000_2.jpg");
        mUrlList.add("http://img02.tooopen.com/images/20140320/sy_57121781945.jpg");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("初次使用会从网络下载大概5MB的图片，确认要下载吗？");
        builder.setTitle("注意");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAdapter.setCanBeUpdated(true);
                mAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("否", null);
        builder.show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            mAdapter.setCanBeUpdated(true);
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.setCanBeUpdated(false);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
