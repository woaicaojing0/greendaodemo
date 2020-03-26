package com.gh.greendaodemo.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Created by cj on 2020/3/26.
 * @description 教师和学生的关系表
 * Email 1206067690@qq.com
 */
@Entity(nameInDb = "teacher_user_merge")
public class TeacherUserMerge {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "user_id")
    private long userId;
    @Property(nameInDb = "teacher_id")
    private long teacherId;
    @Generated(hash = 1075475063)
    public TeacherUserMerge(Long id, long userId, long teacherId) {
        this.id = id;
        this.userId = userId;
        this.teacherId = teacherId;
    }
    @Generated(hash = 163563892)
    public TeacherUserMerge() {
    }
  
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public long getTeacherId() {
        return this.teacherId;
    }
    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }
    
}
