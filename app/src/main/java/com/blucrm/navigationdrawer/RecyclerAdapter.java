package com.blucrm.navigationdrawer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.blucrm.navigationdrawer.colori;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by a.lachi on 24/05/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<colori> arrayList;
    public RecyclerAdapter(Context context, ArrayList<colori> arrayList) {
        this.arrayList = arrayList;
        this.mContext = context;
    }


    private Context mContext;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //identifico la posizione del figlio inizioando sda 1
        //holder.info.setText(String.format("%s",position +1));

        // Associo alla vista i valori dell'array list di oggetti
        //holder.info.setText(arrayList.get(position));
        holder.info.setText(arrayList.get(position).getTitle());
        holder.subinfo.setText(arrayList.get(position).getUrl());

//        Picasso.Builder builder = new Picasso.Builder (mContext);
//        builder.listener(new Picasso.Listener()
//        {
//            @Override
//            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
//            {
//                exception.printStackTrace();
//            }
//        });
//        builder.build().load(arrayList.get(position).getThumbnailUrl()).into(holder.img);

        Picasso.with(mContext).load( arrayList.get(position).getThumbnailUrl())
                .error(R.drawable.no)
                .placeholder(R.drawable.foto)
                .into(holder.img);
//        new DownloadImageTask((ImageView) holder.img).execute("http://placehold.it/150/bc9589");

        //holder.url.setTag(arrayList.get(position).getUrl().toString());

    }

    //contiamo il numero degli elementi children che dobbiamo visualizzare
    @Override
    public int getItemCount() {
        //30 a casao
        return 10;
        //return arrayList.size();
    }

    //Qui definisco i legami tra gli oggetti della scheda
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView info;
        TextView subinfo;
        public ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            info = (TextView)itemView.findViewById(R.id.info_text);
            subinfo = (TextView)itemView.findViewById(R.id.info_subtext);
            img = (ImageView) itemView.findViewById(R.id.thumbnail);
            //new DownloadImageTask((ImageView) itemView.findViewById(R.id.thumbnail)).execute("http://placehold.it/600/35185e");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //v.getContext().startActivity(Children.class);
            Intent intent = new Intent(v.getContext(),Children.class);
            //intent.putExtra("titolo",arrayList.get(getAdapterPosition().getId()));
            v.getContext().startActivity(intent);

            //creazione di un Intento
//            Bundle b = new Bundle(); //creazione di un budle che conterrà i dati che voglio passare
//            b.putString("titolo", arrayList.get(getAdapterPosition()));  //chiave valore
//            intent.putExtras(b); //per mettere il bundle nell'intent

//            Snackbar.make(v,arrayList.get(getAdapterPosition()), Snackbar.LENGTH_SHORT)
//                    .setAction("Action", null).show();
        }
    }



    //CLASSE PER IL LOADING DELLE IMMAGINI

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }



}


