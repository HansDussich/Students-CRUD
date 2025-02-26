package com.example.students.service;

import com.example.students.model.Student;

import java.util.List;

public interface IStudentService {
    Student addSudent(Student student);

    List<Student> getStudent();

    Student updateStudent(Student student, Long id);

    Student getStudentById(Long id);

    void deleteStudent(Long id);


}
