package com.assignment.assignment.service;



import com.assignment.assignment.model.Employee;
import com.assignment.assignment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository ;
    //Fetch or get
    public List<Employee> getAllemployee(){return repository.findAll();}
    public Optional<Employee> getById (Long id ) {return repository.findById(id);}

    //Create or save
    public Employee createEmployee(Employee e) {return repository.save(e);}

    public Employee updateEmplyee(Long id, Employee newEmployee){
        Employee e = repository.findById(id).orElseThrow();
        e.setFirstName(newEmployee.getFirstName());
        e.setLastName(newEmployee.getLastName());
        return repository.save(e);
    }
    //Delete
    public void deleteEmployee(Long id){
        repository.deleteById(id);
    }
}
