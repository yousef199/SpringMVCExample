package com.yousef.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yousef.di.AppConfig;
import com.yousef.entities.Student;
import com.yousef.managers.StudentManager;

@Controller
public class AddController {
	private final StudentManager studentManager = new StudentManager();

	@RequestMapping(path = "/addStudent" , method = RequestMethod.GET) 
	public ModelAndView add(HttpServletRequest req , HttpServletResponse res) {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		int age = Integer.parseInt(req.getParameter("age"));
		Student s = new Student(id , name , age);
		addStudentToDatabase(s);
		ModelAndView mv = new ModelAndView();
        mv.setViewName("responsePage");
        mv.getModel().put("data", "Student Inserted Successfully");
        return mv;
	}
	
	@RequestMapping(path = "/" , method = RequestMethod.GET)
	public String welcome() {
		System.out.println("success");
		return "index";
	}
	
	
	@RequestMapping(path = "/getStudents" , method = RequestMethod.GET)
	public ModelAndView getAllStudents() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("studentsTable");
		List<Student> studentsList = studentManager.getAllStudents();
		mv.getModel().put("StudentsList", studentsList);
		return mv;
	}
	
	
	private void addStudentToDatabase(Student s) {
		studentManager.beginTransaction();
		studentManager.addStudent(s);
		studentManager.commitTransaction();
	}
}
