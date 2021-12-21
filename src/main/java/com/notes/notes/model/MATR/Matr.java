package com.notes.notes.model.MATR;
import com.notes.notes.model.STUDENT.Student;
import com.notes.notes.model.UF.UF;

import java.time.LocalDateTime;
import java.util.List;

public class Matr {
    private int idMatr;
    private List<UF> llistaUFs;
    private Student student;
    private LocalDateTime dataMatr;

    public Matr(Student student, LocalDateTime dataMatr) {
        this.student = student;
        this.dataMatr = dataMatr;
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

    public LocalDateTime getDataMatr() {
        return dataMatr;
    }

    public void setDataMatr(LocalDateTime dataMatr) {
        this.dataMatr = dataMatr;
    }
}
