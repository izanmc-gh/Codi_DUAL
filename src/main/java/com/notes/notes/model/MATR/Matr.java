package com.notes.notes.model.MATR;
import com.notes.notes.model.EXP.Exp;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.STUDENT.Student;
import com.notes.notes.model.UF.UF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Matr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMatr;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 2, max = 10, message = "La data de l'Expedient' ha de tenir entre 2 i 10 caracters")
    private String dataMatr;
    //@OneToMany(mappedBy = "matr", cascade = CascadeType.ALL)
    //private List<Exp> llistaExp;
    //@OneToOne(mappedBy = "matr", fetch = FetchType.LAZY, optional = false)
    //private Student student;
    @ManyToOne
    private Exp exp;


    public Matr() {
    }

    public int getIdMatr() {
        return idMatr;
    }

    public void setIdMatr(int idMatr) {
        this.idMatr = idMatr;
    }

    /*
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Exp> getLlistaExp() {
        return llistaExp;
    }

    public void setLlistaExp(List<Exp> llistaExp) {
        this.llistaExp = llistaExp;
    }

    */

    public String getDataMatr() {
        return dataMatr;
    }

    public void setDataMatr(String dataMatr) {
        this.dataMatr = dataMatr;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}
