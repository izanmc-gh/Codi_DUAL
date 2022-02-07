package com.notes.notes.repos;

import com.notes.notes.model.STUDENT.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    public List<Student> findAllByIdStudent(String idStudent);


}
