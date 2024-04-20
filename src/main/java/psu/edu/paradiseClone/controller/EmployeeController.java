package psu.edu.paradiseClone.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import psu.edu.paradiseClone.entity.Employee;
import psu.edu.paradiseClone.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService empService;
    public EmployeeController(EmployeeService theEmployeeService) {
        empService = theEmployeeService;
    }
    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        // get the employees from db
        List<Employee> theEmployees = empService.findAll();
        // add to the spring model
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }
    @GetMapping("/list/{salary}")
    public String listEmployees(@PathVariable("salary") int salary,Model theModel) {
        // get the employees from db
        List<Employee> theEmployees = empService.findBySalaryGreaterThan(salary);
        // add to the spring model
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }
    // add mapping for "/add"
    @GetMapping("/addForm")
    public String showFormForAdd(Model theModel) {

        //create model attribute to bind form data
        Employee emp = new Employee();
        // add to the spring model
        theModel.addAttribute("employee", emp);
        theModel.addAttribute("action", "/employees/create");
        return "employees/employee-form";
    }
     //add mapping for POST "/employees" and create an employee
     
    @PostMapping("/create") 
    public String createEmployee(@Valid @ModelAttribute("employee") Employee  employee,BindingResult result, Model model) {
     
	     if (result.hasErrors()) {
	    	 model.addAttribute("error", result.getFieldError());
	    	 return "employees/list-employees";
	     }
	    	
	     //save employee
	     empService.save(employee);
	     
	     //redirect to prevent duplicate submissions
	     
	     return "redirect:/employees/list";
     }
    @PostMapping("/update") 
    public String updateEmployee(@Valid @ModelAttribute("employee") Employee  employee,BindingResult result, Model model) {
     
	     if (result.hasErrors()) {
	    	 model.addAttribute("error", result.getFieldError());
	    	 return "employees/list-employees";
	     }
	    	
	     //save employee
	     empService.save(employee);
	     
	     //redirect to prevent duplicate submissions
	     
	     return "redirect:/employees/list";
     }
    
    
    @GetMapping("/updateForm")
    public String showUpdateForm(@RequestParam("employeeId") int id, Model theModel)
    {
    	
        //get the employee from the service
        Employee emp = empService.findById(id);
        
        //set employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", emp);
        theModel.addAttribute("action", "/employees/update");
        
        //send to the form
        return "employees/employee-form";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id)
    {
    	//delete employee
        empService.deleteById(id);

        
        //send to the form
        return "redirect:/employees/list";
    }
}