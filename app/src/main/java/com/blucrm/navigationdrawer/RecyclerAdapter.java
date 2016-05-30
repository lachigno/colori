package com.blucrm.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.blucrm.navigationdrawer.colori;

import java.util.ArrayList;

/**
 * Created by a.lachi on 24/05/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<colori> arrayList;
    public RecyclerAdapter(ArrayList<colori> arrayList) {
        this.arrayList = arrayList;
    }

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

    }

    //contiamo il numero degli elementi children che dobbiamo visualizzare
    @Override
    public int getItemCount() {
        //30 a casao
        //return 30;
        return arrayList.size();
    }

    //Qui definisco i legami tra gli oggetti della scheda
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView info;
        TextView subinfo;
        public ViewHolder(View itemView) {
            super(itemView);
            info = (TextView)itemView.findViewById(R.id.info_text);
            subinfo = (TextView)itemView.findViewById(R.id.info_subtext);
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
}


