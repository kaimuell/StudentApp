package com.student.controller;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.StudentProperties;
import com.student.core.Student;
import com.student.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
	
	@Inject 
	private StudentProperties studentProperties;
	@Inject
	private StudentService studentService;
 
	@GetMapping(path="/msg")
	public String getMessage(@RequestHeader("user-agent") String userAgent) {
		return studentProperties.getGreeting() + " using " + userAgent;
	}
	
	@GetMapping
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}

	@GetMapping("/single")
	public Student getSingleStudent(@RequestParam("id") Optional<Long> optional) {
		return studentService.get(optional.orElse(1l));
	}

	@GetMapping("/search/{department}")
	public Collection<Student> getStudentsPerDepartment(@PathVariable String department,
														@RequestParam("name") Optional<String> optionalSurname){
		return studentService.getAllStudentsInDepartment(department, optionalSurname.orElse(""));
	}
	@PostMapping
	public ResponseEntity<String> addStudent(@RequestParam Student student) {
		studentService.add(student);
		if (student.getId() > 0) {
			URI uri = URI.create("/college/student/" + student.getId());
			return ResponseEntity.accepted().location(uri).build();
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

}
