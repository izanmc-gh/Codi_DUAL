package com.notes.notes.repos;

import com.notes.notes.model.PLA.Pla;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaRepository extends CrudRepository<Pla, Integer> {
    public List<Pla> findAllByidPla(int idPla);
}
