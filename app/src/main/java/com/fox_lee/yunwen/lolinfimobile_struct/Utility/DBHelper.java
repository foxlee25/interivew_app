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
public class DBHelper extends SQLiteOpenHelper {
    //Db version
    private static final int DATABASE_VERSION = 4;
    //name of the db
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        String CREATE_TABLE_STUDENT="CREATE TABLE "+ DbFavorite.TABLE+"("
                + DbFavorite.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + DbFavorite.KEY_topic+" TEXT, "
                + DbFavorite.KEY_age+" INTEGER, "
                + DbFavorite.KEY_content+" TEXT)";
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //if the table exist, delete it
        db.execSQL("DROP TABLE IF EXISTS "+ DbFavorite.TABLE);
        //recreate the table
        onCreate(db);
    }
}
