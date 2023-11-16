package com.example.ex02.Service;

import com.example.ex02.Model.Employee;
import com.example.ex02.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService  {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(long id){
        return employeeRepository.findById(id).orElse(null);
    }
    public void addEmployee(Employee employee){
        employeeRepository.save(employee);
    }
    public void updateEmployee(Employee employee){
        employeeRepository.findById(employee.getId()).ifPresent(newEmployee -> {
            newEmployee.setName(employee.getName());
            newEmployee.setEmail(employee.getEmail());
            newEmployee.setAddress(employee.getAddress());
            newEmployee.setPhone(employee.getPhone());
            employeeRepository.save(newEmployee);
        });
    }
    public void deleteEmployee(long id){
        employeeRepository.findById(id).ifPresent(employee -> {
            employeeRepository.deleteById(id);
        });
    }
}
