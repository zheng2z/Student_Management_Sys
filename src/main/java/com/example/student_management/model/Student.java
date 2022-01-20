package com.example.student_management.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="student")
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name="name")
    private String name;

    @ManyToOne // map to single class
    @JoinColumn(name="university_class_id")
    private UniversityClass universityClass;

//    Replaced by @Data
//    public UniversityClass getUniversityClass() {
//        return universityClass;
//    }
//
//    public void setUniversityClass(UniversityClass universityClass) {
//        this.universityClass = universityClass;
//    }
//

//    public Student() {}
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() { return name; }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override // customize student info
//    public String toString() {
//        String str = "";
//        str += "Primary ID: " + getId();
//        str += " Name: " + getName();
//        return str;
//    }

}
