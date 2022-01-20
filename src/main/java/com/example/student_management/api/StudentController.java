package com.example.student_management.api;

import java.util.*;

import com.example.student_management.exceptions.InvalidUniversityClassException;
import com.example.student_management.exceptions.StudentEmptyNameException;
import com.example.student_management.exceptions.StudentNotExistException;
import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/name") // student/name?name=XXX&other
    @RequiresPermissions("student:read")
    public List<Student> getSudents(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/contain_name") // student/name?contain_name=T
    @RequiresPermissions("student:read")
    public List<Student> getStudentsContainName(@RequestParam String name) {
        return studentService.getStudentsContainName(name);
    }

    @GetMapping("/class") // student/class?year=2021&number=1
    @RequiresPermissions("student:read")
    public List<Student> getStudentsInClass(@RequestParam int year, @RequestParam int number) {
        return studentService.getStudentsInClass(year, number);
    }

    @RequestMapping("/register")
    @PostMapping
    @RequiresPermissions("student:write")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.addStudent(student);
            return ResponseEntity.ok("Registered Student. " + savedStudent.toString());
        }
        catch (StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "assign_class/{sid}/{cid}") // Assignclass using studentid and Cid
    @RequiresPermissions("student:write")
    public ResponseEntity<String> assignClass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {
            Student updatedStudent = studentService.assignClass(studentId, classId);
            return ResponseEntity.ok("Assigned class: " + updatedStudent.toString());
        } catch (StudentNotExistException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
