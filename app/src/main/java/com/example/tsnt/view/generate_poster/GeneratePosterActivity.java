package com.example.tsnt.view.generate_poster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.tsnt.R;
import com.example.tsnt.utils.CommonUtil;
import com.example.tsnt.utils.ViewUtil;

import static android.text.Spanned.SPAN_INCLUSIVE_EXCLUSIVE;
import static android.text.style.DynamicDrawableSpan.ALIGN_BASELINE;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-02-23 20:37
 * @Description: 生成一张海报
 */

public class GeneratePosterActivity extends AppCompatActivity {

    // 海报对应的Bitmap
    private Bitmap posterBitmap;
    // 去生成海报的按钮
    private Button generate;
    // 去加载海报的按钮
    private Button load;
    // 显示生成海报的ImageView
    private ImageView generatedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_poster);
        initView();
    }

    private void initView() {
        generate = (Button) findViewById(R.id.generate);
        load = (Button) findViewById(R.id.load);
        generatedView = (ImageView) findViewById(R.id.generated_view);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatePoster();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPoster();
            }
        });
    }

    // 生成海报
    private void generatePoster() {
        // 创建海报的View
        final View view = View.inflate(this, R.layout.layout_generate_view, null);
        // 设置海报中的文字
        TextView content = (TextView) view.findViewById(R.id.content);
        formatText(content);
        // 设置海报顶部的图片
        final ImageView topView = (ImageView) view.findViewById(R.id.top_view);
        String url = "http://photocdn.sohu.com/20120305/Img336668413.jpg";
        loadPicture(view, topView, url);
    }

    // 加载图片到海报上
    private void loadPicture(final View view, final ImageView topView, String url) {
        // 这里使用Glide去下载图片, 以下是Glide下载图片的回调函数
        Glide.with(this).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                topView.setImageBitmap(resource);
                // 生成海报对应的Bitmap
                posterBitmap = ViewUtil.generateViewInBitmap(GeneratePosterActivity.this, view, false);
                Toast.makeText(GeneratePosterActivity.this, "成功生成图片", Toast.LENGTH_SHORT).show();
                // 保存海报到本地
                savePoster(posterBitmap);
            }
        });
    }

    // 富文本排版
    private void formatText(TextView textView) {
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder("-" + getResources().getString(R.string.comment_about_kobe) + "-");
        ImageSpan font_quote = new ImageSpan(GeneratePosterActivity.this, R.mipmap.icon_font_quote, ALIGN_BASELINE);
        ImageSpan back_quote = new ImageSpan(GeneratePosterActivity.this, R.mipmap.icon_back_quote, ALIGN_BASELINE);
        // 设置评论的前引号
        stringBuilder.setSpan(font_quote, 0, 1, SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置评论的后引号
        stringBuilder.setSpan(back_quote, stringBuilder.length() - 1, stringBuilder.length(), SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(stringBuilder);
    }

    // 加载海报
    private void loadPoster() {
        if (posterBitmap != null) {
            generatedView.setImageBitmap(posterBitmap);
        } else {
            Toast.makeText(this, "还没有生成图片", Toast.LENGTH_SHORT).show();
        }
    }

    // 保存海报至本地
    private void savePoster(Bitmap bitmap) {
        CommonUtil.saveImage(this, "kobe_poster", bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (posterBitmap != null) {
            posterBitmap.recycle();
            posterBitmap = null;
        }
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, GeneratePosterActivity.class);
        context.startActivity(intent);
    }
}
