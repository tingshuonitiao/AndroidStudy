package com.example.tsnt.view.limit_scroll_edittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tsnt.R;

/**
 * Created by tingshuonitiao on 17/12/16.
 * <p>
 * 限制字数, 并且实时显示字数的EditText
 */

public class LimitScrollEditText extends LinearLayout {

    private String hint;
    private int maxLength;

    private EditText content;
    private TextView textCount;
    private TextWatcher textWatcher;

    public LimitScrollEditText(Context context) {
        this(context, null);
    }

    public LimitScrollEditText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LimitScrollEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView(context);
        initData();
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.LimitScrollEditText);
        if (arr != null) {
            hint = arr.getString(R.styleable.LimitScrollEditText_hint);
            maxLength = arr.getInt(R.styleable.LimitScrollEditText_maxLength, 0);
            arr.recycle();
        }
    }

    private void initView(Context context) {
        inflate(context, R.layout.layout_limit_scroll_edittext, this);
        // 因为布局layout_limit_scroll_edittext使用了merge标签, 所以需要设置方向
        setOrientation(VERTICAL);
        content = (EditText) findViewById(R.id.content);
        textCount = (TextView) findViewById(R.id.textCount);
    }

    private void initData() {
        setHint(hint);
        setMaxLength(maxLength);
        setTextWatcher();
    }

    public void setHint(String hint) {
        if (!TextUtils.isEmpty(hint)) content.setHint(hint);
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = Math.max(0, maxLength);
        content.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.maxLength)});
        setTextCount();
    }

    private void setTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setTextCount();
            }
        };

        content.addTextChangedListener(textWatcher);
    }

    private void setTextCount() {
        if (TextUtils.isEmpty(content.getText())) {
            textCount.setText("0/" + maxLength);
        } else {
            textCount.setText(content.getText().toString().length() + "/" + maxLength);
        }
    }
}
