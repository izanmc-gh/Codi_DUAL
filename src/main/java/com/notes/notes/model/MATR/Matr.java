package com.notes.notes.model.MATR;
import com.notes.notes.model.EXP.Exp;
import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.STUDENT.Student;
import com.notes.notes.model.UF.UF;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Matr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idMatr;

    //@OneToOne(mappedBy = "matr", fetch = FetchType.LAZY, optional = false)
    //private Student student;
    private LocalDate dataMatr;
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
    */

    public LocalDate getDataMatr() {
        return dataMatr;
    }

    public void setDataMatr(LocalDate dataMatr) {
        this.dataMatr = dataMatr;
    }
}
