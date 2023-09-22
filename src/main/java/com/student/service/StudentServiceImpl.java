package com.student.service;

import java.util.Collection;
import java.util.Queue;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.student.core.Student;

@Named
public class StudentServiceImpl implements StudentService {

	@PersistenceContext
	EntityManager em;

	@Override
	public Student get(long id) {
		return em.find(Student.class, id);
	}

	@Override
	public Collection<Student> getAllStudents() {
		TypedQuery<Student> q = em.createQuery("FROM Student", Student.class);
		return q.getResultList();
	}


}
