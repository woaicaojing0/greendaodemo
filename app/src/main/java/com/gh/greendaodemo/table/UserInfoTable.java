package com.gh.greendaodemo.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Created by cj on 2020/3/25.
 * @description 说明 一个creationDate的属性，会变成数据库中的列的名字会是CREATION_DATE。
 * Email 1206067690@qq.com
 */
@Entity(nameInDb = "user_info")
public class UserInfoTable {
    @Id(autoincrement = true)
    private long id;

    /**
     * Unique 属性给数据库的列添加了一个唯一性限制。注意，SQLite也会隐式地为它创建一个索引。
     */
    @Unique
    @Property(nameInDb = "user_number")
    private String userNumber;
    private String userName;
    private String userAge;
    private String level;
    /**
     * 标记排除持久化的属性。把它用在维持短暂状态的数据。也就是不持久化到数据库
     */
    @Transient
    private List<TeacherInfoTable> teacherInfoTableList;
    @Generated(hash = 421225048)
    public UserInfoTable(long id, String userNumber, String userName,
            String userAge, String level) {
        this.id = id;
        this.userNumber = userNumber;
        this.userName = userName;
        this.userAge = userAge;
        this.level = level;
    }
    @Generated(hash = 1354492153)
    public UserInfoTable() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserNumber() {
        return this.userNumber;
    }
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserAge() {
        return this.userAge;
    }
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
    public String getLevel() {
        return this.level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
}
