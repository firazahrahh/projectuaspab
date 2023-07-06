package com.example.projectuaspab.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectuaspab.R;

public class DetailActivity extends AppCompatActivity {
    private TextView tvId, tvLinkFoto, tvJudul, tvSutradara, tvDeskripsi, tvPemeran, tvJumlahEpisode;
    private String yId, yLinkFoto, yJudul, yDeskripsi, yPemeran, yJumlahEpisode, ySutradara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setTitle("DETAIL ITEM");

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yLinkFoto = ambil.getStringExtra("xLinkFoto");
        yJudul = ambil.getStringExtra("xJudul");
        ySutradara = ambil.getStringExtra("xSutradara");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");
        yPemeran = ambil.getStringExtra("xPemeran");
        yJumlahEpisode = ambil.getStringExtra("xJumlahEpisode");

        tvLinkFoto = findViewById(R.id.tv_link_foto);
        tvJudul = findViewById(R.id.tv_judul);
        tvSutradara = findViewById(R.id.tv_sutradara);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvPemeran = findViewById(R.id.tv_pemeran);
        tvJumlahEpisode = findViewById(R.id.tv_jumlah_episode);
    }
}
