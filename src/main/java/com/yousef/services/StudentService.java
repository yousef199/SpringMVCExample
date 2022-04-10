package com.yousef.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yousef.entities.Student;
import com.yousef.managers.StudentManager;

@Service
public class StudentService {
	private final StudentManager studentManager;
	
	@Autowired
	public StudentService(StudentManager studentManager) {
		this.studentManager = studentManager;
	}

	public void addStudent(String id , String name , int age) {
		Student s = new Student(id , name , age);
		studentManager.addStudent(s);
	}
	
	public List<Student> getAllStudents(){
		return studentManager.getAllStudents();
	}

}
