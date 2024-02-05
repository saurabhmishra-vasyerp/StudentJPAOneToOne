package com.example.StudentLib.Repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentLib.model.Library;

public interface LibraryRepository extends JpaRepository <Library,Long>{

}
