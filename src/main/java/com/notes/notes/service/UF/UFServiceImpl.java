package com.notes.notes.service.UF;

import com.notes.notes.model.UF.UF;
import com.notes.notes.repos.UfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UFServiceImpl implements IUFService{

    @Autowired
    UfRepository UFRepo;

    private List<UF> llistaUFS;

    public UFServiceImpl() {
        llistaUFS = new ArrayList<>();
    }

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
    public UF getUFByidUF(String idUF) {
        Optional<UF> ufOptional = UFRepo.findById(Integer.parseInt(idUF));
        return ufOptional.orElse(null);
    }

    @Override
    public boolean editaUF(UF uf) {
        UFRepo.save(uf);
        return true;
    }

}
