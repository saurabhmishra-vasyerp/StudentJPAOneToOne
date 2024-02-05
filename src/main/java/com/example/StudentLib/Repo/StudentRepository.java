package com.example.StudentLib.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentLib.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
