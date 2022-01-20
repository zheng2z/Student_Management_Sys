package com.example.student_management.DAO;

import java.util.*;
import com.example.student_management.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentDAO extends CrudRepository<Student, Long> {
    List<Student> findByName(String name);
}
