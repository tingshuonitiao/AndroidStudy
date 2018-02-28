package com.example.tsnt.view.gluttonous_snake;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tsnt.R;

public class GluttonousSnakeActivity extends AppCompatActivity implements View.OnClickListener {

    private View up;
    private View down;
    private View left;
    private View right;
    private SnakeView snakeView;

    private AlertDialog failureDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_gluttonous_snake);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        snakeView = (SnakeView) findViewById(R.id.snake_view);

        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        snakeView.setSnakeViewStateChangeListener(new SnakeView.SnakeViewStateChangeListener() {
            @Override
            public void onStop() {
                showFailureDialog();
            }
        });
    }

    // 失败提示
    private void showFailureDialog() {
        if (failureDialog == null) {
            failureDialog = new AlertDialog.Builder(this)
                    .setMessage("Game over, restart?")
                    .setNegativeButton("abandon", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            failureDialog.dismiss();
                            failureDialog = null;
                            finish();
                        }
                    })
                    .setPositiveButton("restart", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            snakeView.restart();
                        }
                    })
                    .create();
        }
        failureDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.up:
                snakeView.turnUp();
                break;
            case R.id.down:
                snakeView.turnDown();
                break;
            case R.id.left:
                snakeView.turnLeft();
                break;
            case R.id.right:
                snakeView.turnRight();
                break;
        }
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, GluttonousSnakeActivity.class);
        context.startActivity(intent);
    }
}
