package com.Backend.Employee.Management.System.Controllor;


import com.Backend.Employee.Management.System.Entity.Employee;
import com.Backend.Employee.Management.System.Entity.EmployeeDto;
import com.Backend.Employee.Management.System.Entity.Pagedata;
import com.Backend.Employee.Management.System.Entity.PagedataDto;
import com.Backend.Employee.Management.System.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth/user/")
public class Mycontrollor {
    @Autowired
    Service service;
    @PostMapping("save")
   public List<Employee> saveall(@RequestBody List<Employee> e){
       return service.save(e);
   }

   @GetMapping("employess")
    public PagedataDto employee(@RequestBody Pagedata pagedata){
       return service.getemployee(pagedata);
   }
   @GetMapping(value = "employee/{id}")
    public Optional<Employee> getbyid(@PathVariable Long id){
       return service.getByid(id);
   }
   @PutMapping(value = "employees/{id}")
    public EmployeeDto update(@PathVariable Long id, @RequestBody EmployeeDto update){
       return service.update(id,update);
   }
   @DeleteMapping(value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
       try {
           service.delete(id);
           return ResponseEntity.ok("Employee with ID " + id + " deleted successfully.");
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee with ID " + id + " not found.");
       }
   }

   @GetMapping(value = "report/department")
   public ResponseEntity<Map<String, Integer>> getDepartmentCount() {
       Map<String, Integer> departmentCount = service.getEmployeeCountByDepartment();
       return ResponseEntity.ok(departmentCount);
   }
   @GetMapping(value = "report/salary")
   public EmployeeDto salary(){
        return service.getsalary();
   }
}
