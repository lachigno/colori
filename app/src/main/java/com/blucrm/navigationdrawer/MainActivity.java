package com.blucrm.navigationdrawer;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.blucrm.navigationdrawer.colori;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import com.blucrm.navigationdrawer.DbManager;
import com.blucrm.navigationdrawer.DbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.blucrm.navigationdrawer.colori.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Definisco una ProgressBar per il caricamneto dei dati Json
    ProgressDialog progress=null;
    //Inserirsco l'URL di scaricamento
    private static final String ADDRESS = "http://jsonplaceholder.typicode.com/photos";

    private DbManager db = null;
    private CursorAdapter adapter;

    RecyclerView reciclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Sostituisce l'azione della Toolbar con la Nostra Toolbar
        //android.support.v7.widget.Toolbar dentro "app_bar_main.xml"
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Configuro la spinner per il caricamento
        progress=new ProgressDialog(this);
        progress.setMax(100);
        progress.setMessage(getString(R.string.progress_msg));
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //Lancio il TASK in Background ....solo per test, da associare ad un pulsante
//        new BackgroundTask().execute();

        //Configuro il Floating Button
        //android.support.design.widget.FloatingActionButton dentro "app_bar_main.xml"
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Configuro il Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //Configuro la NavigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //----------------------------------------------------------------------
        //Configuro il RecyclerView
        //----------------------------------------------------------------------
        //GLi associo la vista
        reciclerView = (RecyclerView) findViewById(R.id.recycler);
        //impongo la dimensione fissa
        reciclerView.setHasFixedSize(true);

        //Controllo se siamo Portait o Landscape per definire il numero di colonne da visualizzare
        int numberOfColumns = 2;
//        //Check your orientation either in your OnCreate or after it
//        if(this.getResources().getConfiguration().orientation ==
//                this.getResources().getConfiguration()
//                        .ORIENTATION_LANDSCAPE)
//            numberOfColumns = 3;

        //definisco un layout manager di tipo Griglia
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,numberOfColumns);
        reciclerView.setLayoutManager(gridLayoutManager);

        //ArrayList di Test
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Ciao");
        arrayList.add("ragazzi");
        arrayList.add("siete ");
        arrayList.add("pronti");
        arrayList.add("per");
        arrayList.add("uno");
        arrayList.add("scherzone?");
        arrayList.add("Allora:");
        arrayList.add("SCEMO");
        arrayList.add("CHI");
        arrayList.add("LEGGE");



        //definisco questa classe come layout manager
        //reciclerView.setLayoutManager(new LinearLayoutManager(this));

        //definisco il suo adapter
//        reciclerView.setAdapter(new RecyclerAdapter(arrayList));

        //-----------------------------------------------------------------------------------------------------------------------------
        //CREO IL MIO DB
        //DbManager  DatabaseDeiColori= new DbManager(this);
        db = new DbManager (getApplicationContext());
        Cursor crs=db.query();



        //reciclerView.setAdapter(new RecyclerAdapter(MainActivity.this, res));

        //-----------------------------------------------------------------------------------------------------------------------------
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new BackgroundTask().execute();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //CLASSE INTERNA PER LA DEFINIZIONE DI UN TASK ASINCRONO
    //per scaricamento del JSON con i dati

    private class BackgroundTask extends AsyncTask<Void,Integer,ArrayList<colori>> {

        //Prima dell'esecuzione
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress.setProgress(0);
            progress.show();
        }
        //Lavoro in Background
        @Override
        protected ArrayList<colori> doInBackground(Void... params)
        {
            URL url=null;

            try {
                url=new URL(ADDRESS);
            } catch (MalformedURLException e)
            { return null;}

            StringBuffer buffer=null;
            ArrayList<colori> coloriModelList = new ArrayList<>();

            try {

                //parte il collegamento
                BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
                String tmp=null;
                //creo un buffer e con un ciclo leggo tutto
                buffer=new StringBuffer();
                while((tmp=reader.readLine())!=null)
                {
                    buffer.append(tmp);
                }

                //passo ad una stringa
                String finalJson = buffer.toString();
                //passo la string ad un Json Array
                JSONArray array = new JSONArray(finalJson);
//                JSONObject parentObject = new JSONObject(finalJson);
//                JSONArray parentArray = parentObject.getJSONArray("colori");

                //ciclo e metto gli oggetti colore in un ArrayList di Oggetti colore
                for(int i=0;i<array.length();i++)
                {

                    //db.save(array.getJSONObject(i).getString("albumId"),array.getJSONObject(i).getString("id"),array.getJSONObject(i).getString("title"),array.getJSONObject(i).getString("url"),array.getJSONObject(i).getString("thumbnailUrl"));
                    colori colore = new colori();
                    colore.setAlbumId(array.getJSONObject(i).getString("albumId"));
                    colore.setId(array.getJSONObject(i).getString("id"));
                    colore.setTitle(array.getJSONObject(i).getString("title"));
                    colore.setUrl(array.getJSONObject(i).getString("url"));
                    colore.setThumbnailUrl(array.getJSONObject(i).getString("thumbnailUrl"));

                    coloriModelList.add(colore);
                }
                //return coloriModelList;

            } catch (IOException e)
            { return null;} catch (JSONException e) {
                e.printStackTrace();
            }
            return coloriModelList;
        }

        //Durante il Lavoro
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
        }

        //Al termine del Lavoro
        @Override
        protected void onPostExecute(ArrayList<colori> res)
        {
            super.onPostExecute(res);
            progress.dismiss();
            if (res!=null)
            {
                //collego i dati al suo ADAPTER
                //reciclerView.setAdapter(new RecyclerAdapter(MainActivity.this, res));
//                try {
//
//                    JSONArray array = new JSONArray(res);
//                    String[] colori=new String[array.length()];
//                    for(int i=0;i<array.length();i++)
//                    {
//                        String albumId=array.getJSONObject(i).getString("albumId");
//                        String id=array.getJSONObject(i).getString("id");
//                        String title=array.getJSONObject(i).getString("title");
//                        String url=array.getJSONObject(i).getString("url");
//                        String thumbnailUrl=array.getJSONObject(i).getString("thumbnailUrl");
//                        colori[i]=albumId+" "+id+" "+title +" "+url +" "+thumbnailUrl;
//                    }
//                }
//                catch (JSONException e)
//                { }
//                Toast.makeText(getApplicationContext(), //contesto è il rifiremto alla app stessa
//                        "FINITO!!!?!?!", //messaggio
//                        Toast.LENGTH_LONG).show();// Metodo  per la visualizzazione a schermo
            }
        }
    }
    }

