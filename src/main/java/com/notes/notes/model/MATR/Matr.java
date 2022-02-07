package com.notes.notes.model.MATR;
import com.notes.notes.model.STUDENT.Student;
import com.notes.notes.model.UF.UF;

import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;

public class Matr {
    private int idMatr;
    private List<UF> llistaUFs;
    @OneToOne(mappedBy = "matr", fetch = FetchType.LAZY, optional = false)
    private Student student;
    private LocalDate dataMatr;

    public Matr() {
    }

    public int getIdMatr() {
        return idMatr;
    }

    public void setIdMatr(int idMatr) {
        this.idMatr = idMatr;
    }

    public List<UF> getLlistaUFs() {
        return llistaUFs;
    }

    public void setLlistaUFs(List<UF> llistaUFs) {
        this.llistaUFs = llistaUFs;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDate getDataMatr() {
        return dataMatr;
    }

    public void setDataMatr(LocalDate dataMatr) {
        this.dataMatr = dataMatr;
    }
}
