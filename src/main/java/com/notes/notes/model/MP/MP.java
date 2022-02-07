package com.notes.notes.model.MP;

import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.UF.UF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class MP {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idMP;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 2, max = 10, message = "El nom de la MP ha de tenir entre 2 i 10 caracters")
    private String nomMP;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 1, max = 10, message = "El número de la MP ha de tenir entre 1 i 10 caracters")
    private String numMP;
    @OneToMany(mappedBy = "mp", cascade = CascadeType.ALL)
    private List<UF> llistaUFs;
    @ManyToOne
    private Pla pla;

    public MP() {
    }

    public MP(String numMP, String nomMP) {
        this.numMP = numMP;
        this.nomMP = nomMP;
        this.llistaUFs = new ArrayList<>();
    }
    public MP(String numMP, String nomMP, ArrayList<UF> llistaUFs) {
        this.numMP = numMP;
        this.nomMP = nomMP;
        this.llistaUFs = llistaUFs;
    }

    public String getNumMP() {
        return numMP;
    }

    public void setNumMP(String numMP) {
        this.numMP = numMP;
    }

    public String getNomMP() {
        return nomMP;
    }

    public void setNomMP(String nomMP) {
        this.nomMP = nomMP;
    }

    public List<UF> getLlistaUFs() {
        return llistaUFs;
    }

    public void setLlistaUFs(List<UF> llistaUFs) {
        this.llistaUFs = llistaUFs;
    }

    public void addUFaMP(UF uf) {
        this.llistaUFs.add(uf);
    }

    public boolean editaUF(UF uf) {
        for (UF ufTemp : this.llistaUFs) {
            if (ufTemp.getNumUF().equalsIgnoreCase(uf.getNumUF())) {
                ufTemp.setNomUF(uf.getNomUF());
                return true;
            }
        }
        return false;
    }

    public boolean removeUFtoMP(UF uf) {
        for (UF uf1 : this.llistaUFs) {
            if (uf1 == uf) {
                this.llistaUFs.remove(uf1);
                return true;
            }
        }
        return false;
    }

    public UF getUFByNum(String numUF) {
        for (UF uf : this.llistaUFs) {
            if (uf.getNumUF().equals(numUF)) {
                return uf;
            }
        }
        return null;
    }

    public int getIdMP() {
        return idMP;
    }

    public void setIdMP(int idMP) {
        this.idMP = idMP;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MP mp = (MP) o;
        return Objects.equals(nomMP, mp.nomMP) && Objects.equals(numMP, mp.numMP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMP, numMP);
    }

    @Override
    public String toString() {
        return "MP{" +
                "idMP=" + idMP +
                ", nomMP='" + nomMP + '\'' +
                ", numMP='" + numMP + '\'' +
                '}';
    }
}
