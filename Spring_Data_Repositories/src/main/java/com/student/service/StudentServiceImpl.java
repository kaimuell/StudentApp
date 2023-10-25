package com.student.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.student.core.Student;
import com.student.repository.StudentRepository;

@Named
public class StudentServiceImpl implements StudentService {

	@Inject
	private StudentRepository studentRepository;
	 
	@Override
	public Student get(long id) {
		return studentRepository.getOne(id);
	}

	@Override
	public Collection<Student> getAllStudents() {
 		return studentRepository.findAll();
 	}

	@Override
	public Collection<Student> getStudentsByDepartment(String department) {
		return studentRepository.findByDept(department);
	}

	@Override
	public void save(Student student) {
		studentRepository.save(student);
	}


}
