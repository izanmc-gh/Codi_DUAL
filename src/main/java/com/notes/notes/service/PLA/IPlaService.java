package com.notes.notes.service.PLA;

import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.UF.UF;

import java.util.List;

public interface IPlaService {

    public List<Pla> getAllPla();

    public boolean addPla(Pla pla);

    public boolean editaPla(Pla pla);

    public boolean removePla(Pla pla);

    public Pla getPlaByidPla(String idPla);

}
