package com.Backend.Employee.Management.System.Controllor;

import com.Backend.Employee.Management.System.Entity.*;
import com.Backend.Employee.Management.System.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("auth/")
public class Mycontrollor {
    @Autowired
    Service service;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("save")
    public List<Employee> saveall(@RequestBody List<Employee> e){
       return service.save(e);
   }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("employess")
    public PagedataDto employee(@RequestBody Pagedata pagedata){
       return service.getemployee(pagedata);
   }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping(value = "employee/{id}")
    public Optional <Employee> getbyid(@PathVariable Long id){
       return service.getByid(id);
   }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "employees/{id}")
    public Employee update(@PathVariable Long id, @RequestBody EmployeeDto update){
       return service.update(id,update);
   }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.ok(" employee deleted successfully!");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "report/department")
   public ResponseEntity<Map<String, Integer>> getDepartmentCount() {
       Map<String, Integer> departmentCount = service.getEmployeeCountByDepartment();
       return ResponseEntity.ok(departmentCount);
   }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "report/salary")
   public EmployeeSellryDto salary(){
        return service.getsalary();
   }

}
