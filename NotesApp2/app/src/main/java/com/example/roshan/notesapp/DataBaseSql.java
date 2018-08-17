package com.example.roshan.notesapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseSql extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Notes.db";
    public static final String TABLE_NAME="note";
    public static final String FIELD_NAME1="title";
    public static final String FIELD_NAME2="content";
    public DataBaseSql(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" ("+FIELD_NAME1+" TEXT PRIMARY KEY,"+FIELD_NAME2+" TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String t,String c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIELD_NAME1,t);
        contentValues.put(FIELD_NAME2,c);
        long res=db.insert(TABLE_NAME,null,contentValues);
        if(res==-1)
        {
            return false;
        }
        return true;
    }
    public boolean updateData(String t,String c)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(FIELD_NAME1,t);
        contentValues.put(FIELD_NAME2,c);
        long res=db.update(TABLE_NAME,contentValues,FIELD_NAME1+"=?",new String[] {t});
        if(res==-1)
        {
            return false;
        }
        return true;
    }
    public Integer deleteData(String t)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return (db.delete(TABLE_NAME,FIELD_NAME1+"=?",new String[] {t}));

    }
    public Cursor getTitles()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select "+FIELD_NAME1+" from "+TABLE_NAME,null);
        return res;
    }
    public Cursor getData(String tit)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select "+FIELD_NAME2+" from "+TABLE_NAME+" where "+FIELD_NAME1+" = '" + tit + "'",null);
        return res;
    }
}
