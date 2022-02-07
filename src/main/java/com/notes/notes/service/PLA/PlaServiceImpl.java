package com.notes.notes.service.PLA;

import com.notes.notes.model.PLA.Pla;
import com.notes.notes.model.UF.UF;
import com.notes.notes.repos.PlaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaServiceImpl implements IPlaService {

    @Autowired
    PlaRepository PlaRepo;

    @Override
    public List<Pla> getAllPla() {
        return (List<Pla>) PlaRepo.findAll();
    }

    @Override
    public boolean addPla(Pla pla) {
        PlaRepo.save(pla);
        return true;
    }

    @Override
    public boolean editaPla(Pla pla) {
        PlaRepo.save(pla);
        return true;
    }

    @Override
    public boolean removePla(Pla pla) {
        PlaRepo.deleteById(pla.getIdPla());
        return true;
    }

    @Override
    public Pla getPlaByidPla(int idPla) {
        Optional<Pla> plaOptional = PlaRepo.findById(idPla);
        return plaOptional.orElse(null);
    }
}
