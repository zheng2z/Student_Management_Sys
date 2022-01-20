package com.example.student_management.api;

import java.util.*;

import com.example.student_management.exceptions.InvalidUniversityClassException;
import com.example.student_management.exceptions.StudentEmptyNameException;
import com.example.student_management.model.Student;
import com.example.student_management.model.UniversityClass;
import com.example.student_management.service.UniversityClassService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/class")

public class UniversityClassController {

    private UniversityClassService universityClassService;

    @Autowired
    public UniversityClassController(UniversityClassService universityClassService) {
        this.universityClassService = universityClassService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    List<UniversityClass> getAllClasses() {
        return universityClassService.getAllClasses();
    }

    @PostMapping
    @RequestMapping("/add")
    @RequiresPermissions("student:write")
    public ResponseEntity<String> addClass(@RequestBody UniversityClass universityClass){
        try {
            UniversityClass savedUniversityClass = universityClassService.addClass(universityClass);
            return ResponseEntity.ok("Added class. " + savedUniversityClass.toString());
        }
        catch (InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
