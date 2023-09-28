package com.example.fileupload.service;

//import com.example.fileupload.dto.Employee;

import com.example.fileupload.model.infosys.Employee;
import com.example.fileupload.repository.cache.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    @Qualifier("employeeRepository")
    EmployeeRepo er;

    public List<Employee> getAll() {

//        return (List<Employee>) er.getAll();
        return null;
    }

    public void add_employee(String employee_name, String employee_status , int salary)
    {
        Employee emp=new Employee();
        emp.setEmployee_name(employee_name);
        emp.setEmployee_status(employee_status);
        emp.setSalary(salary);
//        employeeRepositories.save(emp);


    }
    public Optional<Employee> findById(int id) {

//        Employee em= er.get(String.valueOf(id));

//        return Optional.ofNullable(em);
        return null;
    }


    public void cacheEmployee(){

        Employee emp=new Employee();
        emp.setEmployee_id(123);
        emp.setEmployee_name("rabish");
        emp.setEmployee_status("active");
        emp.setSalary(11110);
        er.create(emp);
        Employee emp2=er.get(123);
        System.out.println();
       er.delete(1123);

    }

}
