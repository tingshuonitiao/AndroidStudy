package com.example.tsnt.arouter;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-16 21:24
 * @Description:
 */

@Route(path = "/service/json")
public class JsonServiceImpl implements SerializationService {

    private Gson gson;

    @Override
    public void init(Context context) {
        gson = new Gson();
    }

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return (T) gson.fromJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return (T) gson.fromJson(input, clazz);
    }
}
