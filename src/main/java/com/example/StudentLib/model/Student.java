package com.example.StudentLib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;



import lombok.Data;

@Entity
@Table(name="student")
@Data
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	@Column(name="roll_number")
	private String rollNumber;

	
	@OneToOne
	private Library book;


	public Library getBook() {
		return book;
	}


	public void setBook(Library book) {
		this.book = book;
	}


	public String getRollNumber() {
		return rollNumber;
	}


	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}


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




	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", rollNumber=" + rollNumber + ", book="
				+ book + "]";
	}
	

}
