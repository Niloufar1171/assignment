package com.assignment.assignment.controller;

import com.assignment.assignment.model.Employee;
import com.assignment.assignment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController// marks it as a rest controller meaning it's methods would returns a domain object
@RequestMapping("/api/employees")//it means it maps all requests starting with this path to handle by methods in this controllers!
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //get all the employee
    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeService.getAllemployee();
    }

    //get by Id
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id ){
        return employeeService.getById(id);
    }

    @PostMapping
    public ResponseEntity< Employee> createEmployee(@RequestBody Employee e){
        Employee savedEmployee = employeeService.createEmployee(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee e){
        if(!employeeService.getById(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        e.setId(id); // Set the id in case it's not in the request
        Employee updatedEmployee = employeeService.updateEmplyee(id, e);
        return ResponseEntity.ok(updatedEmployee);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id ){
        employeeService.deleteEmployee(id);
    }

}
