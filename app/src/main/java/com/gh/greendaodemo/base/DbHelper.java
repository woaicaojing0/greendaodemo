package com.gh.greendaodemo.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gh.greendaodemo.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.IOException;


/**
 * @author yfjin
 * @date 2018/9/27
 */

public class DbHelper {


    private static DbHelper instance;

    public static DbHelper instance() {
        if (null == instance) {
            throw new NullPointerException("DbHelper未初始化");
        }
        return instance;
    }


    private DaoSession mDaoSession;

    /**
     * 设置greenDao
     */
    public static void init(Context context) {
        if (null == instance) {
            instance = new DbHelper(context);
        }
    }

    /**
     * 设置greenDao
     */
    public static void clear() {
        instance = null;
    }


    private DbHelper(Context context) {
        String path = context.getFilesDir().getPath() + "/els.sqlite";
        Log.i("database", "path:" + path);
        File dbFile = new File(path);
        if (dbFile == null || !dbFile.exists()) {
            try {
                dbFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String filePath = dbFile.getAbsolutePath();
        MyDaoMaster.DevOpenHelper helper = null;
//        helper=new DaoMaster.DevOpenHelper(
//                context
//                , filePath
//                , null
//        );
        helper = new MyDaoMaster.Helper(context, filePath, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        MyDaoMaster daoMaster = new MyDaoMaster(db);
        mDaoSession = daoMaster.newSession();
        //sql日志
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }


}
