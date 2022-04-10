package com.yousef.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yousef.entities.Student;
import com.yousef.services.StudentService;

import lombok.RequiredArgsConstructor;

@Controller 
@RequestMapping(path = "/Student/")
public class StudentController {
	private final StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(path = "/addStudent" , method = RequestMethod.POST) 
	public ModelAndView add(HttpServletRequest req , HttpServletResponse res) {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		int age = Integer.parseInt(req.getParameter("age"));
		studentService.addStudent(id, name, age);
		ModelAndView mv = new ModelAndView();
        mv.setViewName("responsePage");
        mv.getModel().put("data", "Student Inserted Successfully");
        return mv;
	}
	
	
	
	@RequestMapping(path = "/getStudents" , method = RequestMethod.GET)
	public ModelAndView getAllStudents() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("studentsTable");
		List<Student> studentsList = studentService.getAllStudents();
		mv.getModel().put("StudentsList", studentsList);
		return mv;
	}
}
