package com.notes.notes.service.EXP;

import com.notes.notes.model.EXP.Exp;
import com.notes.notes.model.MP.MP;

import java.util.List;

public interface IExpService {

    public List<Exp> getAllExp();

    public boolean addExp(Exp exp);

    public boolean removeExp(Exp exp);

    public Exp getExpByidExp(String idExp);

    public boolean editaExp(Exp exp);

}
