package com.notes.notes.service.MP;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;
import com.notes.notes.repos.MpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MPServiceImpl implements IMPService {

    @Autowired
    MpRepository MPRepo;

    private List<MP> llistaMPS;
    private List<UF> llistaUFS;

    public MPServiceImpl() {
        /*
        llistaMPS = new ArrayList<>();
        llistaMPS.add(new MP("1", "MP01"));
        llistaMPS.add(new MP("2", "MP02"));
        llistaMPS.add(new MP("3", "MP03"));
        */
        llistaUFS = new ArrayList<>();
    }

    @Override
    public List<MP> getAllMP() {
        return (List<MP>)MPRepo.findAll();
    }

    @Override
    public List<UF> getAllUF(String numMP) {
        for (MP mp : this.llistaMPS) {
            if (numMP.equalsIgnoreCase(mp.getNumMP())) {
                return mp.getLlistaUFs();
            }
        }
        return null;
    }

    @Override
    public boolean addMP(MP mp) {
        MPRepo.save(mp);
        return true;
    }

    @Override
    public boolean removeMP(MP mp) {
        MPRepo.deleteById(mp.getIdMP());
        return true;
    }

    @Override
    public MP getMPByidMP(String idMP) {
        Optional<MP> mpOptional = MPRepo.findById(Integer.parseInt(idMP));
        return mpOptional.orElse(null);
    }

    @Override
    public boolean editaMP(MP mp) {
        MPRepo.save(mp);
        return true;
    }

    @Override
    public boolean addUFaMP(String numMP, UF uf) {
        try {
            for (MP mp : this.llistaMPS) {
                System.out.println(mp);
                if (numMP.equalsIgnoreCase(mp.getNumMP())) {
                    mp.addUFaMP(uf);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}