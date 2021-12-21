package com.notes.notes.service.CURS;

import com.notes.notes.model.CURS.Curs;
import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;

import java.util.List;


public interface ICursService {

    public List<Curs> getAllCurs();

    public List<UF> getAllUF(String numMP);

    public boolean addCurs(Curs curs);

    public boolean removeCurs(int idCurs);

    public Curs getCursById(int idCurs);

    public boolean editaCurs(Curs curs);

}
