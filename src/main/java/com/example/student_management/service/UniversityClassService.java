package com.example.student_management.service;

import java.util.*;
import com.example.student_management.DAO.UniversityClassDAO;
import com.example.student_management.exceptions.InvalidUniversityClassException;
import com.example.student_management.model.UniversityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityClassService {
    UniversityClassDAO universityClassDao;

    @Autowired
    public UniversityClassService(UniversityClassDAO universityClassDao) {
        this.universityClassDao = universityClassDao;
    }

    public List<UniversityClass> getAllClasses() {
        return (List<UniversityClass>) universityClassDao.findAll();
//        return new ArrayList<>();
    }

    public UniversityClass addClass(UniversityClass universityClass) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (universityClass.getYear() < currentYear) {
            throw new InvalidUniversityClassException("Cannot Add Class in The Past!");
        }

        if(universityClass.getYear() > currentYear + 1){
            throw new InvalidUniversityClassException("Cannot Add Class in The Future!");
        }

        return universityClassDao.save(universityClass);
    }

}
