package com.notes.notes.service.STUDENT;

import com.notes.notes.model.STUDENT.Student;
import com.notes.notes.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService{

    @Autowired
    StudentRepository StudentRepo;

    //private List<Student> llistaStudents;

    public StudentServiceImpl() {
        //llistaStudents = new ArrayList<>();
        //llistaStudents.add(new Student("47958416N","Izan","Moral", LocalDate.of(1999, 1, 30),"izan@cirvianum.cat"));
    }

    @Override
    public List<Student> getAllStudent() {
        return (List<Student>) StudentRepo.findAll();
    }

    @Override
    public boolean addStudent(Student student) {
        StudentRepo.save(student);
        return true;
    }

    @Override
    public boolean removeStudent(Student student) {
        StudentRepo.deleteById(student.getIdStudent());
        return true;
    }

    @Override
    public Student getStudentByidStudent(String idStudent) {
        Optional<Student> studentOptional = StudentRepo.findById(Integer.parseInt(idStudent));
        return studentOptional.orElse(null);
    }

    @Override
    public boolean editaStudent(Student student) {
        StudentRepo.save(student);
        return true;
    }
}
