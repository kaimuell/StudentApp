package com.student.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import com.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.core.Student;
import com.student.service.StudentService;

 

@RequestMapping("/student")
@RestController
@CrossOrigin
public class StudentController {

	@Inject
	private StudentService studentService;

	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getAll() {
		return studentService.getAllStudents();
	}
	
	@GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(@PathVariable("id") long id) {
		return studentService.get(id);
	}
	
	@GetMapping(path="/search/department", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getDepartments() {
		 return studentService.getAllStudents().stream().map(p-> p.getDept()).distinct().collect(Collectors.toList());
	}
	
	@GetMapping(path="/search/department/{department}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Student> getStudentsByDepartment(@PathVariable ("department") String department) {
		 return studentService.getStudentsByDepartment(department);
	}
	@PostMapping
	public ResponseEntity<String> add(@RequestBody Student student){
		studentService.save(student);
		return ResponseEntity.accepted().header("location", "/student/"+student.getId()).build();

	}

	 
}
