package com.notes.notes.repos;

import com.notes.notes.model.UF.UF;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UfRepository extends CrudRepository<UF, Integer> {
    public List<UF> findAllByNumUF(String numUF);
}
