package com.example.StudentLib.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentLib.Dto.LibraryDto;

import com.example.StudentLib.model.*;
import com.example.StudentLib.Repo.LibraryRepository;
import com.example.StudentLib.Repo.StudentRepository;

@Service
public class LibraryServiceimpl implements LibraryService{
	@Autowired
    private LibraryRepository libraryRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<LibraryDto> getAllStudents() {
		// TODO Auto-generated method stub
		List<LibraryDto> list = new ArrayList<>();
		List<Library>librarylist =  libraryRepository.findAll();
		for (Library library : librarylist) {
			LibraryDto libraryDTO = new LibraryDto();
			libraryDTO.setBookId(library.getBookId());
			libraryDTO.setBookName(library.getBookName());
			libraryDTO.setBookAuthor(library.getBookAuthor());
			list.add(libraryDTO);
		}
		return list;
	
		
	}

	@Override
	public LibraryDto getStudentById(Long id) {
		// TODO Auto-generated method stub
		Library library = libraryRepository.findById(id).get();
		LibraryDto libraryDTO = new LibraryDto();
		libraryDTO.setBookName(library.getBookName());
		libraryDTO.setBookAuthor(library.getBookAuthor());
		return libraryDTO;
	
	}

	@Override
	public  Library deleteBookById(Long id) {
		// TODO Auto-generated method stub
		Library library = libraryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

		libraryRepository.deleteById(id);

		// Convert the deleted student to DTO and return it
		return library;
	}

	@Override
	public void save(LibraryDto libraryDTO) {
		// TODO Auto-generated method stub
		if (libraryDTO.getBookId() == 0) {
			Library library = new Library();
			library.setBookName(libraryDTO.getBookName());
			library.setBookAuthor(libraryDTO.getBookAuthor());
			libraryRepository.save(library);
		} else {
			Library libraryEdit = libraryRepository.findById(libraryDTO.getBookId()).get();
			if (libraryDTO.getBookName() != null) {
				libraryEdit.setBookName(libraryDTO.getBookName());
			} else if (libraryDTO.getBookAuthor() != null) {
				libraryEdit.setBookAuthor(libraryDTO.getBookAuthor());
			}
			libraryRepository.save(libraryEdit);
		}
		
	}

	@Override
	public void update(long id, LibraryDto libraryDTO) {
		// TODO Auto-generated method stub
		Library libraryEdit = libraryRepository.findById(id).get();
		if (libraryDTO.getBookName() != null) {
			libraryEdit.setBookName(libraryDTO.getBookName());
		} else if (libraryDTO.getBookAuthor() != null) {
			libraryEdit.setBookAuthor(libraryDTO.getBookAuthor());
		}
		libraryRepository.save(libraryEdit);
		
	}

}
