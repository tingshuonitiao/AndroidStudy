package com.example.tsnt.arouter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.tsnt.R;

import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_PATH_TEST2;

@Route(path = AROUTER_PATH_TEST2)
public class ARouterTest2Activity extends AppCompatActivity {

    public static final String AROUTER_PATH_TEST2 = "/tsnt/aroutertest2";
    public static final String AROUTER_TEST2_PARAM_NAME = "name";
    public static final String AROUTER_TEST2_PARAM_AGE = "age";
    public static final String AROUTER_TEST2_PARAM_HOMETOWN = "hometown";
    public static final String AROUTER_TEST2_PARAM_PERSON = "person";

    @Autowired(name = AROUTER_TEST2_PARAM_NAME)
    public String name;
    @Autowired(name = AROUTER_TEST2_PARAM_AGE)
    public int age;
    @Autowired(name = AROUTER_TEST2_PARAM_HOMETOWN)
    public String hometown;
    @Autowired(name = AROUTER_TEST2_PARAM_PERSON)
    public Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_arouter_test2);
        findViewById(R.id.show_message_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message;
                if (person != null) {
                    message = "I'm " + person.getName()
                            + ", " + person.getAge() + " years old "
                            + "and from " + person.getHometown() + ".";

                } else {
                    message = "I'm " + name
                            + ", " + age + " years old "
                            + "and from " + hometown + ".";
                }
                Toast.makeText(ARouterTest2Activity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
