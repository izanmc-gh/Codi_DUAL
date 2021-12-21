package com.notes.notes.repos;

import com.notes.notes.model.MP.MP;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MpRepository extends CrudRepository<MP, Integer> {
    public List<MP> findAllByNumMP(String numMP);
}
