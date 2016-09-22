package com.example.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.bean.Note;
import com.example.constast.TABLES;
import com.example.db.DBHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8879528950418307874L;

	private SQLiteDatabase db;

    private DBHelper helper;

    public NoteDAO(DBHelper helper) {
        this.helper = helper;

    }

    /**
     * 添加
     */
    public void add(Note note) {
        db = helper.getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(TABLES.USER.TITLE,note.getTitle());
        values.put(TABLES.USER.CONTENT,note.getContent());
        values.put(TABLES.USER.DATE, note.getDate());

        db.insert(TABLES.USER.TABLE_NAME,null,values);
        db.close();
    }


    /**
     * 删除
     */
    public void del(String date) {
        db = helper.getReadableDatabase();
        String whereClause=TABLES.USER.DATE+"=?";
        String[] whereArgs={date};
        //delete from table   where id=?
        /**
         * 参数1：表名
         * 参数2：删除条件
         * 参数3：参数条件的值
         */
        db.delete(TABLES.USER.TABLE_NAME,whereClause,whereArgs);
        db.close();
    }

    /**
     * 修改
     */
    public void update(Note note) {
        db = helper.getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put(TABLES.USER.TITLE,note.getTitle());
        values.put(TABLES.USER.CONTENT,note.getContent());

        String whereClause=TABLES.USER.DATE+"=?";
        String[] whereArgs={note.getDate()};
        /**
         * 参数1：表名
         * 参数2：要修改的值
         * 参数3：修改条件
         * 参数4：修改条件的值
         */
        db.update(TABLES.USER.TABLE_NAME,values,whereClause,whereArgs);

        db.close();
    }

    /**
     * 查询所有
     */
    public List<Note> queryAll() {
        List<Note> notes = new ArrayList<Note>();
        db = helper.getReadableDatabase();
        //SELECT ID,NAME,AGE FORM USER
        //String[] columns={TABLES.USER.NAME,TABLES.USER.AGE};
        Cursor cursor = db.query(TABLES.USER.TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext())
        {
            String title=cursor.getString(cursor.getColumnIndex(TABLES.USER.TITLE));
            String content=cursor.getString(cursor.getColumnIndex(TABLES.USER.CONTENT));
            String date=cursor.getString(cursor.getColumnIndex(TABLES.USER.DATE));
            Note note =new Note(title,content,date);
            //添加到集合
            notes.add(note);
        }
        db.close();
        return notes;
    }

    /**
     * 根据条件查询
     */
    public List<Note> query(String mTitle) {
        List<Note> notes = new ArrayList<Note>();
        db = helper.getReadableDatabase();
        //String[] columns={TABLES.USER.NAME,TABLES.USER.AGE};
        String selection=TABLES.USER.TITLE+" LIKE '%?%'";
        String[] selectionArgs={mTitle};
        Cursor cursor = db.query(TABLES.USER.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        while (cursor.moveToNext())
        {
            String title=cursor.getString(cursor.getColumnIndex(TABLES.USER.TITLE));
            String content=cursor.getString(cursor.getColumnIndex(TABLES.USER.CONTENT));
            String date=cursor.getString(cursor.getColumnIndex(TABLES.USER.DATE));
            Note note =new Note(title, content, date);
            //添加到集合
            notes.add(note);
        }
        db.close();

        return notes;
    }

    /**
     * 查询一个
     */
    public Note queryById(int mId) {
        db = helper.getReadableDatabase();
        Note note = null;
        String selection=TABLES.USER.ID+"= ?";
        String[] selectionArgs={String.valueOf(mId)};
        Cursor cursor = db.query(TABLES.USER.TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        if (cursor.moveToNext())
        {
        	int id=cursor.getInt(cursor.getColumnIndex(TABLES.USER.ID));
            String title=cursor.getString(cursor.getColumnIndex(TABLES.USER.TITLE));
            String content=cursor.getString(cursor.getColumnIndex(TABLES.USER.CONTENT));
            String date=cursor.getString(cursor.getColumnIndex(TABLES.USER.DATE));
            note =new Note(title, content, date);
        }
        db.close();
        return note;
    }
}
