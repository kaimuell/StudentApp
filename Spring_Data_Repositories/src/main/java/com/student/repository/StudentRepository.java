package com.student.repository;

import com.student.core.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "student", path = "enrollments", excerptProjection = Person.class)
public interface StudentRepository extends JpaRepository<Student, Long> {

    Collection<Student> findByDept(String department);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Student save(Student entity);

}
