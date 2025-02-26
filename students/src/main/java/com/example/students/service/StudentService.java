package com.example.students.service;

import com.example.students.exception.StudentAlredyExistException;
import com.example.students.exception.StudentNotFoundException;
import com.example.students.model.Student;
import com.example.students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student addSudent(Student student) {
        if(studentAlredyExists(student.getEmail())){
            throw new StudentAlredyExistException(student.getEmail() + " alredy exist");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());

            return studentRepository.save(st);

        }).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found "));
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Sorry, Student with ID: " + id + " not found"));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, student not found");
        }
        studentRepository.deleteById(id);
    }

    private Boolean studentAlredyExists(String email){
        return studentRepository.findByEmail(email).isPresent();
    }
}
