package com.example.StudentLib.Dto;

import lombok.Data;

@Data
public class StudentDto {
	private Long id;
	private String name;
	private String email;
	private String rollNumber;
	
	private LibraryDto libraryDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public LibraryDto getLibraryDto() {
		return libraryDto;
	}

	public void setLibraryDto(LibraryDto libraryDto) {
		this.libraryDto = libraryDto;
	}

	@Override
	public String toString() {
		return "StudentDto [id=" + id + ", name=" + name + ", email=" + email + ", rollNumber=" + rollNumber
				+ ", libraryDto=" + libraryDto + "]";
	}
	

}
