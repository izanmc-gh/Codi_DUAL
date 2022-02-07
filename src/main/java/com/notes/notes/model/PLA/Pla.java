package com.notes.notes.model.PLA;

import com.notes.notes.model.CURS.Curs;
import com.notes.notes.model.MP.MP;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pla {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idPla;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 3, max = 10, message = "El nom del Pla ha de tenir entre 3 i 10 caracters")
    private String nomPla;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 4, message = "L'any del Pla ha de tenir 4 caracters")
    private String anyPla;
    @OneToMany (mappedBy = "pla", cascade = CascadeType.ALL)
    private List<MP> llistaMP;

    public Pla(String nomPla) {
        this.nomPla = nomPla;
    }

    public Pla() {

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

    public String getAnyPla() {
        return anyPla;
    }

    public void setAnyPla(String anyPla) {
        this.anyPla = anyPla;
    }

    public List<MP> getLlistaMP() {
        return llistaMP;
    }

    public void setLlistaMP(List<MP> llistaMP) {
        this.llistaMP = llistaMP;
    }

    public Pla(int idPla, String nomPla, String anyPla) {
        this.idPla = idPla;
        this.nomPla = nomPla;
        this.anyPla = anyPla;
        this.llistaMP = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Pla{" +
                "idPla=" + idPla +
                ", nomPla='" + nomPla + '\'' +
                ", anyPla='" + anyPla + '\'' +
                ", llistaMP=" + llistaMP +
                '}';
    }
}
