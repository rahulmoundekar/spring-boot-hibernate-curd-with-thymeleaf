package com.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.Employee;
import com.app.repository.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeDao employeeDao;

	@RequestMapping(value = "/")
	public String loandingPage(Model model) {
		model.addAttribute("employeeForm", new Employee());
		return "index"; // this is a page name
	}

	@PostMapping(value = "employee")
	public String employeeSaveOrUpdate(Model model, @ModelAttribute("employeeForm") @Valid Employee employee,
			BindingResult error, RedirectAttributes attributes) {
		if (error.hasErrors()) {
			model.addAttribute("employeeForm", employee);
			return "index";
		} else {
			if (employee.getId() == "") {
				employee.setId(null);
			}
			Employee employeeResponse = employeeDao.save(employee);
			if (employeeResponse != null) {
				attributes.addFlashAttribute("success", "Employee save successfully!");
			} else {
				attributes.addFlashAttribute("error", "Employee not save !");
			}
		}

		return "redirect:/";
	}

	@RequestMapping(value = "delete")
	public String deleteEmployee(Model model, @RequestParam("id") String id, RedirectAttributes attributes) {
		employeeDao.deleteById(id);
		attributes.addFlashAttribute("success", "Record Deleted");
		return "redirect:/";
	}

	@GetMapping(value = "edit")
	public String getById(Model model, @RequestParam("id") String id) {
		Optional<Employee> optEmployee = employeeDao.findById(id);
		model.addAttribute("employeeForm", optEmployee.isPresent() ? optEmployee.get() : null);
		return "index";
	}

	@ModelAttribute("employees")
	public List<Employee> employees() {
		return employeeDao.findAll();
	}

	@GetMapping(value = "byAge")
	public String findByAgeWhereAgeIsGreaterThan() {
		employeeDao.findByAgeWhereAgeIsGreaterThan(29).forEach(System.out::println);
		return null;
	}

}
