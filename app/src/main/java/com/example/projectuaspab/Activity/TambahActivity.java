package com.example.projectuaspab.Activity;

import androidx.appcompat.app.AppCompatActivity;

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

public class TambahActivity extends AppCompatActivity {

    private EditText etJudul, etDeskripsi, etPemeran, etJumlahEpisode, etSutradara;

    private Button btnSimpan;

    private String judul, deskripsi, pemeran, jumlah_episode, sutradara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etJudul = findViewById(R.id.et_judul);
        etSutradara = findViewById(R.id.et_sutradara);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etPemeran = findViewById(R.id.et_pemeran);
        etJumlahEpisode = findViewById(R.id.et_jumlah_episode);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judul = etJudul.getText().toString();
                sutradara = etSutradara.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                pemeran = etPemeran.getText().toString();
                jumlah_episode = etJumlahEpisode.getText().toString();

                if(judul.trim().isEmpty()){
                    etJudul.setError("Judul Tidak Boleh Kosong");
                }
                else if(sutradara.trim().isEmpty()){
                    etDeskripsi.setError("Sutradara Tidak Boleh Kosong");
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
                    tambahSH();
                }
            }
        });
    }

    private void tambahSH(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardCreate(judul, deskripsi, pemeran, jumlah_episode, sutradara);

        proses.enqueue(new Callback<ModelResponse>() {
            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(TambahActivity.this, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
