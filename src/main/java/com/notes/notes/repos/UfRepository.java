package com.notes.notes.repos;

import com.notes.notes.model.MP.MP;
import com.notes.notes.model.UF.UF;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UfRepository extends CrudRepository<UF, Integer> {
    Iterable<UF> findUFSByMp(MP mp);
}
