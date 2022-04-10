package com.yousef.managers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.yousef.entities.Student;

public class StudentManager {
	private static EntityManager manager;
	private static EntityManagerFactory factory;

	public StudentManager() {
		connect();
	}

	private void connect() {
		factory = Persistence.createEntityManagerFactory("StudentUnit");
		manager = factory.createEntityManager();
	}

	public void beginTransaction() {
		manager.getTransaction().begin();
	}
	public void commitTransaction() {
		manager.getTransaction().commit();
	}

	public void closeConnection() {
		manager.close();
		factory.close();
	}

	public void addStudent(Student student) {
		manager.persist(student);
	}

	public void updateStudent(Student student) {
		manager.merge(student);
	}

	public Student getStudent(String id) {
		Student foundStudent = manager.find(Student.class, id);
		return foundStudent;
	}

	public void remove(String id) {
		Student s = manager.getReference(Student.class, id);
		manager.remove(s);
	}
	
	public List<Student> getAllStudents() {
		String jpqlQuery = "SELECT s FROM Student s";
		List<Student> studentsList = manager.createQuery(jpqlQuery).getResultList();
		return studentsList;
	}
	
}
