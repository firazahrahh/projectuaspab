package com.example.projectuaspab.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectuaspab.API.APIRequestData;
import com.example.projectuaspab.API.RetroServer;
import com.example.projectuaspab.Activity.MainActivity;
import com.example.projectuaspab.Activity.UbahActivity;
import com.example.projectuaspab.Model.ModelResponse;
import com.example.projectuaspab.Model.ModelSH;
import com.example.projectuaspab.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterSH extends RecyclerView.Adapter<AdapterSH.VHSH> {
    private Context ctx;
    private List<ModelSH> listSH;

    public AdapterSH(Context ctx, List<ModelSH> listSH){
        this.ctx = ctx;
        this.listSH = listSH;
    }

    @NonNull
    @Override
    public VHSH onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_sh, parent, false);
        return new VHSH(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHSH holder, int postion){
        ModelSH SH = listSH.get(postion);
        holder.tvId.setText(SH.getId());
        holder.tvJudul.setText(SH.getJudul());
        holder.tvDeskripsi.setText(SH.getDeskripsi());
        holder.tvPemeran.setText(SH.getPemeran());
        holder.tvJumlahEpisode.setText(SH.getPemeran());
        holder.tvUlasan.setText(SH.getPemeran());
    }

    @Override
    public int getItemCount(){ return listSH.size(); }

    public class VHSH extends RecyclerView.ViewHolder{
        TextView tvId, tvJudul, tvDeskripsi, tvPemeran, tvJumlahEpisode, tvUlasan;

        public VHSH(@NonNull View itemView){
            super(itemView);

            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvPemeran = itemView.findViewById(R.id.tv_pemeran);
            tvJumlahEpisode = itemView.findViewById(R.id.tv_jumlah_episode);
            tvUlasan = itemView.findViewById(R.id.tv_ulasan);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Operasi apa yang akan dilakukan?");
                    pesan.setCancelable(true);

                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            hapusSH(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    });

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent pindah = new Intent(ctx, UbahActivity.class);
                            pindah.putExtra("xId", tvId.getText().toString());
                            pindah.putExtra("xJudul", tvJudul.getText().toString());
                            pindah.putExtra("xDeskripsi", tvDeskripsi.getText().toString());
                            pindah.putExtra("xPemeran", tvPemeran.getText().toString());
                            pindah.putExtra("xJumlahEpisode", tvJumlahEpisode.getText().toString());
                            pindah.putExtra("xUlasan", tvUlasan.getText().toString());
                            ctx.startActivity(pindah);
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }

        private void hapusSH(String idSH){
            APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ModelResponse> proses = ARD.ardDelete(idSH);

            proses.enqueue(new Callback<ModelResponse>() {
                @Override
                public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(ctx, "Kode: " + kode + ", Pesan: " + pesan, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveSH();
                }

                @Override
                public void onFailure(Call<ModelResponse> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
