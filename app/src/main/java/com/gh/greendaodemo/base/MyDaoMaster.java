package com.gh.greendaodemo.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.gh.greendaodemo.gen.DaoMaster;
import com.gh.greendaodemo.gen.TeacherInfoTableDao;
import com.gh.greendaodemo.gen.TeacherUserMergeDao;
import com.gh.greendaodemo.gen.UserInfoTableDao;
import com.gh.greendaodemo.table.TeacherInfoTable;
import com.gh.greendaodemo.table.UserInfoTable;
import com.github.yuweiguocn.library.greendao.MigrationHelper;

import org.greenrobot.greendao.database.Database;

import static com.gh.greendaodemo.gen.DaoMaster.SCHEMA_VERSION;
import static com.gh.greendaodemo.gen.DaoMaster.createAllTables;


/**
 * @author cj
 * @date 2020/04/09
 */

public class MyDaoMaster extends DaoMaster.DevOpenHelper {


    public MyDaoMaster(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
        //我就把这里的false修改成了true
        //这样 有就不创建表，没有就创建表
        //原来的OpenHelper的flase太不灵活了
        createAllTables(db, true);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("MyDaoMaster", "oldVersion:" + oldVersion + "-newVersion:" + newVersion);
         /*此处不用super，因为父类中包含了
       dropAllTables(db, true);
        onCreate(db);
        需要自己定制升级
        */
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserInfoTableDao.class, TeacherUserMergeDao.class, TeacherInfoTableDao.class);
    }
}
