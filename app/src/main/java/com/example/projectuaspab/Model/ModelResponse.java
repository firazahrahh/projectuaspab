package com.example.projectuaspab.Model;

import java.util.List;

public class ModelResponse {

    private String kode, pesan;

    private List<ModelSH> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelSH> getData() { return data;}
}
