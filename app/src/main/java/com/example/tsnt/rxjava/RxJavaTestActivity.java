package com.example.tsnt.rxjava;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tsnt.R;
import com.example.tsnt.utils.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTestActivity extends AppCompatActivity {

    public static final int REGISTER_SUCCESS_LOGIN_SUCCESS = 0;     // 注册成功, 登录成功
    public static final int REGISTER_SUCCESS_LOGIN_FAIL = 1;        // 注册成功, 登录失败
    public static final int REGISTER_FAIL = 2;                      // 注册失败

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_test);
    }

    public void onLoginSuccessClick(View view) {
        gotoRegister(REGISTER_SUCCESS_LOGIN_SUCCESS);
    }

    public void onLoginFailClick(View view) {
        gotoRegister(REGISTER_SUCCESS_LOGIN_FAIL);
    }

    public void onRegisterFailClick(View view) {
        gotoRegister(REGISTER_FAIL);
    }

    /**
     * 按序加载信息
     *
     * @param view
     */
    public void onLoadInfoInSequenceClick(View view) {
        int index = 0;
        loadSingleInfo(index).observeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return loadSingleInfo(integer);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        ToastUtil.showToast("new info are all loaded");
                    }
                });
    }

    /**
     * 发起注册请求
     *
     * @param type
     */
    private void gotoRegister(final int type) {
        register(type).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(Integer integer) throws Exception {
                        return login(type);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ToastUtil.showToast("sending request...");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        ToastUtil.showToast("login successfully");
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 注册
     *
     * @param type
     * @return
     */
    private Observable<Integer> register(final int type) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 模拟网络请求
                SystemClock.sleep(1000);
                if (registerSuccess(type)) {
                    // 注册成功
                    emitter.onNext(type);
                } else {
                    // 注册失败
                    emitter.onError(new Exception("fail to register!"));
                }
            }
        });
    }

    /**
     * 登录
     *
     * @param type
     * @return
     */
    private Observable<Integer> login(final int type) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // 模拟网络请求
                SystemClock.sleep(1000);
                if (loginSuccess(type)) {
                    // 登录成功
                    emitter.onNext(type);
                } else {
                    // 登录失败
                    emitter.onError(new Exception("fail to login!"));
                }
            }
        });
    }

    /**
     * 是否注册成功
     *
     * @param type
     * @return
     */
    private boolean registerSuccess(int type) {
        return type != REGISTER_FAIL;
    }

    /**
     * 是否登录成功
     *
     * @param type
     * @return
     */
    private boolean loginSuccess(int type) {
        return type == REGISTER_SUCCESS_LOGIN_SUCCESS;
    }

    /**
     * 获取dialog信息
     *
     * @param index
     * @return
     */
    private Observable<Integer> loadSingleInfo(final int index) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> emitter) throws Exception {
                SystemClock.sleep(200);
                long currTime = SystemClock.currentThreadTimeMillis();
                if (currTime % 2 == 0) {
                    emitter.onNext(index + 1);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showAlertDialog(index, new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                    emitter.onNext(index + 1);
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    /**
     * 获取某个dialog
     *
     * @param index
     * @param dismissListener
     */
    private void showAlertDialog(int index, final DialogInterface.OnDismissListener dismissListener) {
        new AlertDialog.Builder(this)
                .setTitle("Dialog index " + index)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (dismissListener != null) {
                            dismissListener.onDismiss(dialog);
                        }
                    }
                })
                .create()
                .show();
    }

    public static void launch(Context context) {
        context.startActivity(new Intent(context, RxJavaTestActivity.class));
    }
}
