package com.notes.notes.service.MATR;

import com.notes.notes.model.MATR.Matr;

import java.util.List;

public interface IMatrService {

    public List<Matr> getAllMatr();

    public boolean addMatr(Matr matr);

    public boolean removeMatr(Matr matr);

    public Matr getMatrByidMatr(String idMatr);

    public boolean editaMatr(Matr matr);

}
