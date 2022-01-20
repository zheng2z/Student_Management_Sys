package com.example.student_management.model;



import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "University_Class")
@Data
public class UniversityClass {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer number;


    @OneToMany(mappedBy = "universityClass")
    @Getter(AccessLevel.NONE) // Disable Getter Setter to avoid loop get
    @Setter(AccessLevel.NONE)
    List<Student> students;

//    Replaced by @Data
//    public UniversityClass(Long id, Integer year, Integer number) {
//        this.id = id;
//        this.year = year;
//        this.number = number;
//    }
//
//    public UniversityClass() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Integer getYear() {
//        return year;
//    }
//
//    public void setYear(Integer year) {
//        this.year = year;
//    }
//
//    public Integer getNumber() {
//        return number;
//    }
//
//    public void setNumber(Integer number) {
//        this.number = number;
//    }
//
//    @Override
//    public String toString() {
//        String str = "";
//        str += "Primary Id: " + getId();
//        str += " Year: " + getYear();
//        str += " Number: " + getNumber();
//        return str;
//    }


}
