package com.notes.notes.service.EXP;

import com.notes.notes.model.EXP.Exp;
import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;
import com.notes.notes.repos.ExpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpServiceImpl implements IExpService {

    @Autowired
    ExpRepository ExpRepo;

    @Override
    public List<Exp> getAllExp() {
        return (List<Exp>) ExpRepo.findAll();
    }

    @Override
    public boolean addExp(Exp exp) {
        ExpRepo.save(exp);
        return true;
    }

    @Override
    public boolean removeExp(Exp exp) {
        ExpRepo.deleteById(exp.getIdExp());
        return true;
    }

    @Override
    public Exp getExpByidExp(String idExp) {
        Optional<Exp> expOptional = ExpRepo.findById(Integer.valueOf(idExp));
        return expOptional.orElse(null);
    }

    @Override
    public boolean editaExp(Exp exp) {
        ExpRepo.save(exp);
        return true;
    }
}
