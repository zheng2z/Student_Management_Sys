package com.example.student_management.service;
import com.example.student_management.DAO.*;
import com.example.student_management.exceptions.InvalidUniversityClassException;
import com.example.student_management.exceptions.StudentEmptyNameException;
import com.example.student_management.exceptions.StudentNotExistException;
import com.example.student_management.mapper.StudentMapper;
import com.example.student_management.model.Student;
import com.example.student_management.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private StudentDAO studentDao;
    private UniversityClassDAO universityClassDao;
    private StudentMapper studentMapper;

    @Autowired
    public StudentService(StudentDAO studentDao,
                          UniversityClassDAO universityClassDao,
                          StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.universityClassDao = universityClassDao;
        this.studentMapper = studentMapper;
    }

    public Student addStudent(Student student) {
        if (student.getName().isEmpty()) {
            throw new StudentEmptyNameException("Student Name Cannot Be Empty!");
        }

        return studentDao.save(student);
    }

    public Student updateStudent(Student student) {
        if (student.getId() == null || studentDao.existsById(student.getId())) {
            throw new StudentNotExistException("Cannot Find Student Id!");
        }

        return studentDao.save(student);
    }

    public Student assignClass(Long studentId, Long classId){
        if (!studentDao.existsById(studentId)) {
            throw new StudentNotExistException("Cannot Find Student Id: " + studentId);
        }
        if (!universityClassDao.existsById(classId)) {
            throw new InvalidUniversityClassException("Cannot Find Class Id: " + classId);
        }

        Student student = getStudentById(studentId).get();
        UniversityClass universityClass = universityClassDao.findById(classId).get();

        student.setUniversityClass(universityClass);
        return studentDao.save(student);
    }

    public List<Student> getAllStudents() {
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentDao.findById(id);
    }

    public List<Student> getStudentsByName(String name) {
        return  studentDao.findByName(name);
    }

    public List<Student> getStudentsContainName(String name) {
        return studentMapper.getStudentsContainStrInName("%" + name + "%");
    }

    public List<Student> getStudentsInClass(int year, int number) {
        return studentMapper.getStudentsInClass(year, number);
    }

}
