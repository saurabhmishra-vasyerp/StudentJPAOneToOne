package com.example.StudentLib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentLib.Dto.LibraryDto;
import com.example.StudentLib.Dto.StudentDto;
import com.example.StudentLib.Repo.LibraryRepository;
import com.example.StudentLib.Repo.StudentRepository;
import com.example.StudentLib.model.Library;
import com.example.StudentLib.model.Student;

@Service
public class StudentServiceimpl implements StudentService{
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public List<StudentDto> getAllStudents() {
		// TODO Auto-generated method stub
		List<StudentDto> list = new ArrayList<>();
		List<Student> studentList = studentRepository.findAll();
		for(Student student: studentList) {
			StudentDto studentDto = new StudentDto();
			studentDto.setId(student.getId());
			studentDto.setRollNumber(student.getRollNumber());
			studentDto.setName(student.getName());
		    studentDto.setEmail(student.getEmail());
			
			LibraryDto libraryDto = new LibraryDto();
			libraryDto.setBookId(student.getBook().getBookId());
			libraryDto.setBookName(student.getBook().getBookName());
			libraryDto.setBookAuthor(student.getBook().getBookAuthor());
			studentDto.setLibraryDto(libraryDto);
			list.add(studentDto);
		}
		return list;
	}

	@Override
	public StudentDto getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id).get();
		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setRollNumber(student.getRollNumber());
		studentDto.setEmail(student.getEmail());
		studentDto.setName(student.getName());
		
		LibraryDto libraryDto = new LibraryDto();
		libraryDto.setBookId(student.getBook().getBookId());
		libraryDto.setBookName(student.getBook().getBookName());
		libraryDto.setBookAuthor(student.getBook().getBookAuthor());
		studentDto.setLibraryDto(libraryDto);
		
		return studentDto;
	}

	@Override
	public Student deleteStudentById(Long id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

		studentRepository.deleteById(id);

		// Convert the deleted student to DTO and return it
		return student;
	}
	

	@Override
	public void save(StudentDto studentDto) {
		// TODO Auto-generated method stub
		if (studentDto.getId() == 0) {
			Student student = new Student();
			Library library = new Library();
			student.setRollNumber(studentDto.getRollNumber());
			student.setName(studentDto.getName());
			student.setEmail(studentDto.getEmail());
			//student.setNumber(studentDto.getNumber());
			library.setBookId(studentDto.getLibraryDto().getBookId());
			library.setBookName(studentDto.getLibraryDto().getBookName());
			library.setBookAuthor(studentDto.getLibraryDto().getBookAuthor());
			// Save the library first
			libraryRepository.save(library);
			// Set the library in the student
			student.setBook(library);
			// Save the student
			studentRepository.save(student);
		} else {
			Optional<Student> optionalStudent = studentRepository.findById(studentDto.getId());
//			Student studentEdit = studentRepository.findById(studentDTO.getId()).get();
			if (optionalStudent.isPresent()) {
				Student studentEdit = optionalStudent.get();
				if (studentDto.getRollNumber() != null) {
					studentEdit.setRollNumber(studentDto.getRollNumber());
				} else if (studentDto.getName() != null) {
					studentEdit.setName(studentDto.getName());
				} else if (studentDto.getEmail() != null) {
					studentEdit.setEmail(studentDto.getEmail());
			} 
				// Update library information
				Library libraryEdit = studentEdit.getBook();
				if (libraryEdit != null && studentDto.getLibraryDto() != null) {
					libraryEdit.setBookId(studentDto.getLibraryDto().getBookId());
					libraryEdit.setBookName(studentDto.getLibraryDto().getBookName());
					libraryEdit.setBookAuthor(studentDto.getLibraryDto().getBookAuthor());
				}
				studentRepository.save(studentEdit);
			}
		}
		
}

	@Override
	public void update(long id, StudentDto studentDto) {
		// TODO Auto-generated method stub
		Optional<Student> optionalStudent = studentRepository.findById(id);

		if (optionalStudent.isPresent()) {
			Student studentEdit = optionalStudent.get();

			if (studentDto.getId() != 0) {
				studentEdit.setId(studentDto.getId());
			}

			if (studentDto.getRollNumber() != null) {
				studentEdit.setRollNumber(studentDto.getRollNumber());
			}

			if (studentDto.getName() != null) {
				studentEdit.setName(studentDto.getName());
			}

			if (studentDto.getEmail() != null) {
				studentEdit.setEmail(studentDto.getEmail());
			}


			Library library = new Library();

			studentEdit.setRollNumber(studentDto.getRollNumber());
			studentEdit.setName(studentDto.getName());
			studentEdit.setEmail(studentDto.getEmail());
			//studentEdit.setNumber(studentDto.getNumber());
			library.setBookId(studentDto.getLibraryDto().getBookId());
			library.setBookName(studentDto.getLibraryDto().getBookName());
			library.setBookAuthor(studentDto.getLibraryDto().getBookAuthor());

			// Save the library
			libraryRepository.save(library);
			studentEdit.setBook(library);
			studentRepository.save(studentEdit);
		}

		
	}

}
