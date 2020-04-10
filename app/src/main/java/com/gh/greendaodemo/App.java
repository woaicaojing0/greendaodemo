package com.gh.greendaodemo;

import android.app.Application;

import com.gh.greendaodemo.base.DbHelper;

/**
 * @author Created by cj on 2020/4/9.
 * @description 说明
 * Email 1206067690@qq.com
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DbHelper.init(this,"cj",false);
    }
}
