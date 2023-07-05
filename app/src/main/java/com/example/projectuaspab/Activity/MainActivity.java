package com.example.projectuaspab.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectuaspab.API.APIRequestData;
import com.example.projectuaspab.API.RetroServer;
import com.example.projectuaspab.Adapter.AdapterSH;
import com.example.projectuaspab.Model.ModelSH;
import com.example.projectuaspab.Model.ModelResponse;
import com.example.projectuaspab.R;
import com.example.projectuaspab.Utility.KendaliLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvSH;

    private FloatingActionButton fabTambah, fabLogout;

    private TextView tvWelcome;

    private ProgressBar pbSH;

    private RecyclerView.Adapter adSH;

    private RecyclerView.LayoutManager lmSH;

    private List<ModelSH> listSH = new ArrayList<>();

    KendaliLogin KL = new KendaliLogin(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(KL.isLogin(KL.keySP_username) ==true){
            setContentView(R.layout.activity_main);

            rvSH        = findViewById(R.id.rv_sh);
            fabTambah   = findViewById(R.id.fab_tambah);
            fabLogout   = findViewById(R.id.fab_logout);
            pbSH        = findViewById(R.id.pb_sh);
            tvWelcome   = findViewById(R.id.tv_welcome);

            lmSH    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvSH.setLayoutManager(lmSH);

            tvWelcome.setText("Selamat Datang" + KL.getPref(KL.keySP_nama_lengkap));

            fabTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, TambahActivity.class));
                }
            });

            fabLogout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v){
                    KL.setPref(KL.keySP_username, null);
                    KL.setPref(KL.keySP_nama_lengkap, null);
                    KL.setPref(KL.keySP_email, null);
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            });
        }
        else{
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        retrieveSH();
    }

    public void retrieveSH(){
        pbSH.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ModelResponse> proses = ARD.ardRetrieve();

        proses.enqueue(new Callback<ModelResponse>() {

            @Override
            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response){
                String kode = response.body().getKode();
                String pesan = response.body().getPesan();
                listSH = response.body().getData();

                adSH = new AdapterSH(MainActivity.this, listSH);
                rvSH.setAdapter(adSH);
                adSH.notifyDataSetChanged();

                pbSH.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ModelResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbSH.setVisibility(View.GONE);
            }
        });
    }
}
