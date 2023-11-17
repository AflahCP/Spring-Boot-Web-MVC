package com.let.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.let.binding.Student;
import com.let.entity.StudentEntity;
import com.let.repository.StudentRepository;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping(path = "/")
	public String loadForm(Model model) {
		loadFormData(model);
		return "index";
	}
	
	public void loadFormData(Model model) {
		List<String> coursesList = new ArrayList<>();
		coursesList.add("Java");
		coursesList.add("DevOps");
		coursesList.add("Python");
		coursesList.add("AWS");
		
		List<String> timingsList = new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");
		
		Student student = new Student();
		
		model.addAttribute("courses", coursesList);
		model.addAttribute("timings", timingsList);
		model.addAttribute("student", student);
	}
	
	//	method to save student form data
	@PostMapping(path = "/save")
	public String handleSubmit(Student student, Model model) {
		// logic to save
		
		StudentEntity studentEntity = new StudentEntity();
		
		// copy data from binding object to entity object
		
		BeanUtils.copyProperties(student, studentEntity);
		
		studentEntity.setTimings(Arrays.toString(student.getTimings()));
		
		studentRepository.save(studentEntity);
		
		model.addAttribute("msg", "Student saved");
		
		loadFormData(model);
		
		return "index";
	}
	
	// method to display students data
	@GetMapping(path = "/viewStudents")
	public String getStudentsData(Model model) {
		// logic to fetch and send students data
		
		List<StudentEntity> studentEntities = studentRepository.findAll();
		model.addAttribute("students", studentEntities);
		return "data";
	}
}
