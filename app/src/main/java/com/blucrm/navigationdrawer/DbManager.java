package com.blucrm.navigationdrawer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by a.lachi on 30/05/2016.
 * QUESTA CLASSE SERVE PER GESTIRE TUTTE LE CHIAMATE AL DB
 */
public class DbManager {

    private DbHelper helper = null;

    public DbManager(Context context)
    {
        //helper=new DbHelper(context,"/data/data/DBColori.db",null,1);  //Creo il DB"/mnt/sdcard/database_name.db"
        helper=new DbHelper(context);  //Creo il DB
    }

    public void save(String albumId, String id, String title, String url, String thumbnailUrl)
    {
        SQLiteDatabase db = this.helper.getWritableDatabase();

        ContentValues cv  =new ContentValues();
        cv.put("albumId", albumId);
        cv.put("id", id);
        cv.put("title", title);
        cv.put("url", url);
        cv.put("thumbnailUrl", thumbnailUrl);
        try
        {
            db.insert("colori", null,cv);
        }
        catch (SQLiteException sqle)
        {
            // Gestione delle eccezioni
        }
    }

    public boolean delete(long id)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        try
        {
            if (db.delete("colori", "_id=?", new String[]{Long.toString(id)})>0)
                return true;
            return false;
        }
        catch (SQLiteException sqle)
        {
            return false;
        }

    }

    public Cursor query()
    {
        Cursor crs=null;
        try
        {
            SQLiteDatabase db=helper.getReadableDatabase();
            crs=db.query("colori", null, null, null, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
            return null;
        }
        return crs;
    }

}
