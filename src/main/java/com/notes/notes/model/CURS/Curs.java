package com.notes.notes.model.CURS;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.UF.UF;

import java.util.List;

public class Curs {
    private int idCurs;
    private String nivellCurs;
    private Pla pla;
    private List<UF> llistaUFs;

    public Curs(String nivellCurs, Pla pla) {
        this.nivellCurs = nivellCurs;
        this.pla = pla;
    }

    public Curs() {

    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public String getNivellCurs() {
        return nivellCurs;
    }

    public void setNivellCurs(String nivellCurs) {
        this.nivellCurs = nivellCurs;
    }

    public Pla getPla() {
        return pla;
    }

    public void setPla(Pla pla) {
        this.pla = pla;
    }

    public List<UF> getLlistaUFs() {
        return llistaUFs;
    }

    public void setLlistaUFs(List<UF> llistaUFs) {
        this.llistaUFs = llistaUFs;
    }
}
