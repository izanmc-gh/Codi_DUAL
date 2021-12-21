package com.notes.notes.service.MP;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;

import java.util.List;

public interface IMPService {

    public List<MP> getAllMP();

    public List<UF> getAllUF(String numMP);

    public boolean addMP(MP mp);

    public boolean removeMP(MP mp);

    public MP getMPByidMP(String idMP);

    public boolean addUFaMP(String numMP, UF uf);

    public boolean editaMP(MP mp);


}
