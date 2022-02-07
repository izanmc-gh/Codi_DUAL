package com.notes.notes.model.UF;

import com.notes.notes.model.CURS.Curs;
import com.notes.notes.model.MATR.Matr;
import com.notes.notes.model.MP.MP;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class UF {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idUF;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 2, max = 10, message = "El nom de la UF ha de tenir entre 2 i 10 caracters")
    private String nomUF;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 1, max = 10, message = "El número de la UF ha de tenir entre 1 i 10 caracters")
    private String numUF;
    @NotNull(message = "El camp no es pot deixar en blanc")
    private int horesUF;
    //@OneToMany(cascade = CascadeType.ALL)
    //private List<Matr> llistaMatr;
    @ManyToOne
    private MP mp;
    //private Curs curs;

    //Mostrar UFs per id de MP getUFByIdMP

    public UF() {
    }

    public UF(String nomUF, String numUF, int horesUF) {
        this.nomUF = nomUF;
        this.numUF = numUF;
        this.horesUF = horesUF;
    }

    public String getNomUF() {
        return nomUF;
    }

    public void setNomUF(String nomUF) {
        this.nomUF = nomUF;
    }

    public String getNumUF() {
        return numUF;
    }

    public void setNumUF(String numUF) {
        this.numUF = numUF;
    }

    public int getHoresUF() {
        return horesUF;
    }

    public void setHoresUF(int horesUF) {
        this.horesUF = horesUF;
    }

    public int getIdUF() {
        return idUF;
    }

    public void setIdUF(int idUF) {
        this.idUF = idUF;
    }

    public MP getMp() {
        return mp;
    }

    public void setMp(MP mp) {
        this.mp = mp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UF uf = (UF) o;
        return horesUF == uf.horesUF && Objects.equals(nomUF, uf.nomUF) && Objects.equals(numUF, uf.numUF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomUF, numUF, horesUF);
    }

    @Override
    public String toString() {
        return "UF{" +
                "idUF=" + idUF +
                ", nomUF='" + nomUF + '\'' +
                ", numUF='" + numUF + '\'' +
                ", horesUF=" + horesUF +
                ", mp=" + mp +
                '}';
    }
}
