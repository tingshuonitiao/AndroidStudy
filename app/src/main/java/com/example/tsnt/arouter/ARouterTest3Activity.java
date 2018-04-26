package com.example.tsnt.arouter;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tsnt.R;

import static com.example.tsnt.arouter.ARouterTest2Activity.AROUTER_PATH_TEST2;
import static com.example.tsnt.arouter.ARouterTest3Activity.AROUTER_PATH_TEST3;

@Route(path = AROUTER_PATH_TEST3)
public class ARouterTest3Activity extends AppCompatActivity {

    public static final String AROUTER_PATH_TEST3 = "/tsnt/aroutertest3";
    public static final String AROUTER_TEST3_PARAM_PERSON = "person";
    public static final String AROUTER_TEST3_PARAM_AGE = "age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_arouter_test3);
        findViewById(R.id.show_message_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person person = (Person) getIntent().getSerializableExtra(AROUTER_TEST3_PARAM_PERSON);
                int age = getIntent().getIntExtra(AROUTER_TEST3_PARAM_AGE, 0);
                String message;
                message = "I'm " + person.getName()
                        + ", " + age + " years old "
                        + "and from " + person.getHometown() + ".";
                Toast.makeText(ARouterTest3Activity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
