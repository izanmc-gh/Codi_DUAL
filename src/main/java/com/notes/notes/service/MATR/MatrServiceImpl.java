package com.notes.notes.service.MATR;

/*
import com.notes.notes.model.MATR.Matr;
import com.notes.notes.repos.MatrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class MatrServiceImpl implements IMatrService{

    @Autowired
    MatrRepository MatrRepo;

    @Override
    public List<Matr> getAllMatr() {
        return (List<Matr>) MatrRepo.findAll();
    }

    @Override
    public boolean addMatr(Matr matr) {
        MatrRepo.save(matr);
        return true;
    }

    @Override
    public boolean removeMatr(Matr matr) {
        MatrRepo.deleteById(matr.getIdMatr());
        return true;
    }

    @Override
    public Matr getMatrByidMatr(String idMatr) {
        Optional<Matr> matrOptional = MatrRepo.findById(Integer.parseInt(idMatr));
        return matrOptional.orElse(null);
    }

    @Override
    public boolean editaMatr(Matr matr) {
        MatrRepo.save(matr);
        return true;
    }
}
*/