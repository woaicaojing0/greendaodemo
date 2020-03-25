package com.gh.greendaodemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.gh.greendaodemo.table.TeacherInfoTable;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "teacher_info".
*/
public class TeacherInfoTableDao extends AbstractDao<TeacherInfoTable, Void> {

    public static final String TABLENAME = "teacher_info";

    /**
     * Properties of entity TeacherInfoTable.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", false, "ID");
        public final static Property TeacherName = new Property(1, String.class, "teacherName", false, "TEACHER_NAME");
        public final static Property TeachType = new Property(2, int.class, "teachType", false, "teach_type");
    }


    public TeacherInfoTableDao(DaoConfig config) {
        super(config);
    }
    
    public TeacherInfoTableDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"teacher_info\" (" + //
                "\"ID\" INTEGER NOT NULL ," + // 0: id
                "\"TEACHER_NAME\" TEXT," + // 1: teacherName
                "\"teach_type\" INTEGER NOT NULL );"); // 2: teachType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"teacher_info\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TeacherInfoTable entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String teacherName = entity.getTeacherName();
        if (teacherName != null) {
            stmt.bindString(2, teacherName);
        }
        stmt.bindLong(3, entity.getTeachType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TeacherInfoTable entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String teacherName = entity.getTeacherName();
        if (teacherName != null) {
            stmt.bindString(2, teacherName);
        }
        stmt.bindLong(3, entity.getTeachType());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public TeacherInfoTable readEntity(Cursor cursor, int offset) {
        TeacherInfoTable entity = new TeacherInfoTable( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // teacherName
            cursor.getInt(offset + 2) // teachType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TeacherInfoTable entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setTeacherName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTeachType(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(TeacherInfoTable entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(TeacherInfoTable entity) {
        return null;
    }

    @Override
    public boolean hasKey(TeacherInfoTable entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
