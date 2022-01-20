package com.example.student_management.DAO;

import com.example.student_management.model.Student;
import com.example.student_management.model.UniversityClass;
import org.springframework.data.repository.CrudRepository;

public interface UniversityClassDAO extends CrudRepository<UniversityClass, Long> {


}
