package com.gh.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gh.greendaodemo.base.DbHelper;
import com.gh.greendaodemo.gen.DaoMaster;
import com.gh.greendaodemo.gen.DaoSession;
import com.gh.greendaodemo.gen.TeacherInfoTableDao;
import com.gh.greendaodemo.gen.TeacherUserMergeDao;
import com.gh.greendaodemo.table.TeacherInfoTable;
import com.gh.greendaodemo.table.TeacherUserMerge;
import com.gh.greendaodemo.table.UserInfoTable;
import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;
import org.w3c.dom.Text;

import java.util.List;

/**
 * @author cj
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSelect, btnAdd, btnUpdate, btnDelete;
    private TextView tvContent;
    private static final String TAG = "MainActivity";
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSelect = findViewById(R.id.btn_select);
        btnAdd = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        tvContent = findViewById(R.id.tv_content);
        btnSelect.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select:
                selectOne();
                break;
            case R.id.btn_update:
                break;
            case R.id.btn_delete:
                break;
            case R.id.btn_add:
                insertOne();
            default:

        }
    }

    private void insertOne() {
        UserInfoTable userInfoTable = new UserInfoTable();
        userInfoTable.setUserName("张三");
        userInfoTable.setUserAge("16");
        userInfoTable.setLevel("3");
        userInfoTable.setUserNumber("10086");
        long i = DbHelper.getInstance().getDaoSession().getUserInfoTableDao().insert(userInfoTable);
        UserInfoTable userInfoTable2 = new UserInfoTable();
        userInfoTable2.setUserName("李四");
        userInfoTable2.setUserAge("18");
        userInfoTable2.setLevel("4");
        userInfoTable2.setUserNumber("10087");
        long i2 = DbHelper.getInstance().getDaoSession().getUserInfoTableDao().insert(userInfoTable2);
        Log.v(TAG, "新增===========================>" + i + " 条");


        TeacherInfoTable teacherInfoTable = new TeacherInfoTable();
        teacherInfoTable.setTeacherName("张三（老师）");
        teacherInfoTable.setTeachType(TeacherInfoTable.CHINESE);
        DbHelper.getInstance().getDaoSession().getTeacherInfoTableDao().insert(teacherInfoTable);
        TeacherInfoTable teacherInfoTable2 = new TeacherInfoTable();
        teacherInfoTable2.setTeacherName("李四（老师）");
        teacherInfoTable2.setTeachType(TeacherInfoTable.ENGLISH);
        DbHelper.getInstance().getDaoSession().getTeacherInfoTableDao().insert(teacherInfoTable2);
        Log.v(TAG, "新增===========================>" + i2 + " 条");


        TeacherUserMerge userMerge = new TeacherUserMerge();
        userMerge.setUserId(userInfoTable.getId());
        userMerge.setTeacherId(teacherInfoTable.getId());
        DbHelper.getInstance().getDaoSession().getTeacherUserMergeDao().insert(userMerge);

        TeacherUserMerge userMerge2 = new TeacherUserMerge();
        userMerge.setUserId(userInfoTable.getId());
        userMerge.setTeacherId(teacherInfoTable2.getId());
        DbHelper.getInstance().getDaoSession().getTeacherUserMergeDao().insert(userMerge2);

        TeacherUserMerge userMerge3 = new TeacherUserMerge();
        userMerge3.setUserId(userInfoTable2.getId());
        userMerge3.setTeacherId(teacherInfoTable.getId());
        DbHelper.getInstance().getDaoSession().getTeacherUserMergeDao().insert(userMerge3);
    }

    private void selectAll() {
        List<UserInfoTable> userInfoTableList = DbHelper.getInstance().getDaoSession().getUserInfoTableDao().loadAll();
        tvContent.setText(gson.toJson(userInfoTableList));
    }

    private void selectOne() {
        QueryBuilder<TeacherInfoTable> queryBuilder = DbHelper.getInstance().getDaoSession().getTeacherInfoTableDao().queryBuilder();
//        queryBuilder.join(TeacherUserMerge.class, TeacherUserMergeDao.Properties.TeacherId).where(
//                        TeacherUserMergeDao.Properties.UserId.eq(2));
        queryBuilder.join(TeacherInfoTableDao.Properties.Id, TeacherUserMerge.class).where(
                TeacherUserMergeDao.Properties.UserId.eq(2));
        queryBuilder.build().list();


        QueryBuilder<TeacherInfoTable> queryBuilder2 = DbHelper.getInstance().getDaoSession().getTeacherInfoTableDao().queryBuilder();
//        queryBuilder.join(TeacherUserMerge.class, TeacherUserMergeDao.Properties.TeacherId).where(
//                        TeacherUserMergeDao.Properties.UserId.eq(2));
        queryBuilder2.join(TeacherInfoTableDao.Properties.Id, TeacherUserMerge.class, TeacherUserMergeDao.Properties.TeacherId).where(
                TeacherUserMergeDao.Properties.UserId.eq(2));
        queryBuilder2.build().list();
    }
}
