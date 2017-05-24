package com.fox_lee.yunwen.lolinfimobile_struct.Utility;

/**
 * Created by Yunwen on 2/15/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbRepo {
    private DBHelper dbHelper;

    public DbRepo(Context context){
        dbHelper=new DBHelper(context);
    }

    public int insert(DbFavorite dbFavorite){
        //Open Db，write data
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DbFavorite.KEY_age, dbFavorite.age);
        values.put(DbFavorite.KEY_content, dbFavorite.content);
        values.put(DbFavorite.KEY_topic, dbFavorite.topic);
        //
        long algorithm_Id=db.insert(DbFavorite.TABLE,null,values);
        db.close();
        return (int)algorithm_Id;
    }

    public void delete(int algorithm_Id){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(DbFavorite.TABLE, DbFavorite.KEY_ID+"=?", new String[]{String.valueOf(algorithm_Id)});
        db.close();
    }
    public void update(DbFavorite dbFavorite){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(DbFavorite.KEY_age, dbFavorite.age);
        values.put(DbFavorite.KEY_content, dbFavorite.content);
        values.put(DbFavorite.KEY_topic, dbFavorite.topic);
        db.update(DbFavorite.TABLE, values, DbFavorite.KEY_ID + "=?", new String[]{String.valueOf(dbFavorite.algorithm_ID)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAlgorithmList(){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                DbFavorite.KEY_ID+","+
                DbFavorite.KEY_content+","+
                DbFavorite.KEY_topic+","+
                DbFavorite.KEY_age+" FROM "+ DbFavorite.TABLE;
        ArrayList<HashMap<String,String>> algorithmList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> algorithm=new HashMap<String,String>();
                algorithm.put("id",cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_ID)));
                algorithm.put("topic",cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_topic)));
                algorithm.put("content",cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_content)));
                algorithmList.add(algorithm);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithmList;
    }

    public DbFavorite getColumnById(int Id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                DbFavorite.KEY_ID + "," +
                DbFavorite.KEY_content + "," +
                DbFavorite.KEY_topic + "," +
                DbFavorite.KEY_age +
                " FROM " + DbFavorite.TABLE
                + " WHERE " +
                DbFavorite.KEY_ID + "=?";
        int iCount=0;
        DbFavorite dbFavorite =new DbFavorite();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                dbFavorite.algorithm_ID =cursor.getInt(cursor.getColumnIndex(DbFavorite.KEY_ID));
                dbFavorite.content =cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_content));
                dbFavorite.topic  =cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_topic));
                dbFavorite.age =cursor.getInt(cursor.getColumnIndex(DbFavorite.KEY_age));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dbFavorite;
    }

    public DbFavorite getValueByKey(int Id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                DbFavorite.KEY_ID + "," +
                DbFavorite.KEY_content + "," +
                DbFavorite.KEY_topic + "," +
                DbFavorite.KEY_age +
                " FROM " + DbFavorite.TABLE
                + " WHERE " +
                DbFavorite.KEY_ID + "=?";

        int iCount=0;

        DbFavorite dbFavorite =new DbFavorite();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                dbFavorite.algorithm_ID =cursor.getInt(cursor.getColumnIndex(DbFavorite.KEY_ID));
                dbFavorite.content =cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_content));
                dbFavorite.topic  =cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_topic));
                dbFavorite.age =cursor.getInt(cursor.getColumnIndex(DbFavorite.KEY_age));
                Log.d("MainActivity", "The " + " Topic is: " + cursor.getString(cursor.getColumnIndex(dbFavorite.topic)));
                Log.d("MainActivity", "The content is: " + cursor.getString(cursor.getColumnIndex(dbFavorite.content)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dbFavorite;
    }

    public DbFavorite getColumnByTopic(String topic){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                DbFavorite.KEY_ID + "," +
                DbFavorite.KEY_content + "," +
                DbFavorite.KEY_topic + "," +
                DbFavorite.KEY_age +
                " FROM " + DbFavorite.TABLE
                + " WHERE " +
                DbFavorite.KEY_topic + "=?";
        int iCount=0;
        DbFavorite dbFavorite =new DbFavorite();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(topic)});
        if(cursor.moveToFirst()){
            do{
                dbFavorite.algorithm_ID =cursor.getInt(cursor.getColumnIndex(DbFavorite.KEY_ID));
                dbFavorite.content =cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_content));
                dbFavorite.topic  =cursor.getString(cursor.getColumnIndex(DbFavorite.KEY_topic));
                dbFavorite.age =cursor.getInt(cursor.getColumnIndex(DbFavorite.KEY_age));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dbFavorite;
    }
}
