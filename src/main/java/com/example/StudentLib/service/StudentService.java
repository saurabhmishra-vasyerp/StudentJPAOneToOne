package com.example.StudentLib.service;

import java.util.List;

import com.example.StudentLib.Dto.StudentDto;
import com.example.StudentLib.model.Student;

public interface StudentService {
	List<StudentDto> getAllStudents();
	StudentDto getStudentById(Long id);
	Student deleteStudentById(Long id);
	
	public void save(StudentDto studentDto);
	public void update(long id, StudentDto studentDto);
	

}
