package com.gh.greendaodemo.base;

import android.content.Context;

import com.gh.greendaodemo.gen.DaoMaster;
import com.gh.greendaodemo.gen.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;


/**
 * @author cj
 * 使用说明：先在App中init（）。然后在需要使用的地方 DB
 */
public class DbHelper {

    private static final String DEFAULT_DATABASE_NAME = "Database.db";

    private static DbHelper instance;

    private DaoSession mDaoSession;

    public static DbHelper getInstance() {
        if (null == instance) {
            throw new NullPointerException("DbHelper未初始化");
        }
        return instance;
    }


    /**
     * 初始化，默认的数据名
     */
    public static void init(Context context) {
        if (null == instance) {
            instance = new DbHelper(context, DEFAULT_DATABASE_NAME);
        }
    }

    /**
     * 初始化，指定的数据库名
     */
    public static void init(Context context, String dbName) {
        if (null == instance) {
            instance = new DbHelper(context, dbName);
        }
    }
    /**
     * 是否加密
     */
    public static boolean ENCRYPTED = true;
    private static final String PASSWORD = "123456";
    /**
     * 初始化，指定的数据库名是否需要加密
     */
    public static void init(Context context, String dbName, boolean needEncrypted) {
        if (null == instance) {
            ENCRYPTED = needEncrypted;
            instance = new DbHelper(context, dbName);
        }
    }

    private DbHelper(Context context, String dbName) {

        MyDaoMaster helper = new MyDaoMaster(context, dbName, null);
        Database db;
        if (ENCRYPTED) {
            db = helper.getEncryptedWritableDb(PASSWORD);
        } else {
            db = helper.getWritableDb();
        }
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        //sql日志
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }


    /**
     * 设置greenDao
     */
    public static void clear() {
        instance = null;
    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }


}
