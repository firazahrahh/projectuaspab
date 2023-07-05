package com.example.projectuaspab.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectuaspab.API.APIRequestData;
import com.example.projectuaspab.API.RetroServer;
import com.example.projectuaspab.Model.ModelResponse;
import com.example.projectuaspab.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class UbahActivity extends AppCompatActivity {

    private String yId, yJudul, yDeskripsi, yPemeran, yJumlahEpisode, ySutradara;
    private EditText etJudul, etDeskripsi, etPemeran, etJumlahEpisode, etSutradara;
    private Button btnUbah;
    private String judul, deskripsi, pemeran, jumlah_episode, sutradara;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        Intent ambil = getIntent();
        yId = ambil.getStringExtra("xId");
        yJudul = ambil.getStringExtra("xJudul");
        ySutradara = ambil.getStringExtra("xSutradara");
        yDeskripsi = ambil.getStringExtra("xDeskripsi");
        yPemeran = ambil.getStringExtra("xPemeran");
        yJumlahEpisode = ambil.getStringExtra("xJumlahEpisode");

        etJudul = findViewById(R.id.et_judul);
        etSutradara = findViewById(R.id.et_sutradara);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etPemeran = findViewById(R.id.et_pemeran);
        etJumlahEpisode = findViewById(R.id.et_jumlah_episode);
        btnUbah = findViewById(R.id.btn_ubah);

        etJudul.setText(yJudul);
        etSutradara.setText(ySutradara);
        etDeskripsi.setText(yDeskripsi);
        etPemeran.setText(yPemeran);
        etJumlahEpisode.setText(yJumlahEpisode);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judul = etJudul.getText().toString();
                judul = etSutradara.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                pemeran = etPemeran.getText().toString();
                jumlah_episode = etJumlahEpisode.getText().toString();

                if(judul.trim().isEmpty()){
                    etJudul.setError("Judul Tidak Boleh Kosong");
                }
                else if(sutradara.trim().isEmpty()){
                    etSutradara.setError("Ulasan Tidak Boleh Kosong");
                }
                else if(deskripsi.trim().isEmpty()){
                    etDeskripsi.setError("Deskripsi Tidak Boleh Kosong");
                }
                else if(pemeran.trim().isEmpty()){
                    etDeskripsi.setError("Pemeran Tidak Boleh Kosong");
                }
                else if(jumlah_episode.trim().isEmpty()){
                    etDeskripsi.setError("jumlah_episode Tidak Boleh Kosong");
                }
                else{
                    ubahSH();
                }
            }
        });
    }

    private void ubahSH(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardUpdate(yId, judul, deskripsi, pemeran, jumlah_episode, sutradara);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UbahActivity.this, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(UbahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
