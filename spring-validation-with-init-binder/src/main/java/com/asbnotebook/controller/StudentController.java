package com.asbnotebook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asbnotebook.dto.Student;
import com.asbnotebook.validator.StudentValidator;

@RestController("/")
public class StudentController {

	@Autowired
	private StudentValidator studentValidator;

	@InitBinder(value = "student")
	void initStudentValidator(WebDataBinder binder) {
		binder.setValidator(studentValidator);
		/*
		 * binder.setValidator(new Validator() {
		 * 
		 * @Override public boolean supports(Class<?> arg0) { return
		 * Student.class.equals(arg0); }
		 * 
		 * @Override public void validate(Object target, Errors errors) { Student
		 * student = (Student) target; if (student.getGrade() <= 0) { errors.reject(
		 * "student.grade.error", "Student grade should be greater than zero"); } }
		 * 
		 * 
		 * });
		 */
	}

	@PostMapping("/student")
	public ResponseEntity<Student> saveStudent(@RequestBody @Valid Student student) {
		// Other logic here(Calling the service layer,etc.)
		return new ResponseEntity<>(student, HttpStatus.CREATED);
	}
}