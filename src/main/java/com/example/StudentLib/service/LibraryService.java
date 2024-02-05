package com.example.StudentLib.service;

import java.util.List;

import com.example.StudentLib.Dto.LibraryDto;
import com.example.StudentLib.model.Library;

public interface LibraryService {
	List<LibraryDto> getAllStudents();
	LibraryDto getStudentById(Long id);
    Library deleteBookById(Long id);
    public void save(LibraryDto libraryDTO);
	public void update(long id, LibraryDto libraryDTO);
}
