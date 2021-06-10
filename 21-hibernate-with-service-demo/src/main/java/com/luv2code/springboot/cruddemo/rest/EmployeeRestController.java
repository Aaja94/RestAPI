package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")

public class EmployeeRestController {

	//Inject EmployeeService (use constructor injection)
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService)
	{
		employeeService = theEmployeeService;
	}
	
	//expose "/employee" and return list of employee
	@GetMapping("/employee")
	public List<Employee> findAll()
	{
		return employeeService.findAll();
	}
	
	//add mapping for GET "/employee/{employeeId} "
	@GetMapping("employee/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId)
	{
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null)
		{
			throw new RuntimeException("Given Employee Not Found : " +employeeId);
		}
		
		return theEmployee;
		
	}
	
	//Add mapping POST "/employee" - save
	@PostMapping("/employee/{employeeId}")
	public Employee addEmployee(@RequestBody Employee theEmployee)
	{
		theEmployee.setId(0);
		employeeService.save(theEmployee);
		return theEmployee;
	}
	
	//add mapping PUT "/employee" -update
	@PutMapping("/employee")
	public Employee updateEmployee(@RequestBody Employee theEmployee)
	{
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	//add mapping for DELETE"employee/{employeeId}" -delete
	@DeleteMapping("/employee/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId)
	{
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null)
		{
			throw new RuntimeException("Given Employee Not Found : " +employeeId);
		}
		employeeService.delete(employeeId);
		return "Deleted Employee ID :" +employeeId;
	}
	
}
