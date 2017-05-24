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


public class AlgorithmRepo {
    private AlgorithmHelper algorithmHelper;

    public AlgorithmRepo(Context context){
        algorithmHelper =new AlgorithmHelper(context);
    }

    public int insert(Algorithm algorithm){
        //Open Db，write data
        SQLiteDatabase db= algorithmHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Algorithm.KEY_age, algorithm.age);
        values.put(Algorithm.KEY_content, algorithm.content);
        values.put(Algorithm.KEY_topic, algorithm.topic);
        values.put(Algorithm.KEY_code, algorithm.code);
        long algorithm_Id=db.insert(Algorithm.TABLE,null,values);
        db.close();
        return (int)algorithm_Id;
    }

    public void delete(int algorithm_Id){
        SQLiteDatabase db= algorithmHelper.getWritableDatabase();
        db.delete(Algorithm.TABLE, Algorithm.KEY_ID+"=?", new String[]{String.valueOf(algorithm_Id)});
        db.close();
    }
    public void update(Algorithm algorithm){
        SQLiteDatabase db= algorithmHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Algorithm.KEY_age, algorithm.age);
        values.put(Algorithm.KEY_content, algorithm.content);
        values.put(Algorithm.KEY_topic, algorithm.topic);
        values.put(Algorithm.KEY_code, algorithm.code);
        db.update(Algorithm.TABLE, values, Algorithm.KEY_ID + "=?", new String[]{String.valueOf(algorithm.algorithm_ID)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAlgorithmList(){
        SQLiteDatabase db= algorithmHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Algorithm.KEY_ID+","+
                Algorithm.KEY_content+","+
                Algorithm.KEY_topic+","+
                Algorithm.KEY_code+","+
                Algorithm.KEY_age+" FROM "+ Algorithm.TABLE;
        ArrayList<HashMap<String,String>> algorithmList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> algorithm=new HashMap<String,String>();
                algorithm.put("id",cursor.getString(cursor.getColumnIndex(Algorithm.KEY_ID)));
                algorithm.put("topic",cursor.getString(cursor.getColumnIndex(Algorithm.KEY_topic)));
                algorithm.put("content",cursor.getString(cursor.getColumnIndex(Algorithm.KEY_content)));
                algorithm.put("code",cursor.getString(cursor.getColumnIndex(Algorithm.KEY_code)));
                algorithmList.add(algorithm);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithmList;
    }

    public Algorithm getColumnById(int Id){
        SQLiteDatabase db= algorithmHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Algorithm.KEY_ID + "," +
                Algorithm.KEY_content + "," +
                Algorithm.KEY_topic + "," +
                Algorithm.KEY_code + "," +
                Algorithm.KEY_age +
                " FROM " + Algorithm.TABLE
                + " WHERE " +
                Algorithm.KEY_ID + "=?";
        int iCount=0;
        Algorithm algorithm =new Algorithm();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                algorithm.algorithm_ID =cursor.getInt(cursor.getColumnIndex(Algorithm.KEY_ID));
                algorithm.content =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_content));
                algorithm.topic  =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_topic));
                algorithm.code =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_code));
                algorithm.age =cursor.getInt(cursor.getColumnIndex(Algorithm.KEY_age));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithm;
    }

    public Algorithm getValueByKey(int Id){
        SQLiteDatabase db= algorithmHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Algorithm.KEY_ID + "," +
                Algorithm.KEY_content + "," +
                Algorithm.KEY_topic + "," +
                Algorithm.KEY_code + "," +
                Algorithm.KEY_age +
                " FROM " + Algorithm.TABLE
                + " WHERE " +
                Algorithm.KEY_ID + "=?";

        int iCount=0;

        Algorithm algorithm =new Algorithm();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                algorithm.algorithm_ID =cursor.getInt(cursor.getColumnIndex(Algorithm.KEY_ID));
                algorithm.content =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_content));
                algorithm.topic  =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_topic));
                algorithm.code  =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_code));
                algorithm.age =cursor.getInt(cursor.getColumnIndex(Algorithm.KEY_age));
                Log.d("MainActivity", "The " + " Topic is: " + cursor.getString(cursor.getColumnIndex(algorithm.topic)));
                Log.d("MainActivity", "The content is: " + cursor.getString(cursor.getColumnIndex(algorithm.content)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithm;
    }

    public Algorithm getColumnByTopic(String topic){
        SQLiteDatabase db= algorithmHelper.getReadableDatabase();
        String selectQuery="SELECT "+
                Algorithm.KEY_ID + "," +
                Algorithm.KEY_content + "," +
                Algorithm.KEY_topic + "," +
                Algorithm.KEY_code + "," +
                Algorithm.KEY_age +
                " FROM " + Algorithm.TABLE
                + " WHERE " +
                Algorithm.KEY_topic + "=?";
        int iCount=0;
        Algorithm algorithm =new Algorithm();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(topic)});
        if(cursor.moveToFirst()){
            do{
                algorithm.algorithm_ID =cursor.getInt(cursor.getColumnIndex(Algorithm.KEY_ID));
                algorithm.content =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_content));
                algorithm.topic  =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_topic));
                algorithm.code  =cursor.getString(cursor.getColumnIndex(Algorithm.KEY_code));
                algorithm.age =cursor.getInt(cursor.getColumnIndex(Algorithm.KEY_age));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return algorithm;
    }
}
