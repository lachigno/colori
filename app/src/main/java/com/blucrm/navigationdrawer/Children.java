package com.blucrm.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Children extends AppCompatActivity {

    String titolo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //prendo i dati che mi vengono passati dal chiamante
        Intent i = getIntent();
        titolo = i.getStringExtra("titolo");

        //Setto il titolo della AppBar
        Toolbar  mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle(titolo);

        //Setto il background della Layout Bar
        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        mCollapsingToolbarLayout.setBackground(getDrawable(R.drawable.foto));
        //getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.foto));

//        ImageView fotoSfondo = new ImageView(this);
//        fotoSfondo.setBackgroundResource(R.drawable.foto);
//        getSupportActionBar().setBackgroundDrawable(fotoSfondo.getDrawable());

//        TextView text = new TextView(this);
//        text.setText(titolo);
//
//        text.setTextAppearance(this, android.R.style.TextAppearance_Material_Widget_ActionBar_Title_Inverse);
//        toolbar.addView(text);

//        info = (TextView) findViewById(R.id.info_text);
//        info.setText(titolo);

        Toast.makeText(getApplicationContext(), //contesto è il rifiremto alla app stessa
                titolo, //messaggio
                Toast.LENGTH_SHORT).show();// Metodo  per la visualizzazione a schermo


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        ImageView news = (ImageView) findViewById(R.id.image_Foto_little);
        if (news != null) {
            news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), //contesto è il rifiremto alla app stessa
//                            "AZZO!!!!!", //messaggio
//                            Toast.LENGTH_LONG).show();// Metodo  per la visualizzazione a schermo
                    Intent intent = new Intent(getApplicationContext(), FullscreenImage.class);
                    startActivity(intent);
                }
            });
        }
    }
}
