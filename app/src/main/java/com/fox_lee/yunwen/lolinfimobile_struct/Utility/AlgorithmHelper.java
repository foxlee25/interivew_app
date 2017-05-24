package com.fox_lee.yunwen.lolinfimobile_struct.Utility;

/**
 * Created by Yunwen on 2/15/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ifanr on 2015/3/29.
 */
public class AlgorithmHelper extends SQLiteOpenHelper {
    //Db version
    private static final int DATABASE_VERSION = 6;
    //name of the db
    private static final String DATABASE_NAME = "algorithm.db";

    public AlgorithmHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ Algorithm.TABLE+"("
                + Algorithm.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Algorithm.KEY_topic+" TEXT, "
                + Algorithm.KEY_age+" INTEGER, "
                + Algorithm.KEY_content+" TEXT, "
                + Algorithm.KEY_code+" CODE)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if the table exist, delete it
        db.execSQL("DROP TABLE IF EXISTS "+ Algorithm.TABLE);
        //recreate the table
        onCreate(db);
    }
}
