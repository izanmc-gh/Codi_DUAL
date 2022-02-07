package com.notes.notes.model.NOTA_UF;

// Notes UF: la UF, nota ordinaria, nota extraordinaria iniciades a 0

import com.notes.notes.model.UF.UF;

public class Nota_UF {
    private UF uf;
    private int nota_ordinaria = 0;
    private int nota_extraordinaria = 0;

    public Nota_UF() {
    }

    public Nota_UF(UF uf, int nota_ordinaria, int nota_extraordinaria) {
        this.uf = uf;
        this.nota_ordinaria = nota_ordinaria;
        this.nota_extraordinaria = nota_extraordinaria;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public int getNota_ordinaria() {
        return nota_ordinaria;
    }

    public void setNota_ordinaria(int nota_ordinaria) {
        this.nota_ordinaria = nota_ordinaria;
    }

    public int getNota_extraordinaria() {
        return nota_extraordinaria;
    }

    public void setNota_extraordinaria(int nota_extraordinaria) {
        this.nota_extraordinaria = nota_extraordinaria;
    }
}
