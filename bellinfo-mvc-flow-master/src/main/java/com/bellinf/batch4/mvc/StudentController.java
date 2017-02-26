package com.bellinf.batch4.mvc;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bellinf.batch4.mvc.model.Student;
import com.bellinf.batch4.mvc.repository.StudentRepo;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
	@RequestMapping(method = RequestMethod.GET)
	public String getStudent(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "student-login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String studentDetails(@Valid @ModelAttribute Student student, BindingResult result,
			@RequestParam("name") String name, @RequestParam("course") String course, @RequestParam("fee") Integer fee,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("course", course);
		model.addAttribute("fee", fee);
		model.addAttribute("date", student.getDt());
		student.setName(name);
		student.setCourse(course);
		student.setFee(fee);
		StudentRepo repDao = new StudentRepo();
		String isExists = repDao.getUserDetails(name);
		if (isExists == null) {
			repDao.saveRegistraionDetails(student);
			if (result.hasErrors()) {
				return "student-login";
			}
			return "success1";
		} else {
			System.out.println("User already exists");
			return "exists";
		}

	}
}
