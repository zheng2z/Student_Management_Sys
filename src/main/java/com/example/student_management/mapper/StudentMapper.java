package com.example.student_management.mapper;

import com.example.student_management.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {

    // SELECT * FROM student where name LIKE %T% ;match any include T
    @Select("SELECT * FROM student where name LIKE #{name}")// make name a parameter
    List<Student> getStudentsContainStrInName(@Param("name") String name);

    // SELECT * from students where university_class_id IN
    // (SELECT id FROM university_class where year = 2021 AND number = 1)
    @Select("SELECT * from student where university_class_id IN" +
            "(SELECT id FROM university_class where year = #{year} AND number = #{number} )")
    List<Student> getStudentsInClass(@Param("year") int year, @Param("number") int number);

}
