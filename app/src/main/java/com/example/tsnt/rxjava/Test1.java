package com.example.tsnt.rxjava;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-08-26 20:12
 * @Description:
 */

public class Test1 {
    private static final String TAG = Test1.class.getSimpleName();

    public void test1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                return 1 + integer;
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn((AndroidSchedulers.mainThread()))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "complete");
                    }
                });
    }

    public void test2() {
        Flowable.range(0, 10)
                .subscribe(new Subscriber<Integer>() {
                    Subscription sub;

                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.w("TAG", "onsubscribe start");
                        sub = s;
                        sub.request(1);
                        Log.w("TAG", "onsubscribe end");
                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.w("TAG", "onNext--->" + o);
                        sub.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.w("TAG", "onComplete");
                    }
                });

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onComplete();
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return Integer.parseInt(s);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe() called with: d = [" + d + "]");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "onNext() called with: value = [" + value + "]");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete() called");
            }
        });
    }
}
