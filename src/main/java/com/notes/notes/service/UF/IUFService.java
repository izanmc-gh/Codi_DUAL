package com.notes.notes.service.UF;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;

import java.util.List;

public interface IUFService {

    public List<UF> getAllUF();

    public boolean addUF(UF uf);

    public boolean editaUF(UF uf);

    public boolean removeUF(UF uf);

    public UF getUFByidUF(String idUF);


}
