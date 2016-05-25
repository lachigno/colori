package com.blucrm.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a.lachi on 24/05/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<String> arrayList;
    public RecyclerAdapter(ArrayList<String> arrayList) {
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
        holder.info.setText(arrayList.get(position));

    }

    //contiamo il numero degli elementi children che dobbiamo visualizzare
    @Override
    public int getItemCount() {
        //30 a casao
        //return 30;
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView info;
        public ViewHolder(View itemView) {
            super(itemView);
            info = (TextView)itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //v.getContext().startActivity(Children.class);
            Intent intent = new Intent(v.getContext(),Children.class);
            intent.putExtra("titolo",arrayList.get(getAdapterPosition()));
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


