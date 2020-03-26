package com.gh.greendaodemo.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Type;

import androidx.annotation.IntDef;

import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Created by cj on 2020/3/25.
 * @description 说明
 * Email 1206067690@qq.com
 */
@Entity(nameInDb = "teacher_info")
public class TeacherInfoTable {
    public final static int CHINESE = 0;
    public final static int MATH = 1;
    public final static int ENGLISH = 2;
    @Id(autoincrement = true)
    private Long id;
    private String teacherName;

    /**
     * 语文、英语、数学
     * 这里使用IntDef（Android不推荐使用枚举）
     */
    @IntDef({CHINESE, MATH, ENGLISH}) //限定为DB_FROM_SERVER,DB_FROM_LOCAL
    @Retention(RetentionPolicy.SOURCE)
    public @interface TEACH_TYPE {
    }

    /**
     * 老师的教学类型 ： 语文、英语、数学
     */
    @TEACH_TYPE
    @Property(nameInDb = "teach_type")
    private int teachType;

    @Generated(hash = 1995732777)
    public TeacherInfoTable(Long id, String teacherName, int teachType) {
        this.id = id;
        this.teacherName = teacherName;
        this.teachType = teachType;
    }

    @Generated(hash = 1996426326)
    public TeacherInfoTable() {
    }

    public Long getId() {
        return id;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getTeachType() {
        return this.teachType;
    }

    public void setTeachType(int teachType) {
        this.teachType = teachType;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
