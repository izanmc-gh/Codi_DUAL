package com.notes.notes.service.UF;

import com.notes.notes.model.UF.UF;
import com.notes.notes.model.MP.MP;
import com.notes.notes.repos.UfRepository;
import com.notes.notes.repos.MpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UFServiceImpl implements IUFService {

    @Autowired
    UfRepository UFRepo;
    MpRepository MPRepo;

    @Override
    public List<UF> getAllUF() {
        return (List<UF>) UFRepo.findAll();
    }

    @Override
    public boolean addUF(UF uf) {
        UFRepo.save(uf);
        return true;
    }

    @Override
    public boolean removeUF(UF uf) {
        UFRepo.deleteById(uf.getIdUF());
        return true;
    }

    @Override
    public UF getUFByidUF(int idUF) {
        Optional<UF> ufOptional = UFRepo.findById(idUF);
        return ufOptional.orElse(null);
    }

    @Override
    public boolean editaUF(UF uf) {
        UFRepo.save(uf);
        return true;
    }

}
