package com.gh.greendaodemo.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.gh.greendaodemo.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;


/**
 * @author yfjin
 * @date 2018/10/12
 */

public class MyDaoMaster extends DaoMaster {

    public MyDaoMaster(SQLiteDatabase db) {
        super(db);
    }

    public static class Helper extends DaoMaster.DevOpenHelper {

        public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            //我就把这里的false修改成了true
            //这样有就不创建表，没有就创建表
            //原来的OpenHelper的flase太不灵活了
            createAllTables(db, true);
        }


        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("MyDaoMaster", "oldVersion:" + oldVersion + "-newVersion:" + newVersion);
            super.onUpgrade(db, oldVersion, newVersion);
            //如果要增量更新数据库可以如下操作
//            if (oldVersion <= 4 && oldVersion < newVersion ) {
//                StaffRecordTableDao.createTable(db, true);
//                LogUtil.i("MyDaoMaster", "进入升级");
//            }
        }
    }

    /**
     *  dbVersion<5
     *  StaffTable,ManagerTable,DebugTable
     *
     *  dbVersion=5
     *  添加StaffRecordTableDao，其他不变
     */
}
