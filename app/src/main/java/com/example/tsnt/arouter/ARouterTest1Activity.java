package com.example.tsnt.arouter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tsnt.R;

import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_PATH_TEST2;
import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_TEST2_PARAM_AGE;
import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_TEST2_PARAM_HOMETOWN;
import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_TEST2_PARAM_NAME;
import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_TEST2_PARAM_PERSON;
import static com.example.tsnt.arouter.ARouterTest3Activity.AROUTER_PATH_TEST3;
import static com.example.tsnt.arouter.ARouterTest3Activity.AROUTER_TEST3_PARAM_AGE;
import static com.example.tsnt.arouter.ARouterTest3Activity.AROUTER_TEST3_PARAM_PERSON;

public class ARouterTest1Activity extends AppCompatActivity {

    private TextView passBaseTv;
    private TextView passObjectTv;
    private TextView passObjectNotAutoWiredTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_arouter_test1);
        passBaseTv = (TextView) findViewById(R.id.pass_base_tv);
        passObjectTv = (TextView) findViewById(R.id.pass_object_tv);
        passObjectNotAutoWiredTv = (TextView) findViewById(R.id.pass_object_tv_not_autowired);
        passBaseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(AROUTER_PATH_TEST2)
                        .withString(AROUTER_TEST2_PARAM_NAME, "小李")
                        .withInt(AROUTER_TEST2_PARAM_AGE, 23)
                        .withString(AROUTER_TEST2_PARAM_HOMETOWN, "上海")
                        .navigation();
            }
        });
        passObjectTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName("老王");
                person.setAge(58);
                person.setHometown("山东");
                ARouter.getInstance().build(AROUTER_PATH_TEST2)
                        .withObject(AROUTER_TEST2_PARAM_PERSON, person)
                        .navigation();
            }
        });
        passObjectNotAutoWiredTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = new Person();
                person.setName("哈哈");
                person.setHometown("Yhh");
                ARouter.getInstance().build(AROUTER_PATH_TEST3)
                        .withSerializable(AROUTER_TEST3_PARAM_PERSON, person)
                        .withInt(AROUTER_TEST3_PARAM_AGE, 32)
                        .navigation();
            }
        });
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, ARouterTest1Activity.class);
        context.startActivity(intent);
    }
}
