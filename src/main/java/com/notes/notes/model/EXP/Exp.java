package com.notes.notes.model.EXP;

import com.notes.notes.model.MATR.Matr;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.STUDENT.Student;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exp {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idExp;
    @NotBlank(message = "El camp no es pot deixar en blanc")
    @Size(min = 2, max = 10, message = "La data de l'Expedient' ha de tenir entre 2 i 10 caracters")
    private LocalDate dataExp;
    @OneToMany(mappedBy = "exp", cascade = CascadeType.ALL)
    private List<Matr> llistaMatrs;
    private int numMatr = 0;
    @ManyToOne
    private Pla pla;
    @ManyToOne
    private Student student;

    public Exp(Pla pla, Student student) {
        this.dataExp = LocalDate.now();
        this.llistaMatrs = new ArrayList<>();
        this.pla = pla;
        this.student = student;
    }

    public Exp() {

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getNumMatr() {
        return numMatr;
    }

    public void setNumMatr(int numMatr) {
        this.numMatr = numMatr;
    }

    public int getIdExp() {
        return idExp;
    }

    public void setIdExp(int idExp) {
        this.idExp = idExp;
    }

    public LocalDate getDataExp() {
        return dataExp;
    }

    public void setDataExp(LocalDate dataExp) {
        this.dataExp = dataExp;
    }

    public List<Matr> getLlistaMatrs() {
        return llistaMatrs;
    }

    public void setLlistaMatrs(List<Matr> llistaMatrs) {
        this.llistaMatrs = llistaMatrs;
    }

    public Pla getPla() {
        return pla;
    }

    public void setPla(Pla pla) {
        this.pla = pla;
    }
}
