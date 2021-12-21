package com.notes.notes.service.STUDENT;

import com.notes.notes.model.STUDENT.Student;

import java.util.List;

public interface IStudentService {

    public List<Student> getAllStudent();

    public boolean addStudent(Student student);

    public boolean removeStudent(Student student);

    public Student getStudentByidStudent(String idStudent);

    public boolean editaStudent(Student student);
}
