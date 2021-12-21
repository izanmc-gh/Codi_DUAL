package com.notes.notes.service.CURS;


import com.notes.notes.model.CURS.Curs;
import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CursServiceImpl implements ICursService {

    private List<Curs> llistaCurs;
    private List<MP> llistaMPS;

    @Override
    public List<Curs> getAllCurs() {
        return this.llistaCurs;
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
    public boolean addCurs(Curs curs) {
        try {
            this.llistaCurs.add(curs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean removeCurs(int idCurs) {
        for (Curs curs : this.llistaCurs) {
            if (idCurs==curs.getIdCurs()) {
                this.llistaCurs.remove(curs);
                return true;
            }
        }
        return false;
    }

    @Override
    public Curs getCursById(int idCurs) {
        for (Curs curs : this.llistaCurs) {
            if (curs.getIdCurs()==idCurs) {
                return curs;
            }
        }
        return null;
    }

    @Override
    public boolean editaCurs(Curs curs) {
        for (Curs cursTemp : this.llistaCurs) {
            if (cursTemp.getIdCurs()==(curs.getIdCurs())) {
                cursTemp.setNivellCurs(curs.getNivellCurs());
                return true;
            }
        }
        return false;
    }
}