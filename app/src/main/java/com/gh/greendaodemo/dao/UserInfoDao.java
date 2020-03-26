package com.gh.greendaodemo.dao;

import com.gh.greendaodemo.gen.DaoSession;
import com.gh.greendaodemo.gen.UserInfoTableDao;
import com.gh.greendaodemo.table.UserInfoTable;

import java.util.List;

import javax.crypto.interfaces.PBEKey;

/**
 * @author Created by cj on 2020/3/26.
 * @description 学生信息实际操作类
 * Email 1206067690@qq.com
 */
public class UserInfoDao {
    private DaoSession mDaoSession;
    private UserInfoTableDao tableDao;

    UserInfoDao(DaoSession daoSession) {
        this.mDaoSession = daoSession;
        tableDao = daoSession.getUserInfoTableDao();
    }

    public long insertOne(UserInfoTable infoTable) {
        return tableDao.insert(infoTable);
    }

    public int batchInsert(List<UserInfoTable> infoTableList) {
        try {
            tableDao.insertInTx(infoTableList);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int updateOne(UserInfoTable infoTable) {

        try {
            tableDao.update(infoTable);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int updateUserAge(int age, long userId) {
        UserInfoTable infoTable = tableDao.queryBuilder().where(UserInfoTableDao.Properties.Id.eq(userId)).unique();
        infoTable.setUserAge(String.valueOf(age));
        return updateOne(infoTable);
    }

    public int batchUpdate(List<UserInfoTable> infoTableList) {
        try {
            tableDao.updateInTx(infoTableList);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public long deleteOne(UserInfoTable infoTable) {
        try {
            tableDao.delete(infoTable);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int batchDelete(List<UserInfoTable> infoTableList) {
        try {
            tableDao.deleteInTx(infoTableList);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
}
