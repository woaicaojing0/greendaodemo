package com.gh.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gh.greendaodemo.gen.DaoMaster;
import com.gh.greendaodemo.gen.DaoSession;
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
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
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
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test.db", null);
        db = helper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        //sql日志
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_select:
                selectAll();
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
        long i = mDaoSession.getUserInfoTableDao().insert(userInfoTable);
        Log.v(TAG, "新增===========================>" + i + " 条");
    }

    private void selectAll() {
        List<UserInfoTable> userInfoTableList = mDaoSession.getUserInfoTableDao().loadAll();
        tvContent.setText(gson.toJson(userInfoTableList));
    }
}
