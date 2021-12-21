package com.notes.notes.model.PLA;

import com.notes.notes.model.CURS.Curs;

import java.util.List;

public class Pla {
    private int idPla;
    private String nomPla;
    private List<Curs> llistaCurs;

    public Pla(String nomPla) {
        this.nomPla = nomPla;
    }

    public int getIdPla() {
        return idPla;
    }

    public void setIdPla(int idPla) {
        this.idPla = idPla;
    }

    public String getNomPla() {
        return nomPla;
    }

    public void setNomPla(String nomPla) {
        this.nomPla = nomPla;
    }

    public List<Curs> getLlistaCurs() {
        return llistaCurs;
    }

    public void setLlistaCurs(List<Curs> llistaCurs) {
        this.llistaCurs = llistaCurs;
    }
}
