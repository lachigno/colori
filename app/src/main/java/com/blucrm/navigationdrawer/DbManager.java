package com.blucrm.navigationdrawer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    //conteggio dei record
    public int count() {

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "SELECT * FROM colori";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    public List<colori> read() {

        List<colori> recordsList = new ArrayList<colori>();

        String sql = "SELECT * FROM colori ORDER BY id DESC";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                String albumId = cursor.getString(cursor.getColumnIndex("albumId"));
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String url = cursor.getString(cursor.getColumnIndex("url"));
                String thumbnailUrl = cursor.getString(cursor.getColumnIndex("thumbnailUrl"));


                //int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));


                colori colore = new colori();

                colore.setAlbumId(albumId);
                colore.setId(id);
                colore.setTitle(title);
                colore.setUrl(url);
                colore.setThumbnailUrl(thumbnailUrl);

                recordsList.add(colore);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    //STORAGE IMMAGINI DA INTERNET
    }
