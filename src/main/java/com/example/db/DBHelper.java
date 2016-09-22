package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.constast.DBConstast;
import com.example.constast.TABLES;

public class DBHelper extends SQLiteOpenHelper {


    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DBConstast.DATABASE_NAME, null, DBConstast.DATABASE_VERSION);
    }


    /**
     * 只会数据库文件创建时调用
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
       // String sql="CREATE TABLE "+ TABLES.USER.TABLE_NAME+"("+TABLES.USER.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TABLES.USER.NAME+" TEXT,"+TABLES.USER.AGE+")";
        String sql="CREATE TABLE "+ TABLES.USER.TABLE_NAME+"("+TABLES.USER.TITLE+" TEXT,"+TABLES.USER.CONTENT+" TEXT,"+TABLES.USER.DATE+" TEXT)";
        db.execSQL(sql);
        Log.i(TAG,"---数据库表创建成功");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
