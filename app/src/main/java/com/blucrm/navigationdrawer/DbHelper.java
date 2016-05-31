package com.blucrm.navigationdrawer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a.lachi on 30/05/2016.
 * HELPER per IL nostro DB
 */
public class DbHelper  extends SQLiteOpenHelper{

    public static final String DBNAME="BDColori";

    //Costruttore in cui invochiamo il costruttore della classe base
    public DbHelper(Context context) {
        super(context, DBNAME, null, 1);
    }


    //Per la Creazione del DB
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="CREATE TABLE colori " +
                "(" +
               // "_id integer primary key autoincrement," +
                "albumId TEXT  not null,"+
                "id text TEXT null," +
                "title TEXT not null," +
                "url text TEXT null," +
                "thumbnailUrl TEXT not null" +
                ")";
        db.execSQL(query);
        //inizializza(db);
    }

    //Per l'aggiornamento del db (per la gestione delle versioni del DB)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



//    private void inizializza(SQLiteDatabase db)
//    {
//        //inserimento dei dati in una tabella
//        ContentValues values=new ContentValues();
//        values.put("albumId","1");
//        values.put("id","11");
//        values.put("title","nihil at amet non hic quia qui");
//        values.put("url","http://placehold.it/600/1ee8a4");
//        values.put("thumbnailUrl","nihil at amet non hic quia qui");
//
//        db.insert("colori",null,values); //nome tabella, values (oggetto ContentValues = mappa in java)
//
//
//        values=new ContentValues();
//        values.put("albumId","1");
//        values.put("id","12");
//        values.put("title","mollitia soluta ut rerum eos aliquam consequatur perspiciatis maiores");
//        values.put("url","http://placehold.it/600/66b7d2");
//        values.put("thumbnailUrl","http://placehold.it/150/bc9589");
//
//        db.insert("providers",null,values);
//    }
}




/*

{
    "albumId": 1,
    "id": 11,
    "title": "nihil at amet non hic quia qui",
    "url": "http://placehold.it/600/1ee8a4",
    "thumbnailUrl": "http://placehold.it/150/e65eee"
  },
  {
    "albumId": 1,
    "id": 12,
    "title": "mollitia soluta ut rerum eos aliquam consequatur perspiciatis maiores",
    "url": "http://placehold.it/600/66b7d2",
    "thumbnailUrl": "http://placehold.it/150/bc9589"
  },
 */

/*
 public void save(String sub, String txt, String date)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put(DatabaseStrings.FIELD_SUBJECT, sub);
        cv.put(DatabaseStrings.FIELD_TEXT, txt);
        cv.put(DatabaseStrings.FIELD_DATE, date);
        try
        {
            db.insert(DatabaseStrings.TBL_NAME, null,cv);
        }
        catch (SQLiteException sqle)
        {
            // Gestione delle eccezioni
        }
    }

    public boolean delete(long id)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        try
        {
            if (db.delete(DatabaseStrings.TBL_NAME, DatabaseStrings.FIELD_ID+"=?", new String[]{Long.toString(id)})>0)
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
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DatabaseStrings.TBL_NAME, null, null, null, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
            return null;
        }
        return crs;
    }
 */